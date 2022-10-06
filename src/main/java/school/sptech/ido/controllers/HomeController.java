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
        List<Tarefa> tarefas = usuario.getTarefas();
        return tarefas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(tarefas);
    }

    @PostMapping("/tarefas")
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        usuario.adicionarTarefa(tarefa);
        return ResponseEntity.status(201).body(tarefa);
    }

    @PutMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> alterarTarefa(@PathVariable int id, @RequestBody Tarefa tarefa) {
        Tarefa tarefaEditada = usuario.editarTarefa(id, tarefa);
        return tarefaEditada != null ?
                ResponseEntity.ok().body(tarefaEditada) :
                ResponseEntity.notFound().build();
    }

    @PatchMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> alterarStatusTarefa(@PathVariable int id, @RequestBody Tarefa tarefa) {
        Tarefa tarefaEditada = usuario.isConcluido(id, tarefa);
        return tarefaEditada != null ?
                ResponseEntity.ok().body(tarefaEditada) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/tarefas/{id}")
    public ResponseEntity<Tarefa> removerTarefa(@PathVariable int id) {
        Tarefa tarefaRemovida = usuario.removerTarefa(id);
        return tarefaRemovida != null ?
                ResponseEntity.ok().body(tarefaRemovida) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/etiquetas")
    public ResponseEntity<List<Etiqueta>> listarEtiquetas() {
        List<Etiqueta> etiquetas = usuario.getGerenciadorEtiquetas().getEtiquetas();
        return etiquetas.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok().body(etiquetas);
    }

    @PostMapping("/etiquetas")
    public ResponseEntity<Etiqueta> criarEtiqueta(@RequestBody Etiqueta etiqueta) {
        Etiqueta etiquetaCadastrada = usuario.getGerenciadorEtiquetas().cadastrarEtiqueta(etiqueta);
        return etiquetaCadastrada != null ?
                ResponseEntity.status(201).body(etiquetaCadastrada) :
                ResponseEntity.status(405).build();
    }

    @PutMapping("/etiquetas/{id}")
    public ResponseEntity<Etiqueta> alterarEtiqueta(@PathVariable int id, @RequestBody Etiqueta etiqueta) {
        Etiqueta etiquetaEditada = usuario.getGerenciadorEtiquetas().editarEtiqueta(id , etiqueta);
        return etiquetaEditada != null ?
                ResponseEntity.ok().body(etiquetaEditada) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/etiquetas/{id}")
    public ResponseEntity<Etiqueta> removerEtiqueta(@PathVariable int id) {
        Etiqueta etiquetaRemovida = usuario.getGerenciadorEtiquetas().removerEtiqueta(id);
        return etiquetaRemovida != null ?
                ResponseEntity.ok().body(etiquetaRemovida) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/tarefas/ordem")
    public ResponseEntity<List<List<Tarefa>>> ordenarListaTarefa() {
        List<List<Tarefa>> tarefasOrdenadas = usuario.ordenar();
        return tarefasOrdenadas.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok().body(tarefasOrdenadas);
    }

    @GetMapping("/etiquetas/ordem")
    public ResponseEntity<List<Etiqueta>> ordenarListaEtiqueta() {
        List<Etiqueta> etiquetasOrdenadas = usuario.getGerenciadorEtiquetas().ordenar();
        return etiquetasOrdenadas.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok().body(etiquetasOrdenadas);
    }
}
