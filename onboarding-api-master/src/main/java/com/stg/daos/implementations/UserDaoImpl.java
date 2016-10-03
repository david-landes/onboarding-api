package com.stg.daos.implementations;

import org.springframework.data.repository.NoRepositoryBean;

import com.stg.daos.custom.UserCustom;
import com.stg.models.User;

@NoRepositoryBean
public class UserDaoImpl implements UserCustom<User, Long> {

    // @PersistenceContext
    // private EntityManager em;
    //
    // @Override
    // public <S extends User> S save(S entity) {
    // // Check to see if the user already exists
    //
    // em.persist(entity);
    //
    // return entity;
    // }
    //
    // @Override
    // public User findOne(Long id) {
    // User user = em.find(User.class, id);
    // System.out.println(user == null);
    //
    // return user;
    // }

}
