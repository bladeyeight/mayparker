package com.samperry.mayparker.database.dao;

import com.samperry.mayparker.database.entity.Show;
import com.samperry.mayparker.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowDAO extends JpaRepository<Show, Long> {

    @Query(value = "select s from Show s")
    List<Show> findAll();

    Show findById(@Param("id") Integer id);

    Show findByLocation(@Param("location") String location);

    @Query(value = "select * from shows s where s.date < NOW()", nativeQuery = true)
    List<Show> getPreviousShows();

    @Query(value = "select * from shows s where s.date >= NOW()", nativeQuery = true)
    List<Show> getUpcomingShows();


    void deleteById(@Param("id") Integer id);
}
