package school.sptech.ido;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class  HomeController {

    static Usuario usuario;

    @GetMapping("/tarefas")
    public List<Tarefa> listarTarefas(){
        return usuario.getTarefas();
    }

    @PostMapping("/tarefas")
    public Tarefa criarTarefa(@RequestBody Tarefa tarefa){
        usuario.adicionarTarefa(tarefa);
        return tarefa;
    }

    @PutMapping("/tarefas/id")
    public void alterarTarefa(@PathVariable int id, @RequestBody Tarefa tarefa){
        usuario.editarTarefa(id, tarefa);
    }

    @PatchMapping("/tarefas/id")
    public void alterarStatusTarefa(@PathVariable int id, @RequestBody Tarefa tarefa){
        usuario.isConcluido(id, tarefa);
    }

    @DeleteMapping("/tarefas/{id}")
    public void removerTarefa(@PathVariable int id){
        usuario.removerTarefa(id);
    }

    @GetMapping("/etiquetas")
    public List<Etiqueta> listarEtiquetas(){
        return usuario.getGerenciadorEtiquetas().getEtiquetas();
    }

    @PostMapping("/etiquetas")
    public Etiqueta criarEtiqueta(@RequestBody Etiqueta etiqueta){
        return usuario.getGerenciadorEtiquetas().cadastratarEtiqueta(etiqueta);
    }

    @PutMapping("/etiquetas/{id}")
    public Etiqueta alterarEtiqueta(@PathVariable int id, @RequestBody Etiqueta etiqueta){
        return usuario.getGerenciadorEtiquetas().editarEtiqueta(id , etiqueta);
    }

    @DeleteMapping("/etiquetas/{id}")
    public void removerEtiqueta(@PathVariable int id){
        usuario.getGerenciadorEtiquetas().removerEtiqueta(id);
    }

    @GetMapping("/tarefas/ordem")
    public List ordenarListaTarefa(){
        return usuario.ordenar();
    }

    @GetMapping("/etiquetas/ordem")
    public List ordenarListaEtiqueta(){
        return usuario.getGerenciadorEtiquetas().ordenar();
    }
}
