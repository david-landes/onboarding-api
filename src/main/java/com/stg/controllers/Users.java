package com.stg.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stg.daos.PositionDao;
import com.stg.daos.PracticeDao;
import com.stg.daos.ProfileDao;
import com.stg.daos.TaskTemplateDao;
import com.stg.daos.UserDao;
import com.stg.dtos.NewUser;
import com.stg.exceptions.InvalidParameterException;
import com.stg.helpers.MailHelper;
import com.stg.models.Profile;
import com.stg.models.TaskTemplate;
import com.stg.models.User;

@BasePathAwareController
@RequestMapping(value = "/users", produces = "application/json")
public class Users implements ResourceProcessor<RepositorySearchesResource>, ResourceAssembler<User, Resource<User>> {
    private static final Logger log = LoggerFactory.getLogger(Users.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PositionDao positionDao;

    @Autowired
    private PracticeDao practiceDao;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private TaskTemplateDao taskTemplateDao;

    @Autowired
    private EntityLinks entityLinks;

    @Autowired
    private MailHelper mailHelper;

    /**
     * Call to add a user. If the user already exists, it will just use the existing record to add the new start date
     *
     * @param NewUser
     *            newUser
     * @return User
     * @throws Exception
     */
    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ResponseEntity<Resource<User>> getAllUsers(@RequestBody NewUser newUser) throws Exception {
	if (newUser == null || newUser.getEmail() == null || newUser.getEmail().trim().equals("")) {
	    throw new InvalidParameterException("Must pass in email");
	}

	User existingUser = userDao.findByEmail(newUser.getEmail());
	User user = null;
	boolean isExistingUser = (existingUser != null);

	// Check to see if this user already exists
	if (isExistingUser) {
	    user = existingUser;
	} else {
	    // Verify the new user has all of the necessary information
	    List<String> errors = new ArrayList<String>();
	    if (newUser.getFirstName() == null || newUser.getFirstName().trim().equals("")) {
		errors.add("First Name");
	    }
	    if (newUser.getLastName() == null || newUser.getLastName().trim().equals("")) {
		errors.add("Last Name");
	    }
	    if (newUser.getPosition() == null || newUser.getPosition().trim().equals("")) {
		errors.add("Position");
	    }

	    // Throw an exception if we didn't have all of the necessary information
	    if (errors.size() > 0) {
		throw new InvalidParameterException("Unable to save user. The following fields are required: " + StringUtils.collectionToDelimitedString(errors, ","));
	    }

	    // Save user
	    user = new User();
	    user.setEmail(newUser.getEmail());
	    user.setFirstName(newUser.getFirstName());
	    user.setLastName(newUser.getLastName());
	    user.setPosition(positionDao.findByPosition(newUser.getPosition()));
	    user.setPractice(practiceDao.findByPractice(newUser.getPractice()));

	    userDao.save(user);
	}

	// Get the possible profiles for this user
	LocalDate today = LocalDate.now();
	LocalDate startDate = LocalDate.parse(newUser.getDate(), DateTimeFormatter.ISO_DATE);

	List<Profile> profileList = profileDao.findByEndDateIsNullAndUserIdEquals(user.getId());
	if (profileList.isEmpty()) {
	    // Save user profile
	    Profile profile = new Profile();
	    profile.setUser(user);
	    profile.setStartDate(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

	    profileDao.save(profile);

	    // Add the profile to the user, that way we don't need to get the user again
	    Set<Profile> profileSet = new HashSet<Profile>();
	    if (user.getProfiles() != null && user.getProfiles().size() > 0) {
		profileSet.addAll(user.getProfiles());
	    }

	    profileSet.add(profile);

	    user.setProfiles(profileSet);

	    // TODO: Add tasks for this profile
	    List<TaskTemplate> taskTemplateList = new ArrayList<TaskTemplate>();
	    taskTemplateList.addAll(taskTemplateDao.findByPositionIdIsNull());

	    if (user.getPosition() != null) {
		taskTemplateList.addAll(taskTemplateDao.findByPositionIdAndPracticeIdIsNull(user.getPosition().getId()));

		if (user.getPractice() != null) {
		    taskTemplateList.addAll(taskTemplateDao.findByPositionIdAndPracticeId(user.getPosition().getId(), user.getPractice().getId()));
		}
	    }

	    // TODO: Convert Task Templates to tasks for the user
	} else {
	    // TODO: Find out how we want to handle updating previous profiles
	}

	// Verify if we set the user to send the welcome email now or on a future date
	if (!startDate.isAfter(today)) {
	    // Send email now
	    log.info("Sending welcome email now");

	    mailHelper.sendWelcomeEmail(user, isExistingUser);
	}

	// Send the response back to the client
	try {
	    Resource<User> resource = toResource(user);
	    return new ResponseEntity<Resource<User>>(resource, HttpStatus.OK);
	} catch (Exception e) {
	    e.printStackTrace();
	    return new ResponseEntity<Resource<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @Override
    public Resource<User> toResource(User user) {
	Resource<User> resource = new Resource<User>(user);

	return resource;
    }

    /**
     * Adds the new connection points for the API
     */
    @Override
    public RepositorySearchesResource process(RepositorySearchesResource resource) {
	LinkBuilder link = entityLinks.linkFor(User.class, "add-user");
	resource.add(new Link(link.toString() + "/add-user", "add-user"));

	return resource;
    }

}
