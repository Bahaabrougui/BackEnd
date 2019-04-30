package com.project.Evenemenetyback.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.project.Evenemenetyback.domain.Event;
import com.project.Evenemenetyback.domain.User;
import com.project.Evenemenetyback.message.response.ResponseMessage;
import com.project.Evenemenetyback.repository.EventRepository;
import com.project.Evenemenetyback.security.jwt.JwtProvider;
import com.project.Evenemenetyback.service.EventService;

import com.project.Evenemenetyback.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final EventService eventService;

    private final EventRepository eventRepository;

    private final JwtProvider jwtProvider;

    public EventResource(EventRepository eventRepository, EventService eventService, JwtProvider jwtProvider) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/events")
    @Timed
    public List<Event> getAllEvents(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all events");
        return eventService.findAll();
    }

    @GetMapping("/searchByMusic/{music}")
    @Timed
    public List<Event> getAllEventsByMusic(@PathVariable String music){
        log.debug("REST request to get all events by music");
        return eventService.findByMusic(music);
            }

    @GetMapping("/searchByName/{name}")
    @Timed
    public List<Event> getAllEventsByName(@PathVariable String name){
        log.debug("REST request to get all events by name");
        return eventService.findByName(name);
    }

    @DeleteMapping("/deleteEvent/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Timed
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        log.debug("REST request to delete Event: {}", id);

        eventService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("eventManagement.deleted", id.toString())).build();

    }

    @GetMapping("/eventsCreated")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Timed
    public List<Event> getUserEvents() {
        log.debug("REST request to get user's events");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        String username = jwtProvider.getUserNameFromJwtToken(token);

        return eventRepository.findUserEvents(username);

    }


    @GetMapping("/event/{id}")
    @Timed
    public ResponseEntity<?> getEventDetails(@PathVariable Long id){

        if(!eventRepository.existsById(id)){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Event not found"),
                    HttpStatus.BAD_REQUEST);
        }

        Event event = eventRepository.findOneById(id).get();

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Listing event's details", event.getName())).body(event);

    }

    @PostMapping("/events")
    @Timed
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event)  {

        log.debug("REST request to save event : {}", event);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        String username = jwtProvider.getUserNameFromJwtToken(token);

        Event newEvent = eventService.save(event,username);

            return ResponseEntity.ok().headers(HeaderUtil.createAlert("EventManagement : Creation", newEvent.getName())).build();
        }
    }




