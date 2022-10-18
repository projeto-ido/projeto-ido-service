package school.sptech.ido.application.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.dto.TarefaAtualizadaDto;
import school.sptech.ido.application.dto.TarefaCadastroDto;
import school.sptech.ido.application.dto.TarefaDto;
import school.sptech.ido.repository.TarefaRepository;
import school.sptech.ido.repository.UsuarioRepository;
import school.sptech.ido.repository.entity.TarefaEntity;
import school.sptech.ido.repository.entity.UsuarioEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Tarefa", description = "Reponsável por gerir as Tarefas do usuários.")
@RestController
public class TarefaController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioController usuarioController;

    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Não há Tarefas cadastradas.", content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas."),
            @ApiResponse(responseCode = "201", description = "Tarefa cadastrada."),
            @ApiResponse(responseCode = "401", description = "O conteúdo não é autorizado."),
            @ApiResponse(responseCode = "403", description = "O conteúdo dessa página é proibido.")
    })


    @GetMapping("/usuarios/{idUsuario}/tarefas")
    public ResponseEntity<List<TarefaDto>> listarTarefasPorIdUsuario(@PathVariable Integer idUsuario){

        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            List<TarefaEntity> tarefas = tarefaRepository.findByFkUsuario(idUsuario);

            if (tarefas.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(tarefas.stream().map(TarefaDto::new).collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @GetMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}")
    public ResponseEntity<TarefaDto> buscarPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefaEntity = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);

            return tarefaEntity.map(
                entity -> ResponseEntity.ok().body(
                    new TarefaDto(entity)
                )
            ).orElseGet(
                () -> ResponseEntity.notFound().build()
            );

        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @PostMapping("/usuarios/{idUsuario}/tarefas")
    public ResponseEntity<TarefaDto> salvarTarefaPorIdUsuario(
            @PathVariable Integer idUsuario,
            @RequestBody @Valid TarefaCadastroDto tarefaCadastroDto
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<UsuarioEntity> usuario = usuarioRepository.findById(idUsuario);

            if (usuario.isPresent()){
                UsuarioEntity usuarioEncontrado = usuario.get();
                TarefaEntity tarefaSalva = tarefaRepository.save(new TarefaEntity(tarefaCadastroDto, usuarioEncontrado));
                return ResponseEntity.status(201).body(new TarefaDto(tarefaSalva));
            }

            return ResponseEntity.status(401).build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @PutMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}")
    public ResponseEntity<TarefaDto> atualizarTarefaPorIdTarefa(
        @PathVariable Integer idUsuario,
        @PathVariable Integer idTarefa,
        @RequestBody @Valid TarefaAtualizadaDto tarefaAtualizadaDto
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefaEntity = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);

            if (tarefaEntity.isPresent()){
                TarefaEntity tarefaEncontrada = tarefaEntity.get();
                BeanUtils.copyProperties(tarefaAtualizadaDto, tarefaEncontrada);
                TarefaEntity tarefaAtualizada = tarefaRepository.save(tarefaEncontrada);
                return ResponseEntity.ok().body(new TarefaDto(tarefaAtualizada));
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @DeleteMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}")
    public ResponseEntity<Void> deletarTarefaPorIdTarefa(
        @PathVariable Integer idUsuario,
        @PathVariable Integer idTarefa
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){

            if (tarefaRepository.existsById(idTarefa)){
                tarefaRepository.deleteById(idTarefa);
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }
}
