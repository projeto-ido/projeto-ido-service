package school.sptech.ido;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

   private List<Usuario>usuarios = new ArrayList<>();
   private boolean isLogado;

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
    public String verficarLogin(@RequestBody Usuario user){
       for (Usuario usuario: usuarios) {
           if (usuario.getEmail().equals(user.getEmail()) && usuario.getSenha().equals(user.getSenha())){
               isLogado = true;
               HomeController.usuario = usuario;
               TarefaController.usuario = usuario;
               return "Bem vindo " + usuario.getNome();
           }

       }
       return "Email ou senha incorreta";
   }
   @GetMapping("/logoff")
    public String deslogar(){
       isLogado = false;
       HomeController.usuario = null;
       return "Tchau Tchau, volte sempre";
   }


}
