package school.sptech.ido;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final List<Usuario> usuarios = new ArrayList<>();

    @PostMapping
    public String cadastrarUsuario(@RequestBody Usuario usuario){
        usuarios.add(usuario);
        return "Usuário foi cadastrado com sucesso!!";

    }

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarios;
    }

    @PutMapping("/{nome}")
    public Usuario atualizarUsuario(@PathVariable String nome, @RequestBody Usuario usuarioNovo){
        for (Usuario usuario: usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)){
                usuario = usuarioNovo;

                return usuario;
            }
        }
        return null;
    }

    @DeleteMapping("/{nome}")
    public String deletarUsuario(@PathVariable String nome){
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNome().equalsIgnoreCase(nome)){
                usuarios.remove(i);
                return "Usuário foi removido com sucesso!!";
            }
        }
        return "Usuário não foi encontrado";
    }

    @PostMapping("/login")
    public Usuario logar(@RequestBody Usuario user){
        Usuario usuario = validarLogin(user);

        if (usuario != null) {
            usuario.setAutenticado(true);
            HomeController.usuario = user;
            TarefaController.usuario = user;
            return usuario;
        }

        return null;
    }

    @GetMapping("/logoff")
    public String deslogar(){
        HomeController.usuario.setAutenticado(false);
        HomeController.usuario = null;
        return "Tchau Tchau, volte sempre";
    }

    private Usuario validarLogin(Usuario user) {
        for (Usuario usuario: usuarios) {
            if (usuario.getEmail().equals(user.getEmail()) && usuario.senha().equals(user.senha())) {
                return usuario;
            }
        }
        return null;
    }
}
