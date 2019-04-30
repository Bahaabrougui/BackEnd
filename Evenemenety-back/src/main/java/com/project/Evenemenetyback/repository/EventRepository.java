package com.project.Evenemenetyback.repository;

import com.project.Evenemenetyback.domain.Covoiturage;
import com.project.Evenemenetyback.domain.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface EventRepository  extends JpaRepository<Event, Long> {

    Optional<Event> findOneById(Long id);

    @Query("select e from Event e where e.name=:name")
    List<Event> findByName(@Param("name") String name);

    @Query("select e from Event e where e.music=:music")
    List<Event> findByMusic(@Param("music") String music);



    @Query("select e from Event e inner join e.user u where u.username=:username")
    List<Event> findUserEvents(String username);



}
