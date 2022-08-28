package school.sptech.ido;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home/tarefa")
public class TarefaController {
    static Tarefa tarefa;
    static Usuario usuario;

    @GetMapping("/{id}")
    public void tarefaSelecionada(@PathVariable int id){
        for (int i = 0; i < usuario.getTarefas().size(); i++) {
            if(usuario.getTarefas().get(i).getId() == id) tarefa = usuario.getTarefas().get(i);
        }
    }

    @GetMapping
    public SubTarefa[] listarSubtarefa(){
        return tarefa.getSubtarefas();
    }

    @PostMapping
    public void criarSubTarefa(@RequestBody SubTarefa subtarefa){
        tarefa.cadastrarSubTarefa(subtarefa);
    }

    @PutMapping("/{id}")
    public void atualizarSubtarefa(@PathVariable int id, @RequestBody SubTarefa subtarefa){
        tarefa.editarSubtarefa(id, subtarefa);
    }

    @DeleteMapping("{id}")
    public void removerSubtarefa(@PathVariable int id){
        tarefa.removerSubTarefa(id);
    }

    @PostMapping("/associar")
    public void associarEtiqueta(@RequestBody Etiqueta etiqueta){
        tarefa.associarEtiqueta(etiqueta);
    }

    @DeleteMapping("/desassociar/{id}")
    public void desassociarEtiqueta(@PathVariable int id){
        tarefa.desassociarEtiqueta(id);
    }

}
