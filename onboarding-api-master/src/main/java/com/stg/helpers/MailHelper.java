package com.stg.helpers;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.stg.daos.ConfigDao;
import com.stg.models.Config;

import freemarker.template.Configuration;

@Component
public class MailHelper {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ConfigDao configDao;

    @Autowired
    private Configuration freemarkerEngine;

    @PostConstruct
    public void init() {
	JavaMailSenderImpl ms = (JavaMailSenderImpl) mailSender;
	Config hostConfig = configDao.findOne("EMAIL_HOST");
	Config userConfig = configDao.findOne("EMAIL_USERNAME");
	Config passConfig = configDao.findOne("EMAIL_PASSWORD");
	Config portConfig = configDao.findOne("EMAIL_PORT");

	if (hostConfig != null) {
	    ms.setHost(hostConfig.getValue());
	}
	if (userConfig != null) {
	    ms.setUsername(userConfig.getValue());
	}
	if (passConfig != null) {
	    ms.setPassword(passConfig.getValue());
	}
	if (portConfig != null) {
	    ms.setPort(Integer.valueOf(portConfig.getValue()));
	}
    }

    public void sendMail(String toAddress, String subject, String templateName,
	    Map<String, Object> params) throws MessagingException {
	// TODO: Change this to use the emails found in the database

	MimeMessagePreparator preparator = new MimeMessagePreparator() {
	    @Override
	    public void prepare(MimeMessage mimeMessage) throws Exception {
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
		message.setTo(toAddress);
		message.setFrom(
			((JavaMailSenderImpl) mailSender).getUsername());
		message.setSubject(subject);

		String text = FreeMarkerTemplateUtils
			.processTemplateIntoString(
				freemarkerEngine.getTemplate(
					"/" + templateName + ".ftl", "UTF-8"),
				params);

		message.setText(text, true);
	    }
	};
	mailSender.send(preparator);
    }
}
