package school.sptech.ido.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.ido.application.controller.dto.DiaSemana;
import school.sptech.ido.application.controller.dto.Response.*;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PerfilControllerTest {

    @MockBean
    UsuarioController usuarioController;

    @Autowired
    PerfilController perfilController;

    @MockBean
    TarefaRepository tarefaRepository;

    @MockBean
    EtiquetaRepository etiquetaRepository;

    @MockBean
    Data data;

    private final Integer idUsuario = 1;

    protected final List<EtiquetaDto> listaEtiquetasDto = new ArrayList<EtiquetaDto>(
            Arrays.asList(
                new EtiquetaDto(1, "TITULO", "#FAFAFA"),
                new EtiquetaDto(2, "TITULO 2", "#FFFFF")
            )
    );

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

    protected final List<TarefaEtiquetasDto> listaTarefaEtiquetasDto = new ArrayList<TarefaEtiquetasDto>(
            Arrays.asList(new TarefaEtiquetasDto(tarefaEntity))
    );

    protected final List<TarefaTimeLine> listaTarefaTimeline = new ArrayList<TarefaTimeLine>(
            Arrays.asList(new TarefaTimeLine(tarefaEntity))
    );

    @Test
    @DisplayName("Quando acionado quantidade de etiquetas usadas com usuario autenticado devera retornar 200 com corpo")
    void quandoAcionadoQuantidadeEtiquetasUsadasComUsuarioAutenticadoDeveraRetornar200ComCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                etiquetaRepository.findAllEtiquetasDto()
        ).thenReturn(listaEtiquetasDto);

        when(
                tarefaRepository.getTarefaEtiquetaDto()
        ).thenReturn(listaTarefaEtiquetasDto);

        ResponseEntity<List<QtdEtiquetasTarefaDto>> resposta = perfilController.qtdEtiquetasUsadas(idUsuario);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado quantidade de etiquetas usadas com usuario autenticado sem tarefas ou etiquetas devera retornar 204 sem corpo")
    void quandoAcionadoQuantidadeEtiquetasUsadasComUsuarioAutenticadoSemTarefasOuEtiquetasDeveraRetornar204SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                etiquetaRepository.findAllEtiquetasDto()
        ).thenReturn(listaEtiquetasDto);

        when(
                tarefaRepository.getTarefaEtiquetaDto()
        ).thenReturn(new ArrayList<>());

        ResponseEntity<List<QtdEtiquetasTarefaDto>> resposta = perfilController.qtdEtiquetasUsadas(idUsuario);

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado quantidade de etiquetas usadas com usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoQuantidadeEtiquetasUsadasComUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<List<QtdEtiquetasTarefaDto>> resposta = perfilController.qtdEtiquetasUsadas(idUsuario);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado listar tarefas timeline com usuario autenticado e tarefas devera retornar 200 com corpo")
    void quandoAcionadoListarTarefasTimelineComUsuarioAutenticadoETarefasDeveraRetornar200ComCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                tarefaRepository.getTarefasTimeLine()
        ).thenReturn(listaTarefaTimeline);

        ResponseEntity<List<TarefaTimeLine>> resposta = perfilController.listaTarefasTimeLine(idUsuario);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado listar tarefas timeline com usuario autenticado e sem tarefas devera retornar 204 sem corpo")
    void quandoAcionadoListarTarefasTimelineComUsuarioAutenticadoESemTarefasDeveraRetornar204SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                tarefaRepository.getTarefasTimeLine()
        ).thenReturn(new ArrayList<>());

        ResponseEntity<List<TarefaTimeLine>> resposta = perfilController.listaTarefasTimeLine(idUsuario);

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado listar tarefas timeline com usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoListarTarefasTimelineComUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<List<TarefaTimeLine>> resposta = perfilController.listaTarefasTimeLine(idUsuario);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado grafico semanal com usuario autenticado devera retornar 200 com corpo")
    void quandoAcionadoGraficoSemanalComUsuarioAutenticadoDeveraRetornar200ComCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        LocalDate diaSemana = LocalDate.of(2022, Month.DECEMBER, 10);


        when(
                tarefaRepository.getQtdTarefasConcluidasNoDia(diaSemana, idUsuario)
        ).thenReturn(1L);

        when(
            data.obterDiaAtual()
        ).thenReturn(diaSemana);

        ResponseEntity<List<DiaSemana>> resposta = perfilController.graficoSemanal(idUsuario);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado grafico semanal com usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoGraficoSemanalComUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<List<DiaSemana>> resposta = perfilController.graficoSemanal(idUsuario);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado info adicionais perfil com usuario autenticado devera retornar 200 com corpo")
    void quandoAcionadoInfoAdicionaisPerfilComUsuarioAutenticadoDeveraRetornar200ComCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                tarefaRepository.getTotalTarefasPorUsuario(idUsuario)
        ).thenReturn(2L);

        when(
                tarefaRepository.getQtdTarefasPendentesPorUsuario(idUsuario)
        ).thenReturn(2L);

        when(
                tarefaRepository.getQtdTarefasConcluidasPorUsuario(idUsuario)
        ).thenReturn(1L);

        ResponseEntity<List<InfoAdicionaisPerfilDto>> resposta = perfilController.infoAdicionaisPerfil(idUsuario);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNotNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado info adicionais perfil com usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoInfoAdiciionaisPerfilComUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<List<InfoAdicionaisPerfilDto>> resposta = perfilController.infoAdicionaisPerfil(idUsuario);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

}