package school.sptech.ido.application.controller;

import org.apache.spark.internal.config.R;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.ido.application.controller.dto.Request.SubTarefaCadastroDto;
import school.sptech.ido.application.controller.dto.Response.SubTarefaDto;
import school.sptech.ido.resources.repository.SubTarefaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class SubTarefaControllerTest {

    @Autowired
    SubTarefaController subTarefaController;

    @MockBean
    TarefaRepository tarefaRepository;

    @MockBean
    SubTarefaRepository subTarefaRepository;

    @MockBean
    UsuarioController usuarioController;

    @Test
    @DisplayName("Quando chamar buscarPorIdTarefa e o usuário não tiver autenticado deve retornar 403")
    void quandoUsuarioNaoAutenticadoRetorna403() {
        when(
               usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<List<SubTarefaDto>> response = subTarefaController.buscarPorIdTarefa(1, 5);

        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando chamar buscarPorIdTarefa e lista de subTarefa vier vazia deve retornar 204")
    void quandoListaVaziaRetorna204() {

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                subTarefaRepository.findAllByIdTarefa(anyInt())
        ).thenReturn(new ArrayList<>());

        ResponseEntity<List<SubTarefaDto>> response = subTarefaController.buscarPorIdTarefa(1, 5);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando chamar buscarPorIdTarefa e idUsuario for valido e lista não vier vazia deve retornar 200")
    void quandoIdValidoListaNaoVaziaDeveRetornar200() {
        List<SubTarefaEntity> subTarefas = List.of(new SubTarefaEntity());

        when(
            usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
            subTarefaRepository.findAllByIdTarefa(anyInt())
        ).thenReturn(subTarefas);

        ResponseEntity<List<SubTarefaDto>> response = subTarefaController.buscarPorIdTarefa(1, 5);

        assertNotNull(response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando chamar salvarSubTarefaPorIdTarefa e usuario não estiver autenticado deve retornar 403")
    void quandoSalvarSubTarefaUsuarioNaoAutenticado() {
        SubTarefaCadastroDto subTarefaCadastroDto = new SubTarefaCadastroDto();
        subTarefaCadastroDto.setStatus(true);
        subTarefaCadastroDto.setTitulo("licao de geografia");

        when(
            usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<SubTarefaDto> response = subTarefaController.salvarSubTarefaPorIdTarefa(
            1,
            5,
            subTarefaCadastroDto
        );

        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando chamar salvarSubTarefaPorIdTarefa e a tarefa vier nula deve retornar 404")
    void quandoSalvarSubTarefaUsuarioTarefaForNull() {
        SubTarefaCadastroDto subTarefaCadastroDto = new SubTarefaCadastroDto();
        subTarefaCadastroDto.setStatus(true);
        subTarefaCadastroDto.setTitulo("licao de geografia");
        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(),anyInt())
        ).thenReturn(Optional.empty());

        ResponseEntity<SubTarefaDto> response = subTarefaController.salvarSubTarefaPorIdTarefa(
                4,
                8,
                subTarefaCadastroDto
        );

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName(
        "Quando chamar salvarSubTarefaPorIdtarefa e o usuário estiver autenticado e a Tarefa não for nula retorna 201"
    )
    void quandochamarSalvarSubTarefaPorIdtarefaUsuarioAutenticadoeTarefaNaoForNula() {
        Optional<TarefaEntity> tarefaEntity = Optional.of(new TarefaEntity());
        tarefaEntity.get().setIdTarefa(1);
        tarefaEntity.get().setTitulo("Tarefa da faculdade");

        SubTarefaCadastroDto subTarefaCadastroDto = new SubTarefaCadastroDto();
        subTarefaCadastroDto.setStatus(true);
        subTarefaCadastroDto.setTitulo("licao de geografia");

        when(
            usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
            tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(), anyInt())
        ).thenReturn(tarefaEntity);

        ResponseEntity<SubTarefaDto> response = subTarefaController.salvarSubTarefaPorIdTarefa(
                4,
                7,
                subTarefaCadastroDto
        );

        assertEquals(201, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando chamar o metodo atualizarSubTarefaPorIdTarefa deve verificar se o usuario não estiver autenticado")
    void atualizarSubTarefaPorIdTarefaVerficarSeUsuarioNaoEstaAutenticado(){

        SubTarefaDto subTarefaDto = new SubTarefaDto();
        subTarefaDto.setStatus(true);
        subTarefaDto.setTitulo("licao de geografia");

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<SubTarefaEntity> response = subTarefaController.atualizarSubTarefaPorIdTarefa(
                1,
                5,
                8,
                subTarefaDto);

        assertEquals(403, response.getStatusCodeValue());

    }

    @Test
    @DisplayName("Quando chamar o metodo atualizarSubTarefaPorIdTarefa deve verificar se" +
            " o usuario estiver autenticado e a tarefa for nula ")
    void atualizarSubTarefaPorIdVerificarUsuarioAutenticadoETarefaNula(){

        SubTarefaDto subTarefaDto = new SubTarefaDto();
        subTarefaDto.setStatus(true);
        subTarefaDto.setTitulo("licao de geografia");

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(),anyInt())
        ).thenReturn(Optional.empty());

        ResponseEntity<SubTarefaEntity> response = subTarefaController.atualizarSubTarefaPorIdTarefa(
                1,
                2,
                4,
                subTarefaDto
        );

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName(
            "Quando chamar o metodo atualizarSubTarefaPorIdTarefa deve verificar se" +
                    " o usuario estiver autenticado e a tarefa existe e subTarefa for nula deve retornar 404 "
    )
    void  atualizarSubTarefaPorIdVerificarUsuarioAutenticadoTarefaExisteESubTarefaNula(){

        SubTarefaDto subTarefaDto = new SubTarefaDto();
        subTarefaDto.setStatus(true);
        subTarefaDto.setTitulo("licao de geografia");

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(),anyInt())
        ).thenReturn(Optional.of(new TarefaEntity()));

        when(
                subTarefaRepository.existsById(1)
        ).thenReturn(false);

        ResponseEntity<SubTarefaEntity> response = subTarefaController.atualizarSubTarefaPorIdTarefa(
                1,
                2,
                4,
                subTarefaDto
        );

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName(
            "Quando chamar o metodo atualizarSubTarefaPorIdTarefa deve verificar se" +
                    " o usuario estiver autenticado e a tarefa e subTarefa existem deve retornar 200"
    )
    void  atualizarSubTarefaPorIdVerificarUsuarioAutenticadoESubTarefaETarefaExiste(){

        SubTarefaDto subTarefaDto = new SubTarefaDto();
        subTarefaDto.setStatus(true);
        subTarefaDto.setTitulo("licao de geografia");

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(),anyInt())
        ).thenReturn(Optional.of(new TarefaEntity()));

        when(
                subTarefaRepository.existsById(anyInt())
        ).thenReturn(true);

        ResponseEntity<SubTarefaEntity> response = subTarefaController.atualizarSubTarefaPorIdTarefa(
                1,
                2,
                4,
                subTarefaDto
        );

        assertEquals(200, response.getStatusCodeValue());
    }


    @Test
    @DisplayName("Quando chamar o metodo deletarSubTarefaPorIdTarefa deve verificar se " +
            "o usuario não estiver autenticado e retornar 403")
    void deletarSubTarefaPorIdTarefaUsuarioNaoAutenticado(){

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<SubTarefaEntity> response = subTarefaController.deletarSubTarefaPorIdTarefa(
                1,
                5,
                8);

        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando chamar o metodo deletarSubTarefaPorIdTarefa  deve verificar se" +
            " o usuario estiver autenticado e a tarefa for nula  e retornar 404")
    void deletarSubTarefaPorIdTarefaVerificarUsuarioAutenticadoETarefaNula(){

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(),anyInt())
        ).thenReturn(Optional.empty());

        ResponseEntity<SubTarefaEntity> response = subTarefaController.deletarSubTarefaPorIdTarefa(
                1,
                2,
                4
        );

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName(
            "Quando chamar o metodo deletarSubTarefaPorIdTarefa deve verificar se" +
                    " o usuario estiver autenticado e a tarefa existe e subTarefa for nula deve retornar 404"
    )
    void deletarSubTarefaPorIdTarefaVerificarUsuarioAutenticadoTarefaExisteESubTarefaNula(){

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(),anyInt())
        ).thenReturn(Optional.of(new TarefaEntity()));

        when(
                subTarefaRepository.existsById(1)
        ).thenReturn(false);

        ResponseEntity<SubTarefaEntity> response = subTarefaController.deletarSubTarefaPorIdTarefa(
                1,
                2,
                4

        );

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName(
            "Quando chamar o metodo deletarSubTarefaPorIdTarefa deve verificar se" +
                    " o usuario estiver autenticado e a tarefa e subTarefa existem deve retornar 200"
    )
    void  deletarSubTarefaPorIdTarefaVerificarUsuarioAutenticadoESubTarefaETarefaExiste(){

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(),anyInt())
        ).thenReturn(Optional.of(new TarefaEntity()));

        when(
                subTarefaRepository.existsById(anyInt())
        ).thenReturn(true);

        ResponseEntity<SubTarefaEntity> response = subTarefaController.deletarSubTarefaPorIdTarefa(
                1,
                2,
                4
        );

        assertEquals(200, response.getStatusCodeValue());
    }
}