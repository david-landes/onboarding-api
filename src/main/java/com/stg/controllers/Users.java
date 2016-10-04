package com.stg.controllers;

import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stg.daos.UserDao;
import com.stg.helpers.MailHelper;
import com.stg.models.User;

@BasePathAwareController
@CrossOrigin
@RequestMapping(value = "/users", produces = "application/json")
public class Users implements ResourceProcessor<RepositorySearchesResource>, ResourceAssembler<User, Resource<User>> {

	@Autowired
	private UserDao userDao;

	@Autowired
	private EntityLinks entityLinks;

	@Autowired
	private MailHelper mailHelper;

	@RequestMapping(value = "/add-user", method = RequestMethod.POST)
	public ResponseEntity<Resource<User>> getAllUsers(@RequestBody User user) throws Exception {
		User existingUser = userDao.findByEmail(user.getEmail());
		String subject = null;
		String templateName = null;
		Map<String, Object> templateMap = new HashMap<String, Object>();

		// TODO: Move the emailing code to a spring task
		if (existingUser != null) {
			user = existingUser;

			// Set the welcome back email
			subject = "Welcome Back!";
			templateName = "welcome-back";
		} else {
			// Save user
			userDao.save(user);

			// Set the welcome email
			subject = "Welcome!";
			templateName = "welcome";

		}

		// Set the configurations
		templateMap.put("emailAddress", user.getEmail());
		templateMap.put("userName", user.getFirstName() + " " + user.getLastName());

		try {
			mailHelper.sendMail(user.getEmail(), subject, templateName, templateMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		try {
			Resource<User> resource = toResource(user);
			return new ResponseEntity<Resource<User>>(resource, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Resource<User>>(HttpStatus.OK);
		}
	}

	@Override
	public Resource<User> toResource(User user) {
		Resource<User> resource = new Resource<User>(user);

		return resource;
	}

	@Override
	public RepositorySearchesResource process(RepositorySearchesResource resource) {
		LinkBuilder link = entityLinks.linkFor(User.class, "add-user");
		resource.add(new Link(link.toString() + "/add-user", "add-user"));

		return resource;
	}

}
