package school.sptech.ido;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class  HomeController {

    static Usuario usuario;

    @GetMapping
    public List<Tarefa> listarTarefas(){
        return usuario.getTarefas();
    }

    @PostMapping
    public void criarTarefa(@RequestBody Tarefa tarefa){
        usuario.adicionarTarefa(tarefa);
    }

    @PutMapping("/id")
    public void alterarTarefa(@PathVariable int id, @RequestBody Tarefa tarefa){
        usuario.editarTarefa(id, tarefa);
    }

    @PatchMapping("/id")
    public void alterarStatusTarefa(@PathVariable int id, @RequestBody Tarefa tarefa){
        usuario.isConcluido(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void removerTarefa(@PathVariable int id){
        usuario.removerTarefa(id);
    }

    @GetMapping("/etiquetas")
    public List<Etiqueta> listarEtiquetas(){
        return usuario.getGerenciadorEtiquetas().getEtiquetas();
    }

    @PostMapping("/etiquetas")
    public void criarEtiqueta(@RequestBody Etiqueta etiqueta){
        usuario.getGerenciadorEtiquetas().cadastratarEtiqueta(etiqueta);
    }

    @PutMapping("/etiquetas/{id}")
    public void alterarEtiqueta(@PathVariable int id, @RequestBody Etiqueta etiqueta){
        usuario.getGerenciadorEtiquetas().editarEtiqueta(id , etiqueta);
    }

    @DeleteMapping("/etiquetas/{id}")
    public void removerEtiqueta(@PathVariable int id){
        usuario.getGerenciadorEtiquetas().removerEtiqueta(id);
    }
}
