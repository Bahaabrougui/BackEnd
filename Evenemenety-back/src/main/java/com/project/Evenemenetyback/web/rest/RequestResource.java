package com.project.Evenemenetyback.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.project.Evenemenetyback.domain.Event;
import com.project.Evenemenetyback.domain.EventAddRequest;
import com.project.Evenemenetyback.domain.User;
import com.project.Evenemenetyback.message.response.ResponseMessage;
import com.project.Evenemenetyback.repository.EventAddRequestRepository;
import com.project.Evenemenetyback.repository.EventRepository;
import com.project.Evenemenetyback.security.jwt.JwtProvider;
import com.project.Evenemenetyback.service.CovoiturageService;
import com.project.Evenemenetyback.service.EventAddRequestService;
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
public class RequestResource {


    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final EventAddRequestService eventAddRequestService;

    private final EventAddRequestRepository eventAddRequestRepository;

    private final EventService eventService;

    private final JwtProvider jwtProvider;

    public RequestResource(EventService eventService, EventAddRequestService eventAddRequestService, JwtProvider jwtProvider, EventAddRequestRepository eventAddRequestRepository) {

        this.eventService=eventService;
        this.eventAddRequestService=eventAddRequestService;
        this.jwtProvider = jwtProvider;
        this.eventAddRequestRepository=eventAddRequestRepository;
    }

    @GetMapping("/requests")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed
    public List<EventAddRequest> getAllRequests(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all requests");
        return eventAddRequestService.findAll();
    }

    @GetMapping("/requests/{music}")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed
    public List<EventAddRequest> getAllRequestsByMusic(@PathVariable String music){
        log.debug("REST request to get all requests by music");
        return eventAddRequestService.findByMusic(music);
    }

    @DeleteMapping("/deleteRequest/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        log.debug("REST request to delete Request {}", id);

        eventAddRequestService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("RequestManagement.deleted", id.toString())).build();

    }

    @GetMapping("/searchRequestByMusic/{music}")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed
    public List<EventAddRequest> getAllRequestsyMusic(@PathVariable String music){
        log.debug("REST request to get all requests by music");
        return eventAddRequestService.findByMusic(music);
    }

    @GetMapping("/searchRequestByName/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed
    public List<EventAddRequest> getAllRequestsByName(@PathVariable String name){
        log.debug("REST request to get all requests by name");
        return eventAddRequestService.findByName(name);
    }


    @GetMapping("/request/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed
    public ResponseEntity<?> getRequestDetails(@PathVariable Long id){

        if(!eventAddRequestRepository.existsById(id)){
            return new ResponseEntity<>(new ResponseMessage("Fail -> request not found"),
                    HttpStatus.BAD_REQUEST);
        }

        EventAddRequest request = eventAddRequestRepository.findOneById(id).get();

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Listing request's details", request.getName())).body(request);

    }

    @GetMapping("/requestMerge/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed
    public ResponseEntity<?> mergeRequest(@PathVariable Long id){

        if(!eventAddRequestRepository.existsById(id)){
            return new ResponseEntity<>(new ResponseMessage("Fail -> request not found"),
                    HttpStatus.BAD_REQUEST);
        }

        EventAddRequest request = eventAddRequestRepository.findOneById(id).get();
        Event event =eventAddRequestService.merge(request);
        Event newEvent = eventService.save(event, request.getUser().getUsername());
        eventAddRequestService.delete(id);

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Merging a request into an event", newEvent.getName())).body(newEvent);

    }

    @PostMapping("/requests")
    @Timed
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<EventAddRequest> createRequest(@Valid @RequestBody EventAddRequest eventAddRequest)  {

        log.debug("REST request to save request : {}", eventAddRequest);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        String username = jwtProvider.getUserNameFromJwtToken(token);

        EventAddRequest newRequest = eventAddRequestService.save(eventAddRequest,username);

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("RequeestManagement : Creation", newRequest.getName())).build();
    }
}
