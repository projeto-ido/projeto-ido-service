package school.sptech.ido;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import school.sptech.ido.service.email.EmailService;

@SpringBootApplication
public class IdoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdoApplication.class, args);

        EmailService.enviarEmail();
    }

}
