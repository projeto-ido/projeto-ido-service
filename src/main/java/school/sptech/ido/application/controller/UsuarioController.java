package school.sptech.ido.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.domain.model.Usuario;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final List<Usuario> usuarios = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        usuarios.add(usuario);
        return ResponseEntity.status(201).body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return usuarios.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok().body(usuarios);
    }

    @PutMapping("/{nome}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable String nome, @RequestBody Usuario usuarioNovo) {
        for (Usuario usuario: usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)){
                usuario = usuarioNovo;
                return ResponseEntity.ok().body(usuario);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String nome) {
        return usuarios.removeIf(u -> u.getNome().equalsIgnoreCase(nome)) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> logar(@RequestBody Usuario user) {
        Usuario usuario = validarLogin(user);

        if (usuario != null) {
            usuario.setAutenticado(true);
            HomeController.usuario = user;
            TarefaController.usuario = user;
            return ResponseEntity.ok().body(usuario);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/logoff")
    public ResponseEntity<Void> deslogar() {
        if (HomeController.usuario != null && HomeController.usuario.isAutenticado()) {
            HomeController.usuario.setAutenticado(false);
            HomeController.usuario = null;
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(405).build();
    }

    private Usuario validarLogin(Usuario user) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(user.getEmail()) && u.getSenha().equals(user.getSenha()))
                .findAny()
                .orElse(null);
    }
}
