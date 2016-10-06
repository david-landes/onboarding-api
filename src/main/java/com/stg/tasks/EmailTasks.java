package com.stg.tasks;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.stg.daos.ProfileDao;
import com.stg.daos.UserDao;
import com.stg.helpers.MailHelper;
import com.stg.models.User;

@Component
public class EmailTasks {

    private static final Logger log = LoggerFactory.getLogger(EmailTasks.class);

    @Autowired
    private MailHelper mailHelper;

    @Autowired
    private ProfileDao profileDao;

    @Autowired
    private UserDao userDao;

    /**
     * Sends the welcome emails to all of the users that have a starting date of today
     */
    @Scheduled(cron = "0 0 5 * * MON-FRI")
    public void sendOnboardingEmails() {
	log.info("Starting sendOnboardingEmails");

	// Find all users that need onboarding emails
	List<User> userList = profileDao.findUnwelcomedUsersByStartingDate(new Date());

	// Send the onboarding email for each user
	for (User user : userList) {
	    try {
		// Find out if the user exists already or not
		Long userCount = profileDao.countByUserIdEquals(user.getId());

		// If the user has more than the starting 1 profiles, then they are coming back
		boolean isExistingUser = userCount > 1;

		mailHelper.sendWelcomeEmail(user, isExistingUser);

		user.setWelcomed(true);

		this.userDao.save(user);
	    } catch (Exception e) {
		log.error("Failure sending email for user: " + user.getId(), e);

		// TODO: Send a notification to the admins that we were unable to send an email to a specific user
	    }
	}

	log.info("Ending sendOnboardingEmails");
    }
}
