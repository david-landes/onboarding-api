package com.stg.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.TaskLabel;

@RepositoryRestResource(exported = false)
public interface TaskLabelDao extends CrudRepository<TaskLabel, String> {

}
