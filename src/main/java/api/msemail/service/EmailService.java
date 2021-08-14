package api.msemail.service;

import api.msemail.enums.SendStatus;
import api.msemail.models.EmailModel;
import api.msemail.respositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender javaMailSender;


    public EmailModel sendEmail(EmailModel emailSent) {

        emailSent.setSendDate(LocalDateTime.now());

        try{

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(emailSent.getEmailFrom());
            simpleMailMessage.setTo(emailSent.getEmailTo());
            simpleMailMessage.setSubject(emailSent.getSubject());
            simpleMailMessage.setText(emailSent.getBodyText());

            javaMailSender.send(simpleMailMessage);

            emailSent.setSendStatus(SendStatus.SEND);
        }catch (MailException mailException){
            emailSent.setSendStatus(SendStatus.ERROR);
            mailException.printStackTrace();
        }finally {
            return emailRepository.save(emailSent);
        }

    }
}
