package com.samperry.mayparker.database.dao;

import com.samperry.mayparker.database.entity.Show;
import com.samperry.mayparker.database.entity.Song;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SongDAOTests {

    @Autowired
    private SongDAO songDao;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveSongTest() {

        Show show = new Show();
        List<Show> shows = new ArrayList<Show>();
        shows.add(show);
        Song song1 = Song.builder().name("Testsong")
                .sShows(shows).build();

        songDao.save(song1);

        //Assertions.assertThat(user1.getId()).isGreaterThan(0);
        Assertions.assertThat(songDao.findById(song1.getId()).getId()).isEqualTo(1);
    }

    @Test
    @Order(2)
    public void getSongTest() {
        Song song = songDao.findById(1);
        Assertions.assertThat(song.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfSongs() {
        List<Song> songs = songDao.findAll();
        Assertions.assertThat(songs.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateSongTest() {
        Song song = songDao.findById(1);
        song.setName("TestUpdatedName");
        Assertions.assertThat(songDao.findById(1).getName()).isEqualTo(song.getName());
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteSongTest() {
        Song song = songDao.findById(1);
        songDao.delete(song);
        Optional<Song> optionalSong = Optional.ofNullable(songDao.findByName(song.getName()));

        Song tempSong = null;
        if (optionalSong.isPresent()) {
            tempSong = optionalSong.get();
        }

        Assertions.assertThat(tempSong).isNull();
    }
}