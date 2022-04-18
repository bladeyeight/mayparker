package com.teksystems.mayparker.database.dao;

import com.teksystems.mayparker.database.entity.Show;
import com.teksystems.mayparker.database.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowDAO extends JpaRepository<Show, Long> {
    public List<Show> findAll();

    public Show findById(@Param("id") Integer id);

    public void deleteById(@Param("id") Integer id);
}
