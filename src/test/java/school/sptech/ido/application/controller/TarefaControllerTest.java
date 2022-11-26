package school.sptech.ido.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.SubTarefaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.service.UsuarioService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TarefaControllerTest {

    @Autowired
    TarefaController tarefaController;

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    TarefaRepository tarefaRepository;

    @MockBean
    SubTarefaRepository subTarefaRepository;

    @MockBean
    EtiquetaRepository etiquetaRepository;

    @MockBean
    UsuarioController usuarioController;

    @MockBean
    UsuarioService usuarioService;

    @MockBean
    SubTarefaController subTarefaController;




}