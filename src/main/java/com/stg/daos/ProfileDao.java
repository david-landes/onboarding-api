package com.stg.daos;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.Profile;
import com.stg.models.User;

@RepositoryRestResource(exported = true)
public interface ProfileDao extends CrudRepository<Profile, Long> {

    /**
     * Retrieves the number of profiles for a specific user
     *
     * @param userId
     * @return Long
     */
    public Long countByUserIdEquals(long userId);

    /**
     * Finds a currently open profile for a user
     *
     * @param userId
     * @return List<Profile>
     */
    public List<Profile> findByEndDateIsNullAndUserIdEquals(long userId);

    /**
     * Finds all users where the starting date is a specific date and haven't been welcomed, yet
     *
     * @param startDate
     * @return List<User>
     */
    public List<User> findUnwelcomedUsersByStartingDate(Date startDate);
}
