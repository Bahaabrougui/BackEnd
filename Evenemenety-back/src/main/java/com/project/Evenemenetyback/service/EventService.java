package com.project.Evenemenetyback.service;

import com.project.Evenemenetyback.domain.Event;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Field.
 */
public interface EventService {

    /**
     * Save an event.
     *
     * @param event the entity to save
     * @return the persisted entity
     */
    Event save(Event event);

    /**
     * Get all the events.
     *
     * @return the list of entities
     */
    List<Event> findAll();


    /**
     * Get the "id" event.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Event> findOne(Long id);

    /**
     * Delete the "id" event.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the event corresponding to the query.
     *
     * @param query the query of the search
     *
     * @return the list of entities
     */

}