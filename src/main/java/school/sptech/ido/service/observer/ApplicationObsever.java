package school.sptech.ido.service.observer;

import org.apache.hadoop.shaded.org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import school.sptech.ido.service.email.EmailService;

import javax.mail.MessagingException;
import javax.validation.constraints.Email;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

//@Component
public class ApplicationObsever {

    public void notificarPorEmail(LocalDate dataTarefa){
        EmailService.enviarEmail();

    };
}
