package school.sptech.ido.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.dto.*;
import school.sptech.ido.repository.SubTarefaRepository;
import school.sptech.ido.repository.TarefaRepository;
import school.sptech.ido.repository.entity.SubTarefaEntity;
import school.sptech.ido.repository.entity.TarefaEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SubTarefaController {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioController usuarioController;

    @Autowired
    SubTarefaRepository subTarefaRepository;

    @GetMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/sub-tarefas/")
    public ResponseEntity<List<SubTarefaDto>> buscarPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

       List<SubTarefaEntity> subtarefas = subTarefaRepository
                .findAllByIdTarefa(idTarefa);

        if (subtarefas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(subtarefas.stream().map(SubTarefaDto::new).collect(Collectors.toList()));
    }

    @PostMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/sub-tarefas/")
    public ResponseEntity<SubTarefaEntity> salvarSubTarefaPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa,
            @RequestBody @Valid SubTarefaCadastroDto subTarefaCadastroDto
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

        Optional<TarefaEntity> tarefa = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);
        if (tarefa.isPresent()){
            SubTarefaEntity subTarefaEntity = subTarefaRepository.save(new SubTarefaEntity(subTarefaCadastroDto, tarefa.get()));
            return ResponseEntity.status(201).body(subTarefaEntity);
        }

        return ResponseEntity.notFound().build();
    }
}
