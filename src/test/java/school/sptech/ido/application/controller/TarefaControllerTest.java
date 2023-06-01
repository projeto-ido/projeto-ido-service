package school.sptech.ido.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.ido.application.controller.dto.Request.TarefaAtualizacaoDto;
import school.sptech.ido.application.controller.dto.Request.TarefaCadastroDto;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.application.controller.dto.Response.SubTarefaDto;
import school.sptech.ido.application.controller.dto.Response.TarefaDto;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.SubTarefaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import school.sptech.ido.service.UsuarioService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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

    private final EtiquetaEntity etiquetaEntity = new EtiquetaEntity(
            10,
            "Trabalho",
            "#FAFAFA",
            usuario,
            new ArrayList<>()
    );

    private final TarefaEntity tarefaEntity = new TarefaEntity(
            123,
            "Ir as compras",
            "Preciso comprar novas roupas",
            false,
            LocalDateTime.of(2022, Month.DECEMBER, 1, 14,54),
            LocalDateTime.of(2022, Month.DECEMBER, 3, 14,54),
            LocalDateTime.of(2022, Month.DECEMBER, 1, 14,35),
            LocalDateTime.now(),
            true,
            true,
            List.of(),
            usuario,
            List.of(etiquetaEntity)
    );

    private final TarefaAtualizacaoDto tarefaAtualizacaoDto = new TarefaAtualizacaoDto(
            "Ir as compras",
            "Preciso comprar novas roupas",
            false,
            LocalDateTime.of(2022, Month.DECEMBER, 1, 14,54),
            LocalDateTime.of(2022, Month.DECEMBER, 3, 14,54),
            LocalDateTime.of(2022, Month.DECEMBER, 1, 14,35),
            true,
            true,
            List.of(),
            List.of()
    );

    private final SubTarefaEntity subTarefaEntity = new SubTarefaEntity(
        10,
        "SUB",
        false,
        tarefaEntity
    );


    @Test
    @DisplayName("Verificar se no metodo listarTarefasPorUsuario o usuario não está autenticado e deve retornar 403")
    void listarTarefaPorUsuarioUsuarioNaoAuntenticado() {
        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<List<TarefaDto>> response = tarefaController.listarTarefasPorIdUsuario(1);

        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Verificar se no metodo listarTarefasPorUsuario o usuário está autenticado " +
            "e a lista está vazia deve retornar 204")
    void listarTarefaPorUsuarioUsuarioAuntenticadoEListadeTarefasVazia() {
        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuario(1)
        ).thenReturn(List.of());

        ResponseEntity<List<TarefaDto>> response = tarefaController.listarTarefasPorIdUsuario(1);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Verificar se no metodo listarTarefasPorUsuario o usuário está autenticado " +
            "e a lista de tarefa existe deve retornar 200")
    void listarTarefaPorUsuarioUsuarioAuntenticadoEListadeTarefasExiste() {
        TarefaEntity tarefaEntity =
                new TarefaEntity(
                    1,
                    "",
                    "",
                    false,
                    LocalDateTime.MAX,
                    LocalDateTime.MAX,
                    LocalDateTime.MAX,
                    LocalDateTime.MAX,
                    true,
                    true,
                    new ArrayList<>(),
                    new UsuarioEntity(),
                    new ArrayList<>()
                );

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuario(1)
        ).thenReturn(List.of(tarefaEntity));

        ResponseEntity<List<TarefaDto>> response = tarefaController.listarTarefasPorIdUsuario(1);

        assertNotNull(response.getBody());
        assertInstanceOf(List.class, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Verificar se no metodo listarTarefasPorUsuario o usuario não está autenticado e deve retornar 204")
    void listarTarefasPorIdUsuarioOrdenadasNaoAuntenticado()
     {
        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<List<TarefaDto>> response = tarefaController.listarTarefasPorIdUsuarioOrdenadas(1);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Verificar se no metodo listarTarefasPorUsuarioOrdenadas o usuário está autenticado " +
            "e a lista de tarefa existe deve retornar 200")
    void listarTarefasPorIdUsuarioOrdenadasUsuarioAuntenticadoEListadeTarefasExiste() {
        TarefaEntity tarefaEntity =
                new TarefaEntity(
                        1,
                        "",
                        "",
                        false,
                        LocalDateTime.MAX,
                        LocalDateTime.MAX,
                        LocalDateTime.MAX,
                        LocalDateTime.MAX,
                        true,
                        true,
                        new ArrayList<>(),
                        new UsuarioEntity(),
                        new ArrayList<>()
                );

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuario(1)
        ).thenReturn(List.of(tarefaEntity));

        ResponseEntity<List<TarefaDto>> response = tarefaController.listarTarefasPorIdUsuarioOrdenadas(1);

        assertNotNull(response.getBody());
        assertInstanceOf(List.class, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
    @Test
    @DisplayName("Verificar se no metodo buscarPorIdTarefa o usuario não está autenticado e deve retornar 403")
    void buscarPorIdTarefaUsuarioNaoAuntenticado() {
        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<TarefaDto>response = tarefaController.buscarPorIdTarefa(1,2);

        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Verificar se no metodo listarTarefasPorUsuario o usuário está autenticado " +
            "e a lista está vazia deve retornar 404")
    void buscarPorIdTarefaUsuarioAuntenticadoEListadeTarefasVazia() {
        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuario(1)
        ).thenReturn(List.of());

        ResponseEntity<TarefaDto> response = tarefaController.buscarPorIdTarefa(1,2);

        assertEquals(404, response.getStatusCodeValue());
    }
    @Test
    @DisplayName("Verificar se no metodo listarTarefasPorUsuarioOrdenadas o usuário está autenticado " +
            "e a lista de tarefa existe deve retornar 200")
    void buscarPorIdTarefaUsuarioAuntenticadoEListadeTarefasExiste() {
        TarefaEntity tarefaEntity =
                new TarefaEntity(
                        1,
                        "",
                        "",
                        false,
                        LocalDateTime.MAX,
                        LocalDateTime.MAX,
                        LocalDateTime.MAX,
                        LocalDateTime.MAX,
                        true,
                        true,
                        new ArrayList<>(),
                        new UsuarioEntity(),
                        new ArrayList<>()
                );

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(1,4)
        ).thenReturn(Optional.of(tarefaEntity));

        ResponseEntity<TarefaDto> response = tarefaController.buscarPorIdTarefa(1,4);

        assertNotNull(response.getBody());
        assertInstanceOf(TarefaDto.class, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Verificar se no metodo listarTarefasPorUsuario o usuario não está autenticado e deve retornar 403")
    void salvarTarefaPorIdUsuarioUsuarioNaoAuntenticado() {
        TarefaCadastroDto tarefaCadastroDto = new TarefaCadastroDto();

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<TarefaDto> response = tarefaController.salvarTarefaPorIdUsuario(1,tarefaCadastroDto);

        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Verificar se no metodo salvarTarefaPorIdUsuario o usuario não está autenticado e deve retornar 403")
    void salvarTarefaPorIdUsuarioAutenticado() {
        TarefaCadastroDto tarefaCadastroDto = new TarefaCadastroDto();

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        ResponseEntity<TarefaDto> response = tarefaController.salvarTarefaPorIdUsuario(1,tarefaCadastroDto);

        assertEquals(401, response.getStatusCodeValue());
    }

    @Test
    @DisplayName(
        "Dado um usuário que não é nulo e está autenticado, " +
        "quando chamar salvarTarefaPorIdUsuario, entao deve retornar 201"
    )
    void salvarTarefaPorIdUsuarioAutenticadoRetorna201()
     {
        TarefaCadastroDto tarefaCadastroDto = new TarefaCadastroDto();
        UsuarioEntity usuario = new UsuarioEntity();
        TarefaEntity tarefaSalva = new TarefaEntity(
            1,
            "Tarefa teste",
            "Descricao teste",
            true,
            LocalDateTime.now().plusDays(1),
            LocalDateTime.now().plusDays(4),
            LocalDateTime.now(),
            LocalDateTime.now().plusDays(3),
            true,
            true,
            emptyList(),
            usuario,
            emptyList()
        );

        when(
            usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
            usuarioRepository.findById(anyInt())
        ).thenReturn(Optional.of(usuario));

        when(
            tarefaRepository.save(any(TarefaEntity.class))
        ).thenReturn(tarefaSalva);

        ResponseEntity<TarefaDto> response = tarefaController.salvarTarefaPorIdUsuario(1,tarefaCadastroDto);

        assertEquals(201, response.getStatusCodeValue());
    }
    @Test
    @DisplayName("Verificar se no metodo atualizarTarefaPorIdTarefa o usuario não está autenticado e deve retornar 403")
    void atualizarTarefaPorIdTarefaUsuarioNaoAuntenticado() {

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<TarefaDto> response = tarefaController.atualizarTarefaPorIdTarefa(1,1,tarefaAtualizacaoDto);

        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Verificar se no metodo atualizarTarefaPorIdTarefa o usuario está autenticado e tarefaEntity" +
            " não está presente deve retornar 404")
    void atualizarTarefaPorIdTarefaUsuarioAutenticadoETarefaEntityExiste() {

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        ResponseEntity<TarefaDto> response = tarefaController.atualizarTarefaPorIdTarefa(1,1,tarefaAtualizacaoDto);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName(
        "Dado um usuario autenticado e uma tarefa existente, " +
        "quando chamar atualizarTarefaPorIdTarefa, deve retornar 200"
    )
    void atualizarTarefaPorIdTarefaUsuarioAutenticadoETarefaExiste()
    {

        when(
            usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
            tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(), anyInt())
        ).thenReturn(Optional.of(tarefaEntity));

        when(
            subTarefaRepository.getSubtarefasDto(anyInt())
        ).thenReturn(List.of(new SubTarefaDto(
                subTarefaEntity
        )));

        when(
            etiquetaRepository.getEtiquetasDto(anyInt())
        ).thenReturn(List.of(
                new EtiquetaDto(
                    etiquetaEntity
                )
        ));

        when(
                etiquetaRepository.findById(anyInt())
        ).thenReturn(Optional.of(etiquetaEntity));

        when(
                subTarefaRepository.save(any(SubTarefaEntity.class))
        ).thenReturn(subTarefaEntity);

        when(
                tarefaRepository.save(any(TarefaEntity.class))
        ).thenReturn(tarefaEntity);

        ResponseEntity<TarefaDto> response = tarefaController.atualizarTarefaPorIdTarefa(
            1,
            1,
            tarefaAtualizacaoDto
        );

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Dado um usuario nao autenticado, quando chamar atualizarStatusPorId, entao deve retornar 403")
    void atualizarStatusPorIdUsuarioNaoAutenticado() {

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<Void> response = tarefaController.atualizarStatusPorIdTarefa(1,2);

        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Dado um usuario autenticado e tarefa não existe, quando chamar atualizarStatusPorId," +
            " então deve retornar 404")
    void atualizarStatusPorIdUsuarioAutenticadoTarefaNaoExiste() {

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        ResponseEntity<Void> response = tarefaController.atualizarStatusPorIdTarefa(1,2);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Dado um usuario autenticado e tarefa existe, quando chamar atualizarStatusPorId," +
            " então deve retornar 404")
    void atualizarStatusPorIdUsuarioAutenticadoTarefaExiste() {

        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(),anyInt())
        ).thenReturn(Optional.of(new TarefaEntity()));

        ResponseEntity<Void> response = tarefaController.atualizarStatusPorIdTarefa(1,2);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Dado um usuario nao autenticado, quando chamar deletarTarefaPorId, entao deve retornar 403")
    void deletarTarefaPorIdTarefaUsuarioNaoAutenticado() {

        when(
            usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(false);

        ResponseEntity<Void> response = tarefaController.deletarTarefaPorIdTarefa(1,2);

        assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    @DisplayName(
        "Dado um usuario autenticado e idTarefa não encontrado, " +
        "quando chamar deletarTarefaPorId, entao deve retornar 404"
    )
    void deletarTarefaPorIdTarefaUsuarioAutenticadoEIdTarefaNaoExiste() {
        when(
            usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
            tarefaRepository.existsById(anyInt())
        ).thenReturn(false);

        ResponseEntity<Void> response = tarefaController.deletarTarefaPorIdTarefa(1,1);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName(
        "Dado um usuario autenticado e idTarefa for encontrado, " +
        "quando chamar deletarTarefaPorId, entao deve retornar 200"
    )
    void deletarTarefaPorIdUsuarioAutenticadoEIdTarefaExiste() {
        when(
                usuarioController.isUsuarioAutenticado(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.existsById(anyInt())
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(anyInt(), anyInt())
        ).thenReturn(Optional.of(tarefaEntity));

        ResponseEntity<Void> response = tarefaController.deletarTarefaPorIdTarefa(1,1);

        assertEquals(200, response.getStatusCodeValue());
    }
}