package com.project.Evenemenetyback.repository;

import com.project.Evenemenetyback.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {

}
