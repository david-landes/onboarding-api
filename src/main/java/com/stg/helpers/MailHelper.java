package com.stg.helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stg.models.User;
import com.stg.utils.MailUtil;

/**
 * Class that handles the sending of emails
 *
 * @author David Landes
 */
@Component
public class MailHelper {

    @Autowired
    private MailUtil mailUtil;

    /**
     * Sends the welcome email based on the user and whether or not the user
     * already exists. If the user already exists, it will send the welcome back
     * version of the email.
     *
     * @param user
     * @param isExistingUser
     * @throws Exception
     */
    public void sendWelcomeEmail(User user, boolean isExistingUser)
	    throws Exception {
	String subject = null;
	String templateName = null;
	Map<String, Object> templateMap = new HashMap<>();

	if (isExistingUser) {
	    subject = "Welcome Back!";
	    templateName = "welcome";
	} else {
	    subject = "Welcome!";
	    templateName = "welcome";
	}

	// Set the configurations
	templateMap.put("emailAddress", user.getEmail());
	templateMap.put("name", user.getFirstName() + " " + user.getLastName());

	try {
	    this.mailUtil.sendMail(user.getEmail(), subject, templateName,
		    templateMap);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw e;
	}
    }
}
