package com.project.Evenemenetyback.repository;

import com.project.Evenemenetyback.domain.Covoiturage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long> {

    Optional<Covoiturage> findOneById(Long id);



}
