package school.sptech.ido.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.Etiqueta;
import school.sptech.ido.SubTarefa;
import school.sptech.ido.Tarefa;
import school.sptech.ido.Usuario;

import java.util.List;

@RestController
@RequestMapping("/home/tarefa")
public class TarefaController {
    static Tarefa tarefa;
    static Usuario usuario;

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> selecionarTarefa(@PathVariable int id) {
        if (id > 0 && id <= usuario.getTarefas().size()) {
            tarefa = usuario.getTarefas().get(id - 1);
            return ResponseEntity.ok().body(tarefa);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/sub")
    public ResponseEntity<List<SubTarefa>> listarSubtarefa() {
        List<SubTarefa> subTarefas = tarefa.getSubtarefas();
        return subTarefas.isEmpty() ? ResponseEntity.status(204).build() : ResponseEntity.ok().body(subTarefas);
    }

    @PostMapping("/sub")
    public ResponseEntity<SubTarefa> criarSubTarefa(@RequestBody SubTarefa subtarefa) {
        SubTarefa subTarefaAdicionada = tarefa.adicionarSubTarefa(subtarefa);
        return subTarefaAdicionada != null ?
                ResponseEntity.status(201).body(subTarefaAdicionada) :
                ResponseEntity.status(405).build();
    }

    @PutMapping("/sub/{id}")
    public ResponseEntity<SubTarefa> atualizarSubtarefa(@PathVariable int id, @RequestBody SubTarefa subtarefa) {
        SubTarefa subTarefaEditada = tarefa.editarSubtarefa(id, subtarefa);
        return subTarefaEditada != null ?
                ResponseEntity.ok().body(subTarefaEditada) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/sub/{id}")
    public ResponseEntity<SubTarefa> removerSubtarefa(@PathVariable int id) {
        SubTarefa subTarefaRemovida = tarefa.removerSubTarefa(id);
        return subTarefaRemovida != null ?
                ResponseEntity.ok().body(subTarefaRemovida) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/etiqueta")
    public ResponseEntity<Etiqueta> associarEtiqueta(@RequestBody Etiqueta etiqueta) {
        Etiqueta etiquetaAdicionada = tarefa.adicionarEtiqueta(etiqueta);
        return etiquetaAdicionada != null ?
                ResponseEntity.status(201).body(etiquetaAdicionada) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/etiqueta/{id}")
    public ResponseEntity<Etiqueta> desassociarEtiqueta(@PathVariable int id){
        Etiqueta etiquetaRemovida = tarefa.removerEtiqueta(id);
        return etiquetaRemovida != null ?
                ResponseEntity.ok().body(etiquetaRemovida) :
                ResponseEntity.notFound().build();
    }
}
