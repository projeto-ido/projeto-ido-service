package school.sptech.ido.application.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.controller.dto.DiaSemana;
import school.sptech.ido.application.controller.dto.Request.AtualizacaoSenhaDto;
import school.sptech.ido.application.controller.dto.Response.*;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.TarefaEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Tag(name = "Perfil", description = "Responsavel por administrar a pagina de perfil")
@RestController
public class PerfilController {

    @Autowired
    UsuarioRepository usuarioRepository;

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

            List<EtiquetaDto> etiquetaDtos = etiquetaRepository.findAllEtiquetasDto(idUsuario);

            List<TarefaEtiquetasDto> tarefaEtiquetaDto = tarefaRepository.getTarefaEtiquetaDto(idUsuario);

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

            List<TarefaTimeLine> tarefaTimeLines = tarefaRepository.getTarefasTimeLine(idUsuario);

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
            LocalDateTime diaSemana = data.obterDiaAtual();

            Integer diasAnteriores = 0;

            Long qtdTarefasConcluidas;

            List<DiaSemana> semana = new ArrayList<>();

            List<TarefaEntity> tarefas = tarefaRepository.getTarefasConcluidas(idUsuario);

            for (int i = 0; i < 7; i++) {
                qtdTarefasConcluidas = Long.valueOf(0);

                LocalDate diaSemanaLocalDate = LocalDate.from(diaSemana);

                for (TarefaEntity tarefa: tarefas) {
                    LocalDate diaConclusaoTarefa = LocalDate.from(tarefa.getDataConclusao());

                    if (diaSemanaLocalDate.equals(diaConclusaoTarefa)){
                        qtdTarefasConcluidas += 1;
                    }
                }

                DiaSemana dia = new DiaSemana(diaSemana, qtdTarefasConcluidas);

                semana.add(dia);

                diaSemana = diaSemana.minusDays(1);

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

    @PatchMapping("/usuarios/{idUsuario}/perfil/atualizar-senha")
    public ResponseEntity<Void> atualizarSenha(@PathVariable Integer idUsuario,
                                               @RequestBody AtualizacaoSenhaDto atualizacaoSenha){

        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);

        if (isAutenticado){
            Integer isSenhaValida = usuarioRepository.isSenhaValida(atualizacaoSenha.getSenhaAnterior(), idUsuario);

            if (!(isSenhaValida == 1))
                return ResponseEntity.status(BAD_REQUEST).build();

            Integer atualizarSenha = usuarioRepository.updateSenha(atualizacaoSenha.getSenhaNova(), idUsuario);

            if (atualizarSenha == 1)
                return ResponseEntity.ok().build();

            return ResponseEntity.badRequest().build();

        }

        return ResponseEntity.status(FORBIDDEN).build();
    }

}

