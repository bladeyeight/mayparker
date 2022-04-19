package com.samperry.mayparker.database.dao;

import com.samperry.mayparker.database.entity.Show;
import com.samperry.mayparker.database.entity.Song;
import com.samperry.mayparker.database.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShowDAOTests {

    @Autowired
    private ShowDAO showDao;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveShowTest() {

        Song song = new Song();
        List<Song> songs = new ArrayList<Song>();
        songs.add(song);
        Show show1 = Show.builder().date(Date.valueOf("2022-04-19")).location("TestLocation").time("2:55 PM")
                .sSongs(songs).build();

        showDao.save(show1);

        //Assertions.assertThat(user1.getId()).isGreaterThan(0);
        Assertions.assertThat(showDao.findById(show1.getId()).getId()).isEqualTo(1);
    }

    @Test
    @Order(2)
    public void getShowTest() {
        Show show = showDao.findById(1);
        Assertions.assertThat(show.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfShows() {
        List<Show> shows = showDao.findAll();
        Assertions.assertThat(shows.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateShowTest() {
        Show show = showDao.findById(1);
        show.setLocation("TestUpdatedLocation");
        Assertions.assertThat(showDao.findById(1).getLocation()).isEqualTo(show.getLocation());
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteShowTest() {
        Show show = showDao.findById(1);
        showDao.delete(show);
        Optional<Show> optionalShow = Optional.ofNullable(showDao.findByLocation(show.getLocation()));

        Show tempShow = null;
        if (optionalShow.isPresent()) {
            tempShow = optionalShow.get();
        }

        Assertions.assertThat(tempShow).isNull();
    }
}