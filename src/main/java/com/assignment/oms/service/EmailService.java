package com.assignment.oms.service;

import com.assignment.oms.model.*;
import org.springframework.http.ResponseEntity;

public interface EmailService {

    ResponseEntity<EmailResponse> sendMail(EmailRequest emailRequest) throws Exception;
}
