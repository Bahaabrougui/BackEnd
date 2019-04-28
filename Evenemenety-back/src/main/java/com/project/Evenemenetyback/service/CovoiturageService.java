package com.project.Evenemenetyback.service;

import com.project.Evenemenetyback.domain.Covoiturage;
import com.project.Evenemenetyback.domain.Event;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Field.
 */
public interface CovoiturageService {

    /**
     * Save a field.
     *
     * @param covoiturage the entity to save
     * @return the persisted entity
     */
    Covoiturage save(Covoiturage covoiturage);

    /**
     * Get all the Covoiturages.
     *
     * @return the list of entities
     */
    List<Covoiturage> findAll();


    /**
     * Get the "id" Covoiturage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Covoiturage> findOne(Long id);

    /**
     * Delete the "id" Covoiturage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the Covoiturage corresponding to the query.
     *
     * @param query the query of the search
     *
     * @return the list of entities
     */
}