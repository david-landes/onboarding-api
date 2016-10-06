package com.stg.daos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.TaskTemplate;

@RepositoryRestResource(exported = true)
public interface TaskTemplateDao extends CrudRepository<TaskTemplate, String> {

    /**
     * Retrieve all tasks for any position
     *
     * @return
     */
    public List<TaskTemplate> findByPositionIdIsNull();

    /**
     * Retrieve all tasks for a specific position and practice
     *
     * @param positionId
     * @param practiceId
     * @return
     */
    public List<TaskTemplate> findByPositionIdAndPracticeId(long positionId, long practiceId);

    /**
     * Retrieve all tasks for a specific position, but not required to be for a specific practice
     *
     * @param positionId
     * @return
     */
    public List<TaskTemplate> findByPositionIdAndPracticeIdIsNull(long positionId);
}
