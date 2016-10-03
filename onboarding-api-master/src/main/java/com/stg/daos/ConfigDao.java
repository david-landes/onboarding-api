package com.stg.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.Config;

@RepositoryRestResource(exported = false)
public interface ConfigDao extends CrudRepository<Config, String> {

}
