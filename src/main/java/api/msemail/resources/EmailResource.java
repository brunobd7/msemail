package api.msemail.resources;

import api.msemail.dtos.EmailDto;
import api.msemail.models.EmailModel;
import api.msemail.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailResource {

    @Autowired
    EmailService emailService;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailModel> sendingEmail(@Valid @RequestBody EmailDto emailDto){

        EmailModel emailSent = new EmailModel();
        BeanUtils.copyProperties(emailDto,emailSent);

        emailService.sendEmail(emailSent);

        return ResponseEntity.status(HttpStatus.CREATED).body(emailSent);

    }

}
