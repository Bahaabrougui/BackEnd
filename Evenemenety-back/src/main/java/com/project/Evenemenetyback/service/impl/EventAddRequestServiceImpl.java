package com.project.Evenemenetyback.service.impl;

import com.project.Evenemenetyback.domain.EventAddRequest;
import com.project.Evenemenetyback.repository.EventAddRequestRepository;
import com.project.Evenemenetyback.service.EventAddRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class EventAddRequestServiceImpl implements EventAddRequestService {

    private final Logger log = LoggerFactory.getLogger(CovoiturageServiceImpl.class);

    private final EventAddRequestRepository eventAddRequestRepository;


    public EventAddRequestServiceImpl(EventAddRequestRepository eventAddRequestRepository){
        this.eventAddRequestRepository=eventAddRequestRepository;
    }

    /**
     * Save a request.
     *
     * @param request the entity to save
     * @return the persisted entity
     */
    @Override
    public EventAddRequest save(EventAddRequest request) {
        log.debug("Request to save event : {}", request);
        EventAddRequest result = eventAddRequestRepository.save(request);
        return result;
    }

    /**
     * Get all the requests.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EventAddRequest> findAll() {
        log.debug("Request to get all events");
        return eventAddRequestRepository.findAll();
    }


    /**
     * Get one request by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EventAddRequest> findOne(Long id) {
        log.debug("Request to get event : {}", id);
        return eventAddRequestRepository.findById(id);
    }

    /**
     * Delete the request by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Field : {}", id);
        eventAddRequestRepository.deleteById(id);
    }


}
