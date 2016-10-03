package com.stg.daos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.stg.daos.custom.UserCustom;
import com.stg.models.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserDao
	extends PagingAndSortingRepository<User, Long>, UserCustom<User, Long> {

    public User findByEmail(@Param("email") String email);
}
