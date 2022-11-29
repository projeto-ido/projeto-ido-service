package school.sptech.ido.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.ido.application.controller.dto.Response.UsuarioAtualizadoResDto;
import school.sptech.ido.application.controller.dto.Response.UsuarioDto;
import school.sptech.ido.application.controller.dto.UsuarioAtualizadoDto;
import school.sptech.ido.application.controller.dto.UsuarioCadastroDto;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import school.sptech.ido.service.UsuarioService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioControllerTest {

    @Autowired
    UsuarioController usuarioController;

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    UsuarioService usuarioService;

    UsuarioEntity novoUsuario = new UsuarioEntity(
            1,
            "NOME",
            "APELIDO",
            "EMAIL",
            "123",
            "88888888",
            "Bem vindo ao iDo! Você pode editar sua biografia se quiser!",
            LocalDate.MAX,
            null,
            "",
            0,
            false,
            false,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );

    @Test
    @DisplayName("Quando listar usuarios devera retornar 200 com corpo")
    void quandoListarUsuariosDeveRetornar200ComCorpo(){
        List<UsuarioEntity> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new UsuarioEntity());

        when(
            usuarioRepository.findAll()
        ).thenReturn(listaUsuarios);

        ResponseEntity<List<UsuarioDto>> resposta = usuarioController.listarUsuarios();

        assertNotNull(resposta.getBody());
        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando listar usuarios devera retornar 204 sem corpo")
    void quandoListarUsuariosDeveRetornar204SemCorpo(){
        List<UsuarioEntity> listaUsuarios = new ArrayList<>();

        when(
            usuarioRepository.findAll()
        ).thenReturn(listaUsuarios);

        ResponseEntity<List<UsuarioDto>> resposta = usuarioController.listarUsuarios();

        assertNull(resposta.getBody());
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando cadastrar usuario com corpo válido deverá retornar 201 com corpo do usuario")
    void quandoCadastrarUsuarioComCorpoValidoDeveraRetornar201ComCorpoDoUsuario(){
        UsuarioCadastroDto usuarioCadastroDto = new UsuarioCadastroDto("NOME","APELIDO",LocalDate.MAX,"EMAIL","123","8888888888");

        when(
            usuarioRepository.save(any(UsuarioEntity.class))
        ).thenReturn(novoUsuario);

        ResponseEntity<UsuarioDto> resposta = usuarioController.cadastrarUsuario(usuarioCadastroDto);

        assertNotNull(resposta.getBody());
        assertInstanceOf(UsuarioDto.class, resposta.getBody());
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando acionado atualizar usuario com Id Usuario inválido deverá retornar 404 sem corpo")
    void quandoAcionadoAtualizarUsuarioComIdUsuarioInvalidoDeveraRetornar404SemCorpo(){
        UsuarioAtualizadoDto usuarioAtualizadoDto = new UsuarioAtualizadoDto("NOME", "APELIDO", "BIO", null, "");
        Integer idUsuario = 1;

        when(
            usuarioRepository.existsById(idUsuario)
        ).thenReturn(false);

        ResponseEntity<UsuarioAtualizadoResDto> resposta = usuarioController.atualizarUsuario(idUsuario, usuarioAtualizadoDto);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado atualizar usuario com Id Usuario válido deverá retornar 200 com corpo")
    void quandoAcionadoAtualizarUsuarioComIdUsuarioValidoDeveraRetornar200ComCorpo(){
        UsuarioAtualizadoDto usuarioAtualizadoDto = new UsuarioAtualizadoDto("NOME", "APELIDO", "BIO", null, "");
        Integer idUsuario = 1;

        when(
            usuarioRepository.existsById(idUsuario)
        ).thenReturn(true);

        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(novoUsuario));

        when(
            usuarioRepository.save(any(UsuarioEntity.class))
        ).thenReturn(novoUsuario);

        ResponseEntity<UsuarioAtualizadoResDto> resposta = usuarioController.atualizarUsuario(idUsuario, usuarioAtualizadoDto);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
    }

}