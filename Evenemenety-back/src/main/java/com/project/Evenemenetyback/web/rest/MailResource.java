package com.project.Evenemenetyback.web.rest;


import com.codahale.metrics.annotation.Timed;
import com.project.Evenemenetyback.domain.Mail;
import com.project.Evenemenetyback.message.response.ResponseMessage;
import com.project.Evenemenetyback.repository.MailRepository;
import com.project.Evenemenetyback.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MailResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final MailRepository mailRepository;

    public MailResource(MailRepository mailRepository){
        this.mailRepository=mailRepository;
    }

    @GetMapping("/mails")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed
    public List<Mail> getAllMails(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all mails");
        return mailRepository.findAll();
    }

    @GetMapping("/mail/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed
    public ResponseEntity<?> getMailDetail(@PathVariable Long id) {
        log.debug("REST request to get mail's detail : {}", id);

        if(!mailRepository.existsById(id)){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Event not found"),
                    HttpStatus.BAD_REQUEST);
        }

        Mail mail = mailRepository.findById(id).get();

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("Listing event's details", mail.getSujet())).body(mail);
    }

    @DeleteMapping("/mail/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Timed
    public ResponseEntity<?> delteMail(@PathVariable Long id) {
        log.debug("REST request to delete mail by id : {}", id);

        if(!mailRepository.existsById(id)){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Event not found"),
                    HttpStatus.BAD_REQUEST);
        }

        mailRepository.delete(mailRepository.findById(id).get());
        return ResponseEntity.ok().headers(HeaderUtil.createAlert("mailManagement.deleted", id.toString())).build();
    }

    @PostMapping("/mails")
    @Timed
    public ResponseEntity<Mail> createEvent(@Valid @RequestBody Mail mail)  {

        log.debug("REST request to save a mail : {}", mail);

        Mail newMail = mailRepository.save(mail);

        return ResponseEntity.ok().headers(HeaderUtil.createAlert("EventManagement : Creation", newMail.getSujet())).build();
    }
}
