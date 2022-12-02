package school.sptech.ido.application.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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


    }

    @Test
    @DisplayName("Quando acionado listar etiquetas com id usuario valido sem etiquetas devera retornar 204 sem corpo")
    void quandoAcionadoListarEtiquetasComIdUsuarioValidoSemEtiquetasDeveraRetornar204SemCorpo(){

    }

    @Test
    @DisplayName("Quando acionado buscarPorIdTarefa com id usuario e id tarefa valido devera retornar 200 com corpo")
    void quandoAcionadoBuscarPorIdTarefaComIdUsuarioEIdTarefaValidoDeveraRetornar200ComCorpo(){

    }

    @Test
    @DisplayName("Quando acionado buscarPorIdTarefa com id usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoBuscarPorIdTarefaComIdUsuarioNaoAutenticadoDeveraRetornar403SemCorpo(){


    }

    @Test
    @DisplayName("Quando acionado buscarPorIdTarefa com id usuario valido e id tarefa invalido devera retornar 204 sem corpo")
    void quandoAcionadoBuscarPorIdTarefaComIdUsuarioValidoEIdTarefaInvalidoDeveraRetornar204SemCorpo(){


    }

    @Test
    @DisplayName("Quando acionado salvar etiqueta com id usuario valido devera retornar 201 com corpo")
    void quandoAcionadoSalvarEtiquetaComIdUsuarioValidoDeveraRetornar201ComCorpo(){


    }

    @Test
    @DisplayName("Quando acionado salvar etiqueta com id usuario nao autenticado devera retornar 403 sem corpo")
    void quandoAcionadoSalvarEtiquetaComIdUsuarioInvalidoNaoAutenticadoDeveraRetornar403SemCorpo(){


    }

    @Test
    @DisplayName("Quando acionado salvar etiqueta com id usuario invalido devera retornar 404 sem corpo")
    void quandoAcionadoSalvarEtiquetaComIdUsuarioInvalidoDeveraRetornar404SemCorpo(){


    }

    @Test
    @DisplayName("Quando acionado atualizar etiqueta com id usuario valido devera retornar 200 com corpo")
    void quandoAcionadoAtualizarEtiquetaComIdUsuarioValidoDeveraRetornar200ComCorpo(){


    }


    @Test
    @DisplayName("Quando acionado atualizar etiqueta com id usuario valido devera retornar 404 sem corpo")
    void quandoAcionadoAtualizarEtiquetaComIdUsuarioValidoDeveraRetornarRetornar404SemCorpo(){


    }


    @Test
    @DisplayName("Quando acionado atualizar etiqueta com id usuario invalido devera retornar 403 sem corpo")
    void quandoAcionadoAtualizarEtiquetaComIdUsuarioInvalidoDeveraRetornar403SemCorpo(){



    }

    @Test
    @DisplayName("Quando acionado deletar etiqueta com id usuario autenticado e etiqueta encontrada devera retornar 200 sem corpo")
    void quandoAcionadoDeletarEtiquetaComIdUsuarioAutenticadoEEtiquetaEncontradaDeveraRetornar200SemCorpo(){




    }

    @Test
    @DisplayName("Quando acionado deletar etiqueta com id usuario autenticado e etiqueta nao encontrada devera retornar 404 sem corpo")
    void quandoAcionadoDeletarEtiquetaComIdUsuarioAutenticadoEEtiquetaNaoEncontradaDeveraRetornar404SemCorpo(){

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