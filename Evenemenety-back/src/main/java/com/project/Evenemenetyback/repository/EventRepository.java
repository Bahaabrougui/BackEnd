package com.project.Evenemenetyback.repository;

import com.project.Evenemenetyback.domain.Covoiturage;
import com.project.Evenemenetyback.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface EventRepository  extends JpaRepository<Event, Long> {

    Optional<Event> findOneById(Long id);

    Optional<Event> findByMusic(String music);

    @Query("select c from Covoiturage c inner join c.event")
    List<Covoiturage> FindUserEvents();


}
