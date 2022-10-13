package school.sptech.ido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import school.sptech.ido.repository.UsuarioRepository;
import school.sptech.ido.service.UsuarioService;

@SpringBootApplication
public class IdoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdoApplication.class, args);

        UsuarioService usuarioService = new UsuarioService();


    }

}
