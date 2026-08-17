package com.nikolas.app.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendTextEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    public void sendHtmlEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        javaMailSender.send(message);
    }

    public void sendWeirdHtmlEmail(String from, String to, String[] cc, String subject, String body, Date sent) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setCc(cc);
        helper.setSubject(subject);
        helper.setText(body, true);
        helper.setSentDate(sent);
        javaMailSender.send(message);
    }

    public void sendHtmlEmailInlineImages(String to, String subject, String body, String[] imagePaths) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setTo(to);
        helper.setSubject(subject);

        helper.setText(body, true);

        for (int i=1; i<=imagePaths.length; i++)
            helper.addInline("image" + i, new ClassPathResource(imagePaths[i-1]));

        javaMailSender.send(message);
    }

    public void sendHtmlEmailWithAttachments(String to, String subject, String body, File[] files) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);

        for (var file: files)
            helper.addAttachment(file.getName(), file);

        javaMailSender.send(message);
    }

    @Async
    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
            System.out.println("I enjoyed sleeping");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}