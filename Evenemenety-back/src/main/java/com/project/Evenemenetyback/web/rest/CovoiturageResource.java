package com.project.Evenemenetyback.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.project.Evenemenetyback.domain.Covoiturage;
import com.project.Evenemenetyback.domain.Event;
import com.project.Evenemenetyback.domain.User;
import com.project.Evenemenetyback.message.response.ResponseMessage;
import com.project.Evenemenetyback.repository.CovoiturageRepository;
import com.project.Evenemenetyback.repository.EventRepository;
import com.project.Evenemenetyback.security.jwt.JwtProvider;
import com.project.Evenemenetyback.service.CovoiturageService;
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
public class CovoiturageResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final CovoiturageService covoiturageService;

    private final CovoiturageRepository covoiturageRepository;

    private final JwtProvider jwtProvider;

    public CovoiturageResource(CovoiturageRepository covoiturageRepository, CovoiturageService covoiturageService, JwtProvider jwtProvider) {
        this.covoiturageService = covoiturageService;
        this.covoiturageRepository = covoiturageRepository;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/covoiturages")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Timed
    public List<Covoiturage> getAllCovoiturages(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all carpoolings");
        return covoiturageService.findAll();
    }


    @DeleteMapping("/deleteCovoiturage/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Timed
    public ResponseEntity<Void> deleteCovoiturage(@PathVariable Long id) {
        log.debug("REST request to delete Covoiturage: {}", id);

        covoiturageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("eventManagement.deleted", id.toString())).build();

    }

    @GetMapping("/CovoiturageCreated")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @Timed
    public List<Covoiturage> getUserCovoiturages() {
        log.debug("REST request to get user's events");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        String username = jwtProvider.getUserNameFromJwtToken(token);

        return covoiturageRepository.findUserCovoiturages(username);

    }

    @GetMapping("/covoiturageRelated/{eventid}")
    @Timed
    public List<Covoiturage> getEventCovoiturages(@PathVariable Long eventid) {
        log.debug("REST request to get event's carpoolings");

        return covoiturageRepository.findEventCovoiturages(eventid);

    }


    @GetMapping("/covoiturage/{id}")
    @Timed
    public ResponseEntity<?> getCovoiturageDetails(@PathVariable Long id){

        if(!covoiturageRepository.existsById(id)){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Covoiturage not found"),
                    HttpStatus.BAD_REQUEST);
        }

        Covoiturage covoiturage = covoiturageRepository.findOneById(id).get();

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Listing covoiturage's details", covoiturage.getMeetingPlace())).body(covoiturage);

    }

    @PostMapping("/Covoiturages")
    @Timed
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Covoiturage> createCovoiturage(@Valid @RequestBody Covoiturage covoiturage)  {

        log.debug("REST request to save covoiturage : {}", covoiturage);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        String username = jwtProvider.getUserNameFromJwtToken(token);

        String idString = request.getParameter("eventid");
        Long id = Long.parseLong(idString);

        Covoiturage newCovoiturage = covoiturageService.save(covoiturage,username,id);

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("CovoiturageManagement : Creation", newCovoiturage.getMeetingPlace())).build();
    }

}
