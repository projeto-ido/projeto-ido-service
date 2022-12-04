package school.sptech.ido.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.ido.application.controller.dto.DiaSemana;
import school.sptech.ido.application.controller.dto.Response.*;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Perfil", description = "Responsavel por administrar a pagina de perfil")
@RestController
public class PerfilController {


    @Autowired
    UsuarioController usuarioController;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

    @Autowired
    Data data;

    @GetMapping("/usuarios/perfil/grafico-etiqueta/{idUsuario}")
    public ResponseEntity<List<QtdEtiquetasTarefaDto>> qtdEtiquetasUsadas(@PathVariable Integer idUsuario){

        Boolean isAutentico = usuarioController.isUsuarioAutenticado(idUsuario);

        if (isAutentico){

            List<EtiquetaDto> etiquetaDtos = etiquetaRepository.findAllEtiquetasDto();

            List<TarefaEtiquetasDto> tarefaEtiquetaDto = tarefaRepository.getTarefaEtiquetaDto();

            if (tarefaEtiquetaDto.isEmpty() || etiquetaDtos.isEmpty())
                return ResponseEntity.noContent().build();

            List<QtdEtiquetasTarefaDto> listaQtdEtiquetasTarefa = new ArrayList();

            for (EtiquetaDto etiqueta : etiquetaDtos) {
                int qtdEtiqueta = 0;

                for (TarefaEtiquetasDto tarefa : tarefaEtiquetaDto) {
                    if (etiqueta.getTitulo().equals(tarefa.getEtiqueta1())) {
                        qtdEtiqueta += 1;
                    } else if (etiqueta.getTitulo().equals(tarefa.getEtiqueta2())) {
                        qtdEtiqueta += 1;
                    }
                }

                QtdEtiquetasTarefaDto qtdEtiquetasTarefaDto =
                        new QtdEtiquetasTarefaDto(qtdEtiqueta, etiqueta.getTitulo());

                listaQtdEtiquetasTarefa.add(qtdEtiquetasTarefaDto);

            }

            return ResponseEntity.ok().body(listaQtdEtiquetasTarefa);
        }

        return ResponseEntity.status(403).build();
    }

    @GetMapping("/usuarios/perfil/time-line/{idUsuario}")
    public ResponseEntity<List<TarefaTimeLine>> listaTarefasTimeLine(@PathVariable Integer idUsuario){

        Boolean isAutentico = usuarioController.isUsuarioAutenticado(idUsuario);

        if (isAutentico){

            List<TarefaTimeLine> tarefaTimeLines = tarefaRepository.getTarefasTimeLine();

            if (tarefaTimeLines.isEmpty())
                return ResponseEntity.noContent().build();

            return ResponseEntity.ok().body(tarefaTimeLines);
        }

        return ResponseEntity.status(403).build();
    }

    @GetMapping("/usuarios/perfil/semanal/{idUsuario}")
    public ResponseEntity<List<DiaSemana>> graficoSemanal(@PathVariable Integer idUsuario){

        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);

        if (isAutenticado){
            LocalDate diaSemana = data.obterDiaAtual();

            Integer diasAnteriores = 0;

            List<DiaSemana> semana = new ArrayList<>();

            for (int i = 0; i < 7; i++) {
                Long qtdTarefasConcluidas = tarefaRepository.getQtdTarefasConcluidasNoDia( diaSemana, idUsuario);

                DiaSemana dia = new DiaSemana(diaSemana, qtdTarefasConcluidas);

                semana.add(dia);

                diaSemana = diaSemana.minusDays(++diasAnteriores);

            }

            return ResponseEntity.ok().body(semana);

        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/usuarios/perfil/info-adicionais/{idUsuario}")
    public ResponseEntity<List<InfoAdicionaisPerfilDto>> infoAdicionaisPerfil(@PathVariable Integer idUsuario){

        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);

        if (isAutenticado){
            List<InfoAdicionaisPerfilDto> listaInfoAdicional = new ArrayList<>();

            Long qtdTarefas = tarefaRepository.getTotalTarefasPorUsuario(idUsuario);

            Long qtdTarefasPendentes = tarefaRepository.getQtdTarefasPendentesPorUsuario(idUsuario);

            Long qtdTarefasConcluidas = tarefaRepository.getQtdTarefasConcluidasPorUsuario(idUsuario);

            InfoAdicionaisPerfilDto infoAdicional = new InfoAdicionaisPerfilDto(
                    qtdTarefas, qtdTarefasPendentes, qtdTarefasConcluidas);

            listaInfoAdicional.add(infoAdicional);

            return ResponseEntity.ok().body(listaInfoAdicional);
        }

        return ResponseEntity.status(403).build();

    }
}

class Data {
    public LocalDate obterDiaAtual() {
        return LocalDate.now();
    }
}
