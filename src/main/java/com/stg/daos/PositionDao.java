package com.stg.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.Position;

@RepositoryRestResource(exported = false)
public interface PositionDao extends CrudRepository<Position, String> {

    /**
     * Finds the position by the position name
     *
     * @param position
     * @return Position
     */
    public Position findByPosition(String position);
}
