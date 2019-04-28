package com.project.Evenemenetyback.repository;

import com.project.Evenemenetyback.domain.EventAddRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventAddRequestRepository extends JpaRepository<EventAddRequest, Long> {

    List<EventAddRequest> findAll();
}
