package com.samperry.mayparker.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.samperry.mayparker.database.entity.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    public User findById(@Param("id") Integer id);

    //    @Query(value = 'select * from users where email = :email', nativeQuery = true)
    public User findByUsername(@Param("username") String username);

    @Query(value = "select u from User u where u.password = :password", nativeQuery = true)
    public List<User> getByPassword(String password);


}
