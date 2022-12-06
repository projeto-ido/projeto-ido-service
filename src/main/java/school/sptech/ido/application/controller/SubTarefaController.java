package school.sptech.ido.application.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.controller.dto.Request.SubTarefaCadastroDto;
import school.sptech.ido.application.controller.dto.Response.SubTarefaDto;
import school.sptech.ido.resources.repository.SubTarefaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Sub-Tarefa", description = "Responsável por gerir as sub-tarefas que estão associada a tarefa.")
@RestController
public class SubTarefaController {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioController usuarioController;

    @Autowired
    SubTarefaRepository subTarefaRepository;

    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Não há Sub-Tarefas cadastradas.", content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(responseCode = "200", description = "Sub-Tarefas encontradas."),
            @ApiResponse(responseCode = "201", description = "sub-Tarefa cadastrada."),
            @ApiResponse(responseCode = "403", description = "O conteúdo dessa página é proibido.")
    })

    @GetMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/sub-tarefas/")
    public ResponseEntity<List<SubTarefaDto>> buscarPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            List<SubTarefaEntity> subtarefas = subTarefaRepository
                    .findAllByIdTarefa(idTarefa);

            if (subtarefas.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(subtarefas.stream().map(SubTarefaDto::new).collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @PostMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/sub-tarefas")
    public ResponseEntity<SubTarefaDto> salvarSubTarefaPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa,
            @RequestBody @Valid SubTarefaCadastroDto subTarefaCadastroDto
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefa = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);
            if (tarefa.isPresent()) {
                SubTarefaEntity subTarefaEntity = new SubTarefaEntity(subTarefaCadastroDto, tarefa.get());
                subTarefaRepository.save(subTarefaEntity);
                SubTarefaDto subTarefaDto = new SubTarefaDto(subTarefaEntity);
                return ResponseEntity.status(201).body(subTarefaDto);
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @PutMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/sub-tarefas/{idSubTarefa}")
    public ResponseEntity<SubTarefaEntity> atualizarSubTarefaPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa,
            @PathVariable Integer idSubTarefa,
            @RequestBody SubTarefaDto subTarefaDto
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefa = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);
            if (tarefa.isPresent()){
                subTarefaDto.setIdSubTarefa(idSubTarefa);
                if (subTarefaRepository.existsById(idSubTarefa)){
                    return ResponseEntity.status(200).body(
                            subTarefaRepository.save(new SubTarefaEntity(subTarefaDto, tarefa.get())));
                }
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @DeleteMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/sub-tarefas/{idSubTarefa}")
    public ResponseEntity<SubTarefaEntity> deletarSubTarefaPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa,
            @PathVariable Integer idSubTarefa
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefa = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);
            if (tarefa.isPresent()){
                if (subTarefaRepository.existsById(idSubTarefa)){

                    Optional<SubTarefaEntity> sub = subTarefaRepository.findById(idSubTarefa);

                    if (sub.isPresent()){
                        SubTarefaEntity subTarefaEntity = sub.get();
                        TarefaEntity tarefaEntity = subTarefaEntity.getTarefa();

                        subTarefaEntity.setTarefa(null);
                        subTarefaRepository.save(subTarefaEntity);

                        List<SubTarefaEntity> subTarefas = new ArrayList<>();

                        for ( SubTarefaEntity subTarefa: tarefaEntity.getSubTarefas()) {
                            if (!subTarefa.getIdSubTarefa().equals(idSubTarefa)){
                                subTarefas.add(subTarefa);
                            }
                        }

                        tarefaEntity.setSubTarefas(subTarefas);
                        tarefaRepository.save(tarefaEntity);
                    }

                    subTarefaRepository.deleteById(idSubTarefa);
                    return ResponseEntity.status(200).build();
                }
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }
}
