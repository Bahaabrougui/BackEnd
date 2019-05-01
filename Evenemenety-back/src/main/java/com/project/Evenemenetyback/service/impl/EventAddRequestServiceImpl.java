package com.project.Evenemenetyback.service.impl;

import com.project.Evenemenetyback.domain.Event;
import com.project.Evenemenetyback.domain.EventAddRequest;
import com.project.Evenemenetyback.domain.User;
import com.project.Evenemenetyback.repository.EventAddRequestRepository;
import com.project.Evenemenetyback.repository.UserRepository;
import com.project.Evenemenetyback.service.EventAddRequestService;
import org.apache.http.annotation.Obsolete;
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

    private final UserRepository userRepository;


    public EventAddRequestServiceImpl(EventAddRequestRepository eventAddRequestRepository, UserRepository userRepository){
        this.eventAddRequestRepository=eventAddRequestRepository;
        this.userRepository=userRepository;
    }

    /**
     * Save a request.
     *
     * @param request the entity to save
     * @return the persisted entity
     */
    @Override
    public EventAddRequest save(EventAddRequest request, String username) {
        log.debug("Request to save event : {}", request);

        User user = userRepository.findByUsername(username).get();

        request.setUser(user);
        EventAddRequest result = eventAddRequestRepository.save(request);
        return result;
    }

    public List<EventAddRequest> findByMusic(String music){

        log.debug("Request to get requests by music : {}", music);

        List<EventAddRequest> requests = eventAddRequestRepository.findByMusic(music);

        return requests;

    }

    public List<EventAddRequest> findByName(String music){

        log.debug("Request to get requests by name : {}", music);

        List<EventAddRequest> requests = eventAddRequestRepository.findByName(music);

        return requests;

    }

    /**
     * Transform the request into an event.
     *
     * @param request the body of the entity
     * @return the entity
     */

    @Override
    public Event merge(EventAddRequest request){

        Event event = new Event();
        event.setEndingTime(request.getEndingTime());
        event.setStartingTime(request.getStartingTime());
        event.setPlace(request.getPlace());
        event.setMusic(request.getMusic());
        event.setDescription(request.getDescription());
        event.setDate(request.getDate());
        event.setName(request.getName());

        return event;

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
