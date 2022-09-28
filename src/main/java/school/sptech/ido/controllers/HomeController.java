package school.sptech.ido.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.Etiqueta;
import school.sptech.ido.Tarefa;
import school.sptech.ido.Usuario;

import java.util.List;

@RestController
@RequestMapping("/home")
public class  HomeController {

    static Usuario usuario;

    @GetMapping("/tarefas")
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        if(usuario.getTarefas().isEmpty()){
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok().body(usuario.getTarefas());
    }

    @PostMapping("/tarefas")
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        usuario.adicionarTarefa(tarefa);
        return ResponseEntity.ok().body(tarefa);
    }

    @PutMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> alterarTarefa(@PathVariable int id, @RequestBody Tarefa tarefa) {
        if (isIdInvalid(id)) {
            return ResponseEntity.notFound().build();
        }
        usuario.editarTarefa(id, tarefa);
        return ResponseEntity.ok().body(tarefa);
    }

    @PatchMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> alterarStatusTarefa(@PathVariable int id, @RequestBody Tarefa tarefa) {
        if (isIdInvalid(id)) {
            return ResponseEntity.notFound().build();
        }
        usuario.isConcluido(id, tarefa);
        return ResponseEntity.ok().body(tarefa);
    }

    @DeleteMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> removerTarefa(@PathVariable int id) {
        if (isIdInvalid(id)) {
            return ResponseEntity.notFound().build();
        }
        Tarefa tarefaRemovida = usuario.getTarefas().stream().filter(t -> t.getId() == id).findAny().get();
        usuario.removerTarefa(id);
        return ResponseEntity.ok().body(tarefaRemovida);
    }

    @GetMapping("/etiquetas")
    public ResponseEntity<List<Etiqueta>> listarEtiquetas() {
        List<Etiqueta> etiquetas = usuario.getGerenciadorEtiquetas().getEtiquetas();
        if (etiquetas.isEmpty()) return ResponseEntity.status(401).build();
        return ResponseEntity.ok().body(etiquetas);
    }

    @PostMapping("/etiquetas")
    public ResponseEntity<Etiqueta> criarEtiqueta(@RequestBody Etiqueta etiqueta) {
        return ResponseEntity.status(201).body(usuario.getGerenciadorEtiquetas().cadastrarEtiqueta(etiqueta));
    }

    @PutMapping("/etiquetas/{id}")
    public ResponseEntity<Etiqueta> alterarEtiqueta(@PathVariable int id, @RequestBody Etiqueta etiqueta) {
        if (isIdInvalid(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuario.getGerenciadorEtiquetas().editarEtiqueta(id , etiqueta));
    }

    @DeleteMapping("/etiquetas/{id}")
    public ResponseEntity<Etiqueta> removerEtiqueta(@PathVariable int id) {
        if (isIdInvalid(id)) {
            return ResponseEntity.notFound().build();
        }

        Etiqueta etiquetaRemovida = usuario.getGerenciadorEtiquetas().getEtiquetas()
            .stream()
            .filter(e -> e.getId() == id)
            .findAny()
            .get();

        usuario.getGerenciadorEtiquetas().removerEtiqueta(id);
        return ResponseEntity.ok().body(etiquetaRemovida);
    }

    @GetMapping("/tarefas/ordem")
    public ResponseEntity<List<List<Tarefa>>> ordenarListaTarefa() {
        return ResponseEntity.ok().body(usuario.ordenar());
    }

    @GetMapping("/etiquetas/ordem")
    public ResponseEntity<List<Etiqueta>> ordenarListaEtiqueta() {
        return ResponseEntity.ok().body(usuario.getGerenciadorEtiquetas().ordenar());
    }

    private boolean isIdInvalid(int id) {
        // stream = apenas outro tipo de lista, mas com métodos que o List nao oferece
        // noneMatch = nenhum elemento da lista satisfaz a condição passada
        return usuario.getTarefas().stream().noneMatch(t -> t.getId() == id);
    }
}
