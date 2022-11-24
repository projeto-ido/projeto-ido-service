package school.sptech.ido.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import school.sptech.ido.resources.repository.ConquistaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConquistaControllerTest {

    @MockBean
    private ConquistaRepository conquistaRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private UsuarioController usuarioController;

    @Autowired
    ConquistaController conquistaController;

    //com o usuario existindo retorna lista
    //se o usuario não existir
}