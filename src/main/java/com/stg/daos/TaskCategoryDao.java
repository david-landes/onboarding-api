package com.stg.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.TaskCategory;

@RepositoryRestResource(exported = false)
public interface TaskCategoryDao extends CrudRepository<TaskCategory, String> {

}
