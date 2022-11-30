package school.sptech.ido.application.controller;

import org.apache.hadoop.shaded.org.checkerframework.checker.nullness.Opt;
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
import school.sptech.ido.application.controller.dto.UsuarioLoginDto;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import school.sptech.ido.service.UsuarioService;
import java.time.LocalDate;
import java.time.Month;
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

    private final UsuarioEntity novoUsuario = new UsuarioEntity(
            1,
            "NOME",
            "APELIDO",
            "EMAIL",
            "123",
            "88888888",
            "Bem vindo ao iDo! Você pode editar sua biografia se quiser!",
            LocalDate.MAX,
            new Byte[1],
            "",
            0,
            false,
            false,
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
    );

    private final Integer idUsuario = 1;
    private final Integer novoNivel = 2;

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

    @Test
    @DisplayName("Quando acionado atualizar nivel com Id Usuario válido deverá retornar 200 com corpo")
    void quandoAcionadoAtualizarNivelComIdUsuarioValidoDeveraRetornar200ComCorpo(){

        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(novoUsuario));

        when(
            usuarioRepository.save(any(UsuarioEntity.class))
        ).thenReturn(novoUsuario);

        ResponseEntity<UsuarioDto> resposta = usuarioController.atualizarNivel(idUsuario, novoNivel);
        UsuarioDto bodyResposta = resposta.getBody();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(novoNivel, bodyResposta.getNivel());
        assertNotNull(bodyResposta);
    }

    @Test
    @DisplayName("Quando acionado atualizar nivel com Id Usuario inválido deverá retornar 404 sem corpo")
    void quandoAcionadoAtualizarNivelComIdUsuarioInvalidoDeveraRetornar404SemCorpo(){

        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.empty());

        when(
            usuarioRepository.save(any(UsuarioEntity.class))
        ).thenReturn(novoUsuario);

        ResponseEntity<UsuarioDto> resposta = usuarioController.atualizarNivel(idUsuario, novoNivel);
        UsuarioDto bodyResposta = resposta.getBody();

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(bodyResposta);
    }

    @Test
    @DisplayName("Quando acionado deletar usuario com id usuario válido deverá retornar 200 sem corpo")
    void quandoAcionadoDeletarUsuarioComIdUsuarioValidoDeveraRetornar200SemCorpo(){

        when(
            usuarioRepository.existsById(idUsuario)
        ).thenReturn(true);

        ResponseEntity<Void> resposta = usuarioController.deletarUsuario(idUsuario);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado deletar usuario com id usuario inválido deverá retornar 404 sem corpo")
    void quandoAcionadoDeletarUsuarioComIdUsuarioInvalidoDeveraRetornar404SemCorpo(){

        when(
            usuarioRepository.existsById(idUsuario)
        ).thenReturn(false);

        ResponseEntity<Void> resposta = usuarioController.deletarUsuario(idUsuario);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado logar com usuario válido deverá retornar 200 com corpo")
    void quandoAcionadoLogarComUsuarioValidoDeveraRetornar200ComCorpo(){
        UsuarioLoginDto usuarioLoginDto = new UsuarioLoginDto("EMAIL", "123");

        when(
            usuarioRepository.findByEmailAndSenha("EMAIL", "123")
        ).thenReturn(Optional.of(novoUsuario));

        when(
            usuarioRepository.save(any(UsuarioEntity.class))
        ).thenReturn(novoUsuario);

        ResponseEntity<UsuarioDto> resposta = usuarioController.logar(usuarioLoginDto);
        UsuarioDto bodyResposta = resposta.getBody();

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(bodyResposta);
        assertEquals(true, bodyResposta.getAutenticado());
    }

    @Test
    @DisplayName("Quando acionado logar com usuario inválido deverá retornar 404 sem corpo")
    void quandoAcionadoLogarComUsuarioInvalidoDeveraRetornar404SemCorpo(){
        UsuarioLoginDto usuarioLoginDto = new UsuarioLoginDto("EMAIL", "123");

        when(
            usuarioRepository.findByEmailAndSenha("EMAIL", "123")
        ).thenReturn(Optional.empty());

        ResponseEntity<UsuarioDto> resposta = usuarioController.logar(usuarioLoginDto);
        UsuarioDto bodyResposta = resposta.getBody();

        assertEquals(401, resposta.getStatusCodeValue());
        assertNull(bodyResposta);
    }

    @Test
    @DisplayName("Quando acionado deslogar com usuario valido e autenticado devera retornar 200 sem corpo")
    void quandoAcionadoDeslogarComUsuarioValidoAutenticadoDeveraRetornar200SemCorpo(){
        UsuarioEntity usuarioDeslogar = novoUsuario;
        usuarioDeslogar.setAutenticado(true);

        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(usuarioDeslogar));

        when(
            usuarioRepository.save(any(UsuarioEntity.class))
        ).thenReturn(novoUsuario);

        ResponseEntity<Void> resposta = usuarioController.deslogar(idUsuario);

        assertNull(resposta.getBody());
        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando acionado deslogar com usuario valido e nao autenticado devera retornar 401 sem corpo")
    void quandoAcionadoDeslogarComUsuarioValidoNaoAutenticadoDeveraRetornar401SemCorpo(){
        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(novoUsuario));

        ResponseEntity<Void> resposta = usuarioController.deslogar(idUsuario);

        assertNull(resposta.getBody());
        assertEquals(401, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando acionado deslogar com usuario invalido devera retornar 404 sem corpo")
    void quandoAcionadoDeslogarComUsuarioInvalidoDeveraRetornar404SemCorpo(){
        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.empty());

        ResponseEntity<Void> resposta = usuarioController.deslogar(idUsuario);

        assertNull(resposta.getBody());
        assertEquals(404, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando acionado isUsuarioAutenticado com id usuario válido devera retornar o campo autenticado")
    void quandoAcionadoIsUsuarioAutenticadoComIdUsuarioValidoDeveraRetornarCampoAutenticado(){

        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(novoUsuario));

        Boolean resposta = usuarioController.isUsuarioAutenticado(idUsuario);

        assertNotNull(resposta);
        assertEquals(novoUsuario.getAutenticado(), resposta);
    }

    @Test
    @DisplayName("Quando acionado isUsuarioAutenticado com id usuario inválido devera retornar false")
    void quandoAcionadoIsUsuarioAutenticadoComIdUsuarioInvalidoDeveraRetornarFalse(){
        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.empty());

        Boolean resposta = usuarioController.isUsuarioAutenticado(idUsuario);

        assertNotNull(resposta);
        assertEquals(false, resposta);
    }

    @Test
    @DisplayName("Quando acionado getUsuarioDto com id usuario valido devera retornar um usuario")
    void quandoAcionadoGetUsuarioDtoComIdUsuarioValidoDeveraRetornarUmUsuario(){
        UsuarioDto usuarioDto =
            new UsuarioDto(
                1,
                "NOME",
                "APELIDO",
                "EMAIL",
                "SENHA",
                "BIO",
                LocalDate.of(2001, Month.JANUARY,10),
                new Byte[1],
                "img",
                0,
                false,
                false
            );

        when(
            usuarioRepository.getusuarioDto(idUsuario)
        ).thenReturn(Optional.of(usuarioDto));

        UsuarioDto resposta = usuarioController.getUsuarioDto(idUsuario);

        assertNotNull(resposta);
    }

    @Test
    @DisplayName("Quando acionado getUsuarioDto com id usuario invalido devera retornar null")
    void quandoAcionadoGetUsuarioDtoComIdUsuarioInvalidoDeveraRetornarNull(){
        when(
            usuarioRepository.getusuarioDto(idUsuario)
        ).thenReturn(Optional.empty());

        UsuarioDto resposta = usuarioController.getUsuarioDto(idUsuario);

        assertNull(resposta);
    }

//    @Test
//    @DisplayName("Quando acionado desabilitarNotificacao com id usuario valido devera retornar 200 sem corpo")
//    void quandoAcionadoDesabilitarNotificacaoComIdUsuarioValidoDeveraRetornar200SemCorpo(){
//        when(
//            usuarioService.removerSubject(idUsuario)
//        ).thenReturn()
//
//
//
//    }
//
//    @Test
//    @DisplayName("Quando acionado desabilitarNotificacao com id usuario invalido devera 404 sem corpo")

}