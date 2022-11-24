package school.sptech.ido.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.ido.application.controller.dto.Response.UsuarioDto;
import school.sptech.ido.application.controller.dto.UsuarioCadastroDto;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import school.sptech.ido.service.UsuarioService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioControllerTest {

    @Autowired
    UsuarioController usuarioController;

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    UsuarioService usuarioService;

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
        UsuarioCadastroDto usuarioCadastroDto = new UsuarioCadastroDto();
        usuarioCadastroDto.setApelido("USER");
        usuarioCadastroDto.setEmail("EMAIL");
        usuarioCadastroDto.setNascimento(LocalDate.MAX);
        usuarioCadastroDto.setSenha("123");
        usuarioCadastroDto.setTelefone("8888888888");

        when(
            usuarioRepository.save(new UsuarioEntity())
        ).thenReturn(new UsuarioEntity(usuarioCadastroDto));

        ResponseEntity<UsuarioDto> resposta = usuarioController.cadastrarUsuario(usuarioCadastroDto);

        assertNotNull(resposta.getBody());
        assertEquals(201, resposta.getStatusCodeValue());
    }


}