package com.project.Evenemenetyback.repository;

import com.project.Evenemenetyback.domain.Covoiturage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository

public interface CovoiturageRepository extends JpaRepository<Covoiturage, Long> {

    Optional<Covoiturage> findOneById(Long id);

    @Query("select c from Covoiturage c inner join c.user u where u.username=:username")
    List<Covoiturage> findUserCovoiturages(String username);

    @Query("select c from Covoiturage c inner join c.event e where e.id=:id")
    List<Covoiturage> findEventCovoiturages(Long id);
}
