package school.sptech.ido.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import school.sptech.ido.resources.repository.ConquistaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;

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