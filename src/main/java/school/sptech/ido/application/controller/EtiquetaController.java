package school.sptech.ido.application.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Etiquetas", description = "Reponsável por gerir as etiquetas nas tarefas e nas sub Tarefas dos usuários.")
@RestController
public class EtiquetaController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioController usuarioController;

    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Não há Etiquetas cadastradas.", content = @Content(schema = @Schema(hidden = true))
        ),
        @ApiResponse(responseCode = "200", description = "Etiquetas encontradas."),
        @ApiResponse(responseCode = "201", description = "Etiqueta cadastrada."),
        @ApiResponse(responseCode = "403", description = "O conteúdo dessa página é proibido.")
    })

    @GetMapping("/usuarios/{idUsuario}/etiquetas")
    public ResponseEntity<List<EtiquetaDto>> listarEtiquetas(@PathVariable Integer idUsuario) {
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            List<EtiquetaEntity> etiquetas = etiquetaRepository.findByFkUsuario(idUsuario);

            if (etiquetas.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(etiquetas.stream().map(EtiquetaDto::new).collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/etiquetas")
    public ResponseEntity<List<EtiquetaDto>> buscarPorIdTarefa(
       @PathVariable Integer idUsuario,
       @PathVariable Integer idTarefa
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            List<EtiquetaEntity> etiquetas = etiquetaRepository.findByIdTarefa(idTarefa);

            if (etiquetas.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(etiquetas.stream().map(EtiquetaDto::new).collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/usuarios/{idUsuario}/etiquetas")
    public ResponseEntity<EtiquetaDto> salvarEtiqueta(
        @PathVariable Integer idUsuario,
        @RequestBody @Valid EtiquetaDto etiquetaDto
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<UsuarioEntity> usuario = usuarioRepository.findById(idUsuario);

            if (usuario.isPresent()){
                UsuarioEntity usuarioEncontrado = usuario.get();
                EtiquetaEntity etiquetaSalva =
                        etiquetaRepository.save(new EtiquetaEntity(etiquetaDto, usuarioEncontrado));
                return ResponseEntity.status(201).body(new EtiquetaDto(etiquetaSalva));
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/usuarios/{idUsuario}/etiquetas/{idEtiqueta}")
    public ResponseEntity<Void> deletarEtiquetaPorId(
        @PathVariable Integer idUsuario,
        @PathVariable Integer idEtiqueta
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<EtiquetaEntity> etiqueta = etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta);

            if (etiqueta.isPresent()){
                etiquetaRepository.deletaAllEtiquetaById(idEtiqueta);
                etiquetaRepository.deleteById(idEtiqueta);
                return ResponseEntity.status(200).build();
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @PostMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/etiquetas/{idEtiqueta}")
    public ResponseEntity<List<EtiquetaDto>> associarEtiquetaPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa,
            @PathVariable Integer idEtiqueta
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefa = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);
            if (tarefa.isPresent()){
                TarefaEntity tarefaEncontrada = tarefa.get();
                Optional<EtiquetaEntity> etiqueta = etiquetaRepository
                        .findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta);

                if (etiqueta.isPresent()){
                    EtiquetaEntity etiquetaEncontrada = etiqueta.get();
                    etiquetaRepository.saveEtiquetaTarefa(idTarefa, idEtiqueta);
                    return ResponseEntity.status(201).build();
                }

                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.notFound().build();

        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @DeleteMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/etiquetas/{idEtiqueta}")
    public ResponseEntity<Void> desassociarEtiquetaPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa,
            @PathVariable Integer idEtiqueta
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefa = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);

            if (tarefa.isPresent()){
                TarefaEntity tarefaEncontrada = tarefa.get();
                Optional<EtiquetaEntity> etiqueta = etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta);

                if (etiqueta.isPresent()){
                    EtiquetaEntity etiquetaEncontrada = etiqueta.get();
                    etiquetaRepository.deleteEtiquetaByIdAndIdTarefa(idTarefa, idEtiqueta);
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
