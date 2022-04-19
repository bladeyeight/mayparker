package com.samperry.mayparker.database.dao;

import com.samperry.mayparker.database.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongDAO extends JpaRepository<Song, Long> {
    public List<Song> findAll();

    public Song findById(@Param("id") Integer id);

    public void deleteById(@Param("id") Integer id);
}