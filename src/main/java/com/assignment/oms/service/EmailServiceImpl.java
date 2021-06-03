package com.assignment.oms.service;

import com.assignment.oms.constants.Message;
import com.assignment.oms.model.*;
import com.assignment.oms.utils.RetryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${email.retry.count: 3}")
    private Long maxAttempts;

    @Value("${email.retry.delay: 5000}")
    private Long delay;


    @Override
    public ResponseEntity<EmailResponse> sendMail(EmailRequest emailRequest) throws Exception {
        final Context context = new Context();
        EmailResponse emailResponse = new EmailResponse();

        context.setVariable("message", emailRequest.getContent());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailRequest.getFrom());
            String[] emailArray = Arrays.stream(emailRequest.getRecipients().toArray()).toArray(String[]::new);
            helper.setTo(emailArray);
            helper.setSubject(emailRequest.getSubject());
            String body = templateEngine.process(emailRequest.getTemplateName(), context);
            helper.setText(body, true);
            RetryConfig config = RetryConfig.builder()
                    .maxAttempts(maxAttempts)
                    .retryOn(new HashSet<>(Arrays.asList(MailException.class)))
                    .delayConfig(
                            DelayConfig.builder()
                                    .delay(delay)
                                    .timeUnit(TimeUnit.MILLISECONDS)
                                    .build()
                    )
                    .build();
            RetryUtil.builder()
                    .build().executeWithRetry(Executors.callable(() -> javaMailSender.send(mimeMessage)), config);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw e;
        }

        emailResponse.setStatus(Message.SUCCESSFUL);
        return new ResponseEntity<>(emailResponse, HttpStatus.OK);
    }
}
