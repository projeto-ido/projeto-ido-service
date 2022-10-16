package school.sptech.ido.application.controller;

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

@RestController
public class ConquistaController {

    @Autowired
    ConquistaRepository conquistaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioController usuarioController;

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
            if (usuarioEntity.isPresent()){
                return ResponseEntity.status(201).body(
                    conquistaRepository.save(new ConquistaEntity(conquistaDto, usuarioEntity.get()))
                );
            }
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
