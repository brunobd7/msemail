package api.msemail.models;

import api.msemail.enums.SendStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_email")
public class EmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;

    @Column( columnDefinition = "TEXT")
    private String bodyText;

    private LocalDateTime sendDate;
    private SendStatus sendStatus;

}
