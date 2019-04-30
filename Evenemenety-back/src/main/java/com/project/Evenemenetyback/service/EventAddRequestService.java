package com.project.Evenemenetyback.service;

import com.project.Evenemenetyback.domain.EventAddRequest;

import java.util.List;
import java.util.Optional;

public interface EventAddRequestService {

    /**
     * Save a field.
     *
     * @param eventrequest the entity to save
     * @return the persisted entity
     */
    EventAddRequest save(EventAddRequest eventrequest, String username);

    /**
     * Get the "music" request.
     *
     * @param music the music of the entity
     * @return the entity
     */
    List<EventAddRequest> findByMusic(String music);

    /**
     * Get all the eventrequests.
     *
     * @return the list of entities
     */
    List<EventAddRequest> findAll();


    /**
     * Get the "id" eventrequest.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EventAddRequest> findOne(Long id);

    /**
     * Delete the "id" eventrequest.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the eventrequest corresponding to the query.
     *
     * @param query the query of the search
     *
     * @return the list of entities
     */
}
