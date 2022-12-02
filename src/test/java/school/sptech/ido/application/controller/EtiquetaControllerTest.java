package school.sptech.ido.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.ido.application.controller.dto.Request.EtiquetaCadastroDto;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class EtiquetaControllerTest {

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    EtiquetaRepository etiquetaRepository;

    @MockBean
    TarefaRepository tarefaRepository;

    @MockBean
    UsuarioController usuarioController;

    @Autowired
    EtiquetaController etiquetaController;

    private final Integer idUsuario = 1;
    private final Integer idTarefa = 1;

    private final Integer idEtiqueta = 1;

    private final UsuarioEntity usuario = new UsuarioEntity(
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

    private final EtiquetaEntity etiquetaEntity = new EtiquetaEntity(
        10,
        "Trabalho",
        "#FAFAFA",
        usuario,
        List.of(new TarefaEntity())
    );
    private final EtiquetaDto etiquetaDto = new EtiquetaDto(
        etiquetaEntity
    );

    private final EtiquetaCadastroDto etiquetaCadastroDto = new EtiquetaCadastroDto(
        "Titulo",
            "#FAFAFA"
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

    @Test
    @DisplayName("Quando acionado listar etiquetas com id usuario valido devera retornar 200 com corpo")
    void quandoAcionadoListarEtiquetasComIdUsuarioValidoDeveraRetornar200ComCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            etiquetaRepository.findByFkUsuario(idUsuario)
        ).thenReturn(List.of(new EtiquetaEntity()));

        ResponseEntity<List<EtiquetaDto>> resposta = etiquetaController.listarEtiquetas(idUsuario);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
        assertInstanceOf(List.class, resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado listar etiquetas com id usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoListarEtiquetasComIdUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<List<EtiquetaDto>> resposta = etiquetaController.listarEtiquetas(idUsuario);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado listar etiquetas com id usuario valido sem etiquetas devera retornar 204 sem corpo")
    void quandoAcionadoListarEtiquetasComIdUsuarioValidoSemEtiquetasDeveraRetornar204SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            etiquetaRepository.findByFkUsuario(idUsuario)
        ).thenReturn(List.of());

        ResponseEntity<List<EtiquetaDto>> resposta = etiquetaController.listarEtiquetas(idUsuario);

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado buscarPorIdTarefa com id usuario e id tarefa valido devera retornar 200 com corpo")
    void quandoAcionadoBuscarPorIdTarefaComIdUsuarioEIdTarefaValidoDeveraRetornar200ComCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            etiquetaRepository.getEtiquetasDto(idTarefa)
        ).thenReturn(List.of(new EtiquetaDto()));

        ResponseEntity<List<EtiquetaDto>> resposta = etiquetaController.buscarPorIdTarefa(idUsuario, idTarefa);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado buscarPorIdTarefa com id usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoBuscarPorIdTarefaComIdUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<List<EtiquetaDto>> resposta = etiquetaController.buscarPorIdTarefa(idUsuario, idTarefa);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado buscarPorIdTarefa com id usuario valido e id tarefa invalido devera retornar 204 sem corpo")
    void quandoAcionadoBuscarPorIdTarefaComIdUsuarioValidoEIdTarefaInvalidoDeveraRetornar204SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            etiquetaRepository.getEtiquetasDto(idTarefa)
        ).thenReturn(List.of());

        ResponseEntity<List<EtiquetaDto>> resposta = etiquetaController.buscarPorIdTarefa(idUsuario, idTarefa);

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado salvar etiqueta com id usuario valido devera retornar 201 com corpo")
    void quandoAcionadoSalvarEtiquetaComIdUsuarioValidoDeveraRetornar201ComCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(usuario));

        when(
            etiquetaRepository.save(any(EtiquetaEntity.class))
        ).thenReturn(etiquetaEntity);


        ResponseEntity<EtiquetaDto> resposta = etiquetaController.salvarEtiqueta(idUsuario, etiquetaDto);

        assertEquals(201, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado salvar etiqueta com id usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoSalvarEtiquetaComIdUsuarioInvalidoNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<EtiquetaDto> resposta = etiquetaController.salvarEtiqueta(idUsuario, etiquetaDto);

        assertNull(resposta.getBody());
        assertEquals(403, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("Quando acionado atualizar etiqueta com id usuario valido devera retornar 200 com corpo")
    void quandoAcionadoAtualizarEtiquetaComIdUsuarioValidoDeveraRetornar200ComCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta)
        ).thenReturn(Optional.of(etiquetaEntity));

        when(
            etiquetaRepository.save(any(EtiquetaEntity.class))
        ).thenReturn(etiquetaEntity);

        ResponseEntity<EtiquetaDto> resposta = etiquetaController.atualizarEtiqueta(idUsuario, idEtiqueta, etiquetaCadastroDto);

        assertNotNull(resposta.getBody());
        assertEquals(200, resposta.getStatusCodeValue());
    }


    @Test
    @DisplayName("Quando acionado atualizar etiqueta com id usuario valido devera retornar 404 sem corpo")
    void quandoAcionadoAtualizarEtiquetaComIdUsuarioValidoDeveraRetornarRetornar404SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta)
        ).thenReturn(Optional.empty());

        ResponseEntity<EtiquetaDto> resposta = etiquetaController.atualizarEtiqueta(idUsuario, idEtiqueta, etiquetaCadastroDto);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }


    @Test
    @DisplayName("Quando acionado atualizar etiqueta com id usuario invalido devera retornar 403 sem corpo")
    void quandoAcionadoAtualizarEtiquetaComIdUsuarioInvalidoDeveraRetornar403SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<EtiquetaDto> resposta = etiquetaController.atualizarEtiqueta(idUsuario, idEtiqueta, etiquetaCadastroDto);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado deletar etiqueta com id usuario autenticado e etiqueta encontrada devera retornar 200 sem corpo")
    void quandoAcionadoDeletarEtiquetaComIdUsuarioAutenticadoEEtiquetaEncontradaDeveraRetornar200SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta)
        ).thenReturn(Optional.of(etiquetaEntity));

        when(
            tarefaRepository.findByFkUsuario(idUsuario)
        ).thenReturn(List.of(tarefaEntity));

        when(
            tarefaRepository.save(any(TarefaEntity.class))
        ).thenReturn(tarefaEntity);

        ResponseEntity<Void> resposta = etiquetaController.deletarEtiquetaPorId(idUsuario, idEtiqueta);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado deletar etiqueta com id usuario autenticado e etiqueta nao encontrada devera retornar 404 sem corpo")
    void quandoAcionadoDeletarEtiquetaComIdUsuarioAutenticadoEEtiquetaNaoEncontradaDeveraRetornar404SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta)
        ).thenReturn(Optional.empty());

        ResponseEntity<Void> resposta = etiquetaController.deletarEtiquetaPorId(idUsuario, idEtiqueta);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado deletar etiqueta com id usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoDeletarEtiquetaComIdUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){


    }

    @Test
    @DisplayName("Quando acionado limpar acoes com id usuario autenticado e acao encontrada devera retornar 200 sem corpo")
    void quandoAcionadoLimparAcoesComIdUsuarioAutenticadoEAcaoEncontradaDeveraRetornar200SemCorpo(){


    }

    @Test
    @DisplayName("Quando acionado limpar acoes com id usuario autenticado e acao nao encontrada devera retornar 204 sem corpo")
    void quandoAcionadoLimparAcoesComIdUsuarioAutenticadoEAcaoNaoEncontradaDeveraRetornar204SemCorpo(){


    }

}