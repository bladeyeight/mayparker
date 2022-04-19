package com.samperry.mayparker.database.dao;

import java.util.*;

import com.samperry.mayparker.database.dao.UserDAO;
import com.samperry.mayparker.database.entity.Show;
import com.samperry.mayparker.database.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class UserDAOTests {

    @Autowired
    private UserDAO userDao;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest() {

        Show show = new Show();
        List<Show> shows = new ArrayList<Show>();
        shows.add(show);
        User user1 = User.builder().username("Testman").password("test")
                .uShows(shows).build();

        userDao.save(user1);

        //Assertions.assertThat(user1.getId()).isGreaterThan(0);
        Assertions.assertThat(userDao.findById(user1.getId()).getId()).isEqualTo(1);
    }

    @Test
    @Order(2)
    public void getUserTest() {
        User user = userDao.findById(1);
        Assertions.assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfUsers() {
        List<User> users = userDao.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateUserTest() {
        User user = userDao.findById(1);
        user.setUsername("TestUpdatedUsername");
        Assertions.assertThat(userDao.findById(1).getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteUserTest() {
        User user = userDao.findById(1);
        userDao.delete(user);
        Optional<User> optionalUser = Optional.ofNullable(userDao.findByUsername(user.getUsername()));

        User tempUser = null;
        if (optionalUser.isPresent()) {
            tempUser = optionalUser.get();
        }

        Assertions.assertThat(tempUser).isNull();
    }
}