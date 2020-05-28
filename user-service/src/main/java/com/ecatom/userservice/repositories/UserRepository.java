package com.ecatom.userservice.repositories;

import com.ecatom.usercommons.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path="user")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    //using this interface we can create personalized method using keywords like(findBy , And , desired Attribute)
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    @RestResource(path="getUser")//Renaming rest methods
    User findByUserNameIgnoreCase(@Param("name") String username);

    //Same query but with custom SQL
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query
    @Query("select u from User u where u.userName=?1")
    User getUserByUserName(String userName);
}
