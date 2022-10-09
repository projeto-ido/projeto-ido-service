package school.sptech.ido.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.dto.UsuarioAtualizadoDto;
import school.sptech.ido.application.dto.UsuarioCadastroDto;
import school.sptech.ido.application.dto.UsuarioLoginDto;
import school.sptech.ido.domain.model.Usuario;
import school.sptech.ido.repository.UsuarioRepository;
import school.sptech.ido.repository.entity.UsuarioEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> listarUsuarios() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok().body(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDto usuario) {
        return ResponseEntity.status(201).body(usuarioRepository.save(new UsuarioEntity(usuario)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> atualizarUsuario(
        @PathVariable Integer id,
        @RequestBody @Valid UsuarioAtualizadoDto usuarioAtualizadoDto
    ) {

        if (usuarioRepository.existsById(id)){
            UsuarioEntity usuarioAtualizado = new UsuarioEntity(id, usuarioAtualizadoDto);
            return ResponseEntity.ok().body(usuarioRepository.save(usuarioAtualizado));
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/nivel/{novoNivel}")
    public ResponseEntity<UsuarioEntity> atualizarNivel(
        @PathVariable Integer id,
        @PathVariable Integer novoNivel
    ) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()){
            UsuarioEntity usuarioEncontrado = usuario.get();
            usuarioEncontrado.setNivel(novoNivel);
            return ResponseEntity.ok().body(usuarioRepository.save(usuarioEncontrado));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioEntity> deletarUsuario(@PathVariable Integer id) {

        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioEntity> logar(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmailAndSenha(
            usuarioLoginDto.getEmail(),
            usuarioLoginDto.getSenha()
        );

        if (usuario.isPresent()){
            UsuarioEntity usuarioLogado = usuario.get();
            usuarioLogado.setAutenticado(true);
            return ResponseEntity.ok().body(usuarioRepository.save(usuarioLogado));
        }

        return ResponseEntity.status(401).build();
    }

    @GetMapping("/{id}/logoff")
    public ResponseEntity<Void> deslogar(@PathVariable Integer id) {

        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()){
            UsuarioEntity usuarioDeslogado = usuario.get();
            if (usuarioDeslogado.getAutenticado()){
                usuarioDeslogado.setAutenticado(false);
                usuarioRepository.save(usuarioDeslogado);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.status(404).build();
    }

}
