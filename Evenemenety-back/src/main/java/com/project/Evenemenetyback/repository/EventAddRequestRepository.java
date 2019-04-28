package com.project.Evenemenetyback.repository;

import com.project.Evenemenetyback.domain.EventAddRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EventAddRequestRepository extends JpaRepository<EventAddRequest, Long> {

    List<EventAddRequest> findAll();
}
