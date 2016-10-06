package com.stg.daos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.models.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserDao extends PagingAndSortingRepository<User, Long> {

    /**
     * Retrieves a user by their email
     *
     * @param email
     * @return User
     */
    public User findByEmail(@Param("email") String email);
}
