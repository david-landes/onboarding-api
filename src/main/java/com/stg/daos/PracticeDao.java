package com.stg.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.Practice;

@RepositoryRestResource(exported = false)
public interface PracticeDao extends CrudRepository<Practice, String> {

    /**
     * Finds the practice by the practice name
     *
     * @param practice
     * @return Practice
     */
    public Practice findByPractice(String practice);
}
