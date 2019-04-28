package com.project.Evenemenetyback.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.project.Evenemenetyback.domain.Covoiturage;
import com.project.Evenemenetyback.domain.Event;
import com.project.Evenemenetyback.domain.User;
import com.project.Evenemenetyback.message.response.ResponseMessage;
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

    private final EventService eventService;

    private final CovoiturageService covoiturageService;

    private final EventRepository eventRepository;

    private final JwtProvider jwtProvider;

    public CovoiturageResource(EventRepository eventRepository, EventService eventService, JwtProvider jwtProvider, CovoiturageService covoiturageService) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
        this.jwtProvider = jwtProvider;
        this.covoiturageService = covoiturageService;
    }


}
