package com.samperry.mayparker.database.dao;

import com.samperry.mayparker.database.entity.Show;
import com.samperry.mayparker.database.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowDAO extends JpaRepository<Show, Long> {

    @Query(value = "select s from Show s")
    public List<Show> findAll();

    public Show findById(@Param("id") Integer id);

    public Show findByLocation(@Param("location") String location);


    public void deleteById(@Param("id") Integer id);
}
