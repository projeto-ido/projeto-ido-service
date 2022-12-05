package school.sptech.ido.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.ido.application.controller.dto.ConquistaDto;
import school.sptech.ido.resources.repository.ConquistaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.ConquistaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

    private final List<ConquistaEntity> listaConquistas = new ArrayList<>();

    private final UsuarioEntity usuario = new UsuarioEntity(
            1,
            "NOME",
            "APELIDO",
            "EMAIL",
            "123",
            "88888888",
            "Bem vindo ao iDo! Você pode editar sua biografia se quiser!",
            LocalDate.MAX,
            new byte[1],
            new byte[1],
            0,
            false,
            false,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );

    private final ConquistaEntity conquistaEntity = new ConquistaEntity(
        1,
            "Conquista",
            "Descricao",
            usuario
    );

    private final ConquistaDto conquistaDto = new ConquistaDto(
        conquistaEntity
    );

    private final Integer idUsuario = 1;

    @Test
    @DisplayName("Quando acionado listar conquistas com usuario autenticado e com conquistas devera retornar 200 com corpo")
    void quandoAcionadoListarConquistasComUsuarioAutenticadoComConquistasDeveraRetornar200ComCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        listaConquistas.add(conquistaEntity);

        when(
                conquistaRepository.findByIdUsuario(idUsuario)
        ).thenReturn(listaConquistas);


        ResponseEntity<List<ConquistaEntity>> resposta = conquistaController.listarConquistas(idUsuario);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        listaConquistas.clear();
    }

    @Test
    @DisplayName("Quando acionado listar conquistas com usuario autenticado sem conquistas devera retornar 204 sem corpo")
    void quandoAcionadoListarConquistasComUsuarioAutenticadoSemConquistasDeveraRetornar204SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        listaConquistas.clear();
        when(
                conquistaRepository.findByIdUsuario(idUsuario)
        ).thenReturn(listaConquistas);

        ResponseEntity<List<ConquistaEntity>> resposta = conquistaController.listarConquistas(idUsuario);

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado listar conquistas com usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoListarConquistasComUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<List<ConquistaEntity>> resposta = conquistaController.listarConquistas(idUsuario);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado salvar conquistas com usuario autenticado devera retornar 201 com corpo")
    void quandoAcionadoSalvarConquistaComUsuarioAutenticadoDeveraRetornar201ComCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(usuario));

        when(
                conquistaRepository.save(any(ConquistaEntity.class))
        ).thenReturn(conquistaEntity);

        ResponseEntity<ConquistaEntity> resposta = conquistaController.salvarConquista(idUsuario, conquistaDto);

        assertEquals(201, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado salvar conquistas com usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoSalvarConquistasComUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<ConquistaEntity> resposta = conquistaController.salvarConquista(idUsuario, conquistaDto);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }
}