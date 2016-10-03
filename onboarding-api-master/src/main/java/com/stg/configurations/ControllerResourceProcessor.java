package com.stg.configurations;

import org.springframework.data.rest.webmvc.RepositoryLinksResource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import com.stg.controllers.Practices;

@Component
public class ControllerResourceProcessor
	implements ResourceProcessor<RepositoryLinksResource> {

    @Override
    public RepositoryLinksResource process(RepositoryLinksResource resource) {
	resource.add(ControllerLinkBuilder.linkTo(Practices.class)
		.withRel("practices"));
	return resource;
    }
}
