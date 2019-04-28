package com.project.Evenemenetyback.repository;

import com.project.Evenemenetyback.domain.Covoiturage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long> {

    Optional<Covoiturage> findOneById(Long id);



}
