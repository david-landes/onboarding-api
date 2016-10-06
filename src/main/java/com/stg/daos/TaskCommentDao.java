package com.stg.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.TaskComment;

@RepositoryRestResource(exported = true, path = "taskComments", collectionResourceRel = "taskComments")
public interface TaskCommentDao extends CrudRepository<TaskComment, Long> {

}
