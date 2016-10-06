package com.stg.services;

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
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.stg.daos.PositionDao;
import com.stg.daos.PracticeDao;
import com.stg.daos.ProfileDao;
import com.stg.daos.TaskDao;
import com.stg.daos.TaskTemplateDao;
import com.stg.daos.UserDao;
import com.stg.dtos.NewUser;
import com.stg.exceptions.InvalidParameterException;
import com.stg.helpers.MailHelper;
import com.stg.helpers.TaskTemplateConverter;
import com.stg.helpers.UserConverter;
import com.stg.models.Position;
import com.stg.models.Practice;
import com.stg.models.Profile;
import com.stg.models.Task;
import com.stg.models.TaskTemplate;
import com.stg.models.User;

@Component
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

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
    private TaskDao taskDao;

    @Autowired
    private MailHelper mailHelper;

    public User createUser(NewUser newUser) throws Exception {

	User existingUser = this.userDao.findByEmail(newUser.getEmail());
	User user = null;
	boolean isExistingUser = (existingUser != null);

	// Check to see if this user already exists
	if (isExistingUser) {
	    user = existingUser;
	} else {
	    // Verify the new user has all of the necessary information
	    List<String> errors = this.validateNewUser(newUser);

	    // Throw an exception if we didn't have all of the necessary
	    // information
	    if (errors.size() > 0) {
		throw new InvalidParameterException("Unable to save user. The following fields are required: " + StringUtils.collectionToDelimitedString(errors, ","));
	    }

	    // Save user
	    Position position = this.positionDao.findByPosition(newUser.getPosition());
	    Practice practice = this.practiceDao.findByPractice(newUser.getPractice());

	    user = UserConverter.convertToUser(newUser, position, practice);

	    this.userDao.save(user);
	}

	// Get the possible profiles for this user
	LocalDate today = LocalDate.now();
	LocalDate startDate = LocalDate.parse(newUser.getDate(), DateTimeFormatter.ISO_DATE);

	List<Profile> profileList = this.profileDao.findByEndDateIsNullAndUserIdEquals(user.getId());
	if (profileList.isEmpty()) {
	    this.createNewProfile(user, startDate);
	} else {
	    // TODO: Find out how we want to handle updating previous profiles
	}

	// Verify if we set the user to send the welcome email now or on a future date
	if (!startDate.isAfter(today)) {
	    // Send email now
	    log.info("Sending welcome email now");

	    this.mailHelper.sendWelcomeEmail(user, isExistingUser);

	    user.setWelcomed(true);

	    this.userDao.save(user);
	}

	return user;
    }

    private void createNewProfile(User user, LocalDate startDate) {
	// Save user profile
	Profile profile = new Profile();
	profile.setUser(user);
	profile.setStartDate(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

	this.profileDao.save(profile);

	// Add the profile to the user, that way we don't need to get the
	// user again
	Set<Profile> profileSet = new HashSet<>();
	if ((user.getProfiles() != null) && (user.getProfiles().size() > 0)) {
	    profileSet.addAll(user.getProfiles());
	}

	profileSet.add(profile);

	user.setProfiles(profileSet);

	// Add tasks for this profile
	List<TaskTemplate> taskTemplateList = new ArrayList<>();
	taskTemplateList.addAll(this.taskTemplateDao.findByPositionIdIsNull());

	if (user.getPosition() != null) {
	    taskTemplateList.addAll(this.taskTemplateDao.findByPositionIdAndPracticeIdIsNull(user.getPosition().getId()));

	    if (user.getPractice() != null) {
		taskTemplateList.addAll(this.taskTemplateDao.findByPositionIdAndPracticeId(user.getPosition().getId(), user.getPractice().getId()));
	    }
	}

	// Convert Task Templates to tasks for the user
	List<Task> taskList = TaskTemplateConverter.convertList(taskTemplateList, profile);

	this.taskDao.save(taskList);
    }

    public List<String> validateNewUser(NewUser newUser) throws InvalidParameterException {
	List<String> errors = new ArrayList<>();
	if (newUser == null) {
	    throw new InvalidParameterException("Must pass in user data");
	} else {
	    if ((newUser.getEmail() == null) || newUser.getEmail().trim().equals("")) {
		errors.add("Email");
	    }
	    if ((newUser.getFirstName() == null) || newUser.getFirstName().trim().equals("")) {
		errors.add("First Name");
	    }
	    if ((newUser.getLastName() == null) || newUser.getLastName().trim().equals("")) {
		errors.add("Last Name");
	    }
	    if ((newUser.getPosition() == null) || newUser.getPosition().trim().equals("")) {
		errors.add("Position");
	    }
	}

	return errors;
    }
}
