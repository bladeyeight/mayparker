package com.teksystems.mayparker.database.dao;

import com.teksystems.mayparker.database.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongDAO extends JpaRepository<Song, Long> {
    public List<Song> findAll();
}