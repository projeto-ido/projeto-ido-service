package school.sptech.ido.service.observer;

import org.apache.hadoop.shaded.org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import school.sptech.ido.service.email.EmailService;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

@Component
public class ApplicationObsever {

    @Autowired
    private EmailService emailService;

    public void notificarPorEmail(LocalDate dataTarefa){
        try {
            emailService.enviarEmail();
        } catch (MessagingException error) {
            System.out.println("Erro ao decodificar a mensagem");
            error.printStackTrace();
        } catch (UnsupportedEncodingException error) {
            System.out.println("Erro ao enviar o email");
            error.printStackTrace();
        }

    };
}
