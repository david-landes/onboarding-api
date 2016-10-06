package com.stg.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.stg.models.Position;
import com.stg.models.Practice;
import com.stg.models.Profile;
import com.stg.models.Task;
import com.stg.models.TaskLabel;
import com.stg.models.User;

@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
	config.exposeIdsFor(User.class);
	config.exposeIdsFor(Practice.class);
	config.exposeIdsFor(Position.class);
	config.exposeIdsFor(Profile.class);
	config.exposeIdsFor(Task.class);
	config.exposeIdsFor(TaskLabel.class);
    }
}
