package com.stg.controllers;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.stg.dtos.NewUser;
import com.stg.exceptions.InvalidParameterException;
import com.stg.models.User;
import com.stg.services.UserService;

@BasePathAwareController
@CrossOrigin
@RequestMapping(value = "/users", produces = "application/json")
public class Users implements ResourceProcessor<RepositorySearchesResource>, ResourceAssembler<User, Resource<User>> {
    private static final Logger log = LoggerFactory.getLogger(Users.class);

    @Autowired
    private UserService userService;

    @Autowired
    private EntityLinks entityLinks;

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
		if ((newUser == null) || (newUser.getEmail() == null) || newUser.getEmail().trim().equals("")) {
			throw new InvalidParameterException("Must pass in email");
		}

		// Create the user
		User user = userService.createUser(newUser);

		// Send the response back to the client
		try {
			Resource<User> resource = toResource(user);
			return new ResponseEntity<>(resource, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Failure in Users.getAllUsers", e);

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    }

    /**
     * Adds the new connection points for the API
     */
    @Override
    public RepositorySearchesResource process(RepositorySearchesResource resource) {
	LinkBuilder link = this.entityLinks.linkFor(User.class, "add-user");
	resource.add(new Link(link.toString() + "/add-user", "add-user"));

	return resource;
    }

    @Override
    public Resource<User> toResource(User user) {
	Resource<User> resource = new Resource<>(user);

	return resource;
    }

}
