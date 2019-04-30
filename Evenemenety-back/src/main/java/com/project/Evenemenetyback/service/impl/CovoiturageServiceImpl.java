package com.project.Evenemenetyback.service.impl;

import com.project.Evenemenetyback.domain.Covoiturage;
import com.project.Evenemenetyback.domain.Event;
import com.project.Evenemenetyback.domain.User;
import com.project.Evenemenetyback.repository.CovoiturageRepository;
import com.project.Evenemenetyback.repository.EventRepository;
import com.project.Evenemenetyback.repository.UserRepository;
import com.project.Evenemenetyback.service.CovoiturageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service

public class CovoiturageServiceImpl implements CovoiturageService {

    private final Logger log = LoggerFactory.getLogger(CovoiturageServiceImpl.class);

    private final CovoiturageRepository covoiturageRepository;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    public CovoiturageServiceImpl(CovoiturageRepository covoiturageRepository, UserRepository userRepository, EventRepository eventRepository){
    this.covoiturageRepository= covoiturageRepository;
    this.userRepository = userRepository;
    this.eventRepository= eventRepository;
    }

    /**
     * Save a covoiturage.
     *
     * @param covoiturage the entity to save
     * @return the persisted entity
     */
    @Override
    public Covoiturage save(Covoiturage covoiturage, String username, Long id) {
        log.debug("Request to save covoiturage : {}", covoiturage);

        User user = userRepository.findByUsername(username).get();
        covoiturage.setUser(user);

        Event event = eventRepository.getOne(id);
        covoiturage.setEvent(event);

        Covoiturage result = covoiturageRepository.save(covoiturage);
        return result;
    }

    /**
     * Get all the covoiturages.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Covoiturage> findAll() {
        log.debug("Request to get all covoiturages");
        return covoiturageRepository.findAll();
    }


    /**
     * Get one covoiturage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Covoiturage> findOne(Long id) {
        log.debug("Request to get covoiturage : {}", id);
        return covoiturageRepository.findById(id);
    }

    /**
     * Delete the covoiturage by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete covoiturage : {}", id);
        covoiturageRepository.deleteById(id);
    }


}
