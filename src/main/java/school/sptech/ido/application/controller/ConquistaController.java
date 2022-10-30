package school.sptech.ido.application.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.controller.dto.ConquistaDto;
import school.sptech.ido.resources.repository.ConquistaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.ConquistaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import java.util.List;
import java.util.Optional;

@Tag(name = "Conquista", description = "Reponsável por gerir as conquistas dos usuários.")
@RestController
public class ConquistaController {

    @Autowired
    ConquistaRepository conquistaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioController usuarioController;

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Não há Conquista realizadas.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "200", description = "Conquistas encontradas."),
            @ApiResponse(responseCode = "404", description = "Conquista não encontrada.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Usuário não autenticado."),


    })

    @GetMapping("/usuarios/{idUsuario}/conquistas")
    public ResponseEntity<List<ConquistaEntity>> listarConquistas(
        @PathVariable Integer idUsuario
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            List<ConquistaEntity> conquistas = conquistaRepository.findByIdUsuario(idUsuario);

            if (conquistas.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(conquistas);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/usuarios/{idUsuario}/conquistas")
    public ResponseEntity<ConquistaEntity> salvarConquista(
        @PathVariable Integer idUsuario,
        @RequestBody ConquistaDto conquistaDto
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(idUsuario);

            return usuarioEntity.map(entity -> ResponseEntity.status(201).body(
                    conquistaRepository.save(new ConquistaEntity(conquistaDto, entity))
            )).orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
