package com.project.Evenemenetyback.repository;

import com.project.Evenemenetyback.domain.EventAddRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface EventAddRequestRepository extends JpaRepository<EventAddRequest, Long> {

    Optional<EventAddRequest> findOneById(Long id);

    List<EventAddRequest> findAll();

    @Query("select e from EventAddRequest e where e.music=:music")
    List<EventAddRequest> findByMusic(@Param("music") String music);

    @Query("select e from EventAddRequest e where e.name=:name")
    List<EventAddRequest> findByName(@Param("name") String name);
}
