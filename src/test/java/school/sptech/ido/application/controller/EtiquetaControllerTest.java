package school.sptech.ido.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.ido.application.controller.dto.Request.EtiquetaCadastroDto;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.domain.model.Acao;
import school.sptech.ido.domain.model.AcoesUsuario;
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
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<Void> resposta = etiquetaController.deletarEtiquetaPorId(idUsuario, idEtiqueta);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado limpar acoes com id usuario autenticado e acao encontrada devera retornar 200 sem corpo")
    void quandoAcionadoLimparAcoesComIdUsuarioAutenticadoEAcaoEncontradaDeveraRetornar200SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        etiquetaController.acoesUsuarios.add(new AcoesUsuario(idUsuario));

        ResponseEntity<Void> resposta = etiquetaController.limparAcoes(idUsuario);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado limpar acoes com id usuario autenticado e acao nao encontrada devera retornar 204 sem corpo")
    void quandoAcionadoLimparAcoesComIdUsuarioAutenticadoEAcaoNaoEncontradaDeveraRetornar204SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        etiquetaController.acoesUsuarios.clear();

        ResponseEntity<Void> resposta = etiquetaController.limparAcoes(idUsuario);

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado limpar acoes com id usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoLimparAcoesComIdUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<Void> resposta = etiquetaController.limparAcoes(idUsuario);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado desfazer ultima acao com usuario autenticado e com acoes encontradas devera retornar 200 sem corpo")
    void quandoAcionadoDesfazerUltimaAcaoComUsuarioAutenticadoEComAcoesEncontradaDeveraRetornar200SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(usuario));

        etiquetaController.acoesUsuarios.add(new AcoesUsuario(idUsuario));
        etiquetaController.acoesUsuarios.get(0).getAcoes().push(new Acao("criar", etiquetaDto));

        ResponseEntity<Void> resposta = etiquetaController.desfazerUltimaAcao(idUsuario);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado desfazer ultima acao com usuario autenticado e com acao invalida devera retornar 422 sem corpo")
    void quandoAcionadoDesfazerUltimaAcaoComUsuarioAutenticadoEcomAcaoInvalidaDeveraRetornar422SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(usuario));

        etiquetaController.acoesUsuarios.add(new AcoesUsuario(idUsuario));
        etiquetaController.acoesUsuarios.get(0).getAcoes().push(new Acao("ACAOINVALIDA", etiquetaDto));

        ResponseEntity<Void> resposta = etiquetaController.desfazerUltimaAcao(idUsuario);

        assertEquals(422, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado desfazer ultima acao com usuario autenticado e sem acoes devera retornar 422 sem corpo")
    void quandoAcionadoDesfazerUltimaAcaoComUsuarioAutenticadoESemAcoesDeveraRetornar422SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(usuario));

        etiquetaController.acoesUsuarios.clear();
        etiquetaController.acoesUsuarios.add(new AcoesUsuario(idUsuario));

        ResponseEntity<Void> resposta = etiquetaController.desfazerUltimaAcao(idUsuario);

        assertEquals(422, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado desfazer ultima acao com usuario autenticado e acoes nao encontradas do usuario devera retornar 204 sem corpo")
    void quandoAcionadoDesfazerUltimaAcaoComUsuarioAutenticadoEAcoesNaoEncontradasDoUsuarioDeveraRetornar204SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            usuarioRepository.findById(idUsuario)
        ).thenReturn(Optional.of(usuario));

        etiquetaController.acoesUsuarios.clear();
        etiquetaController.acoesUsuarios.add(new AcoesUsuario(2));

        ResponseEntity<Void> resposta = etiquetaController.desfazerUltimaAcao(idUsuario);

        assertEquals(204, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado desfazer ultima acao com usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoDesfazerUltimaAcaoComUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<Void> resposta = etiquetaController.desfazerUltimaAcao(idUsuario);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado salvar acao do usuario com usuario autenticado deve adicionar a acao e devera retornar 200 sem corpo")
    void quandoAcionadoSalvarAcaoDoUsuarioComUsuarioAutenticadoDeveAdicionarAcaoDeveraRetornar200SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        etiquetaController.acoesUsuarios.clear();
        etiquetaController.acoesUsuarios.add(new AcoesUsuario(idUsuario));

        ResponseEntity<Void> resposta = etiquetaController.salvarAcaoDoUsuario(idUsuario, new Acao("criar", etiquetaDto));

        assertEquals(200, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado salvar acao do usuario com usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoSalvarAcaoDoUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<Void> resposta = etiquetaController.salvarAcaoDoUsuario(idUsuario, new Acao("criar", etiquetaDto));

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado associar etiqueta por id tarefa com usuario autenticado, tarefa e etiqueta encontrada devera retornar 201 sem corpo")
    void quandoAcionadoAssociarEtiquetaPorIdTarefaComUsuarioAutenticadoTarefaEEtiquetaEncontradaDeveraRetornar201SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
            tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa)
        ).thenReturn(Optional.of(tarefaEntity));

        when(
            etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta)
        ).thenReturn(Optional.of(etiquetaEntity));

        when(
            etiquetaRepository.save(any(EtiquetaEntity.class))
        ).thenReturn(etiquetaEntity);

        ResponseEntity<Void> resposta = etiquetaController.associarEtiquetaPorIdTarefa(idUsuario, idTarefa, idEtiqueta);

        assertEquals(201, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado associar etiqueta por id tarefa com usuario autenticado, tarefa e etiqueta nao encontrada devera retornar 404 sem corpo")
    void quandoAcionadoAssociarEtiquetaPorIdTarefaComUsuarioAutenticadoTarefaEEtiquetaNaoEncontradaDeveraRetornar404SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa)
        ).thenReturn(Optional.of(tarefaEntity));

        when(
                etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta)
        ).thenReturn(Optional.empty());

        ResponseEntity<Void> resposta = etiquetaController.associarEtiquetaPorIdTarefa(idUsuario, idTarefa, idEtiqueta);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado associar etiqueta por id tarefa com usuario autenticado, tarefa nao encontrada devera retornar 404 sem corpo")
    void quandoAcionadoAssociarEtiquetaPorIdTarefaComUsuarioAutenticadoTarefaNaoEncontradaDeveraRetornar404SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa)
        ).thenReturn(Optional.empty());

        ResponseEntity<Void> resposta = etiquetaController.associarEtiquetaPorIdTarefa(idUsuario, idTarefa, idEtiqueta);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado associar etiqueta por id tarefa com usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoAssociarEtiquetaPorIdTarefaComUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<Void> resposta = etiquetaController.associarEtiquetaPorIdTarefa(idUsuario, idTarefa, idEtiqueta);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado desassociar etiqueta com usuario autenticado com tarefa e etiqueta encontrada devera retornar 200 sem corpo")
    void quandoAcionadoDesassociarEtiquetaComUsuarioAutenticadoComTarefaEEtiquetaEncontradaDeveraRetornar200SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa)
        ).thenReturn(Optional.of(tarefaEntity));

        when(
                etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta)
        ).thenReturn(Optional.of(etiquetaEntity));

        ResponseEntity<Void> resposta = etiquetaController.desassociarEtiquetaPorIdTarefa(idUsuario, idTarefa, idEtiqueta);

        assertEquals(200, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado desassociar etiqueta com usuario autenticado com a tarefa e etiqueta nao encontrada devera retornar 404 sem corpo")
    void quandoAcionadoDesassociarEtiquetaComUsuarioAutenticadoComTarefaEncontradaEEtiquetaNaoEncontradaDeveraRetornar404SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa)
        ).thenReturn(Optional.of(tarefaEntity));

        when(
                etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta)
        ).thenReturn(Optional.empty());

        ResponseEntity<Void> resposta = etiquetaController.desassociarEtiquetaPorIdTarefa(idUsuario, idTarefa, idEtiqueta);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado desassociar etiqueta com usuario autenticado com a tarefa nao encontrada devera retornar 404 sem corpo")
    void quandoAcionadoDesassociarEtiquetaComUsuarioAutenticadoComTarefaNaoEncontradaDeveraRetornar404SemCorpo(){
        when(
                usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(true);

        when(
                tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa)
        ).thenReturn(Optional.empty());

        ResponseEntity<Void> resposta = etiquetaController.desassociarEtiquetaPorIdTarefa(idUsuario, idTarefa, idEtiqueta);

        assertEquals(404, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

    @Test
    @DisplayName("Quando acionado desassociar etiqueta com usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoDesassociarEtiquetaComUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){
        when(
            usuarioController.isUsuarioAutenticado(idUsuario)
        ).thenReturn(false);

        ResponseEntity<Void> resposta = etiquetaController.desassociarEtiquetaPorIdTarefa(idUsuario, idTarefa, idEtiqueta);

        assertEquals(403, resposta.getStatusCodeValue());
        assertNull(resposta.getBody());
    }

}