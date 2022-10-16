package school.sptech.ido.application.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.controller.dto.TarefaAtualizadaDto;
import school.sptech.ido.application.controller.dto.TarefaCadastroDto;
import school.sptech.ido.application.controller.dto.TarefaDto;
import school.sptech.ido.domain.model.ListaObj;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class TarefaController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioController usuarioController;

    @GetMapping("/usuarios/{idUsuario}/tarefas")
    public ResponseEntity<List<TarefaDto>> listarTarefasPorIdUsuario(@PathVariable Integer idUsuario){

        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            List<TarefaEntity> tarefas = tarefaRepository.findByFkUsuario(idUsuario);

            if (tarefas.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(tarefas.stream().map(TarefaDto::new).collect(Collectors.toList()));
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @GetMapping("/usuarios/{idUsuario}/tarefas/ordenado")
    public ResponseEntity<List<TarefaDto>> listarTarefasPorIdUsuarioOrdenadas(@PathVariable Integer idUsuario){

        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            List<TarefaEntity> tarefas = tarefaRepository.findByFkUsuario(idUsuario);

            if (tarefas.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            ListaObj<TarefaEntity> listaTarefas = new ListaObj<TarefaEntity>(tarefas.size());

            for (TarefaEntity tarefa: tarefas){
                listaTarefas.adiciona(tarefa);
            }

            listaTarefas.exibe();

            listaTarefas.ordenarTarefa(listaTarefas);

            List<TarefaDto> tarefasDtos = new ArrayList<TarefaDto>();
            for (int i = 0; i < listaTarefas.getTamanho(); i++) {
                tarefasDtos.add(new TarefaDto(listaTarefas.getElemento(i)));
            }

            return ResponseEntity.ok().body(tarefasDtos);
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @GetMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}")
    public ResponseEntity<TarefaDto> buscarPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefaEntity = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);

            if (tarefaEntity.isPresent()){
                return ResponseEntity.ok().body(new TarefaDto(tarefaEntity.get()));
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @PostMapping("/usuarios/{idUsuario}/tarefas")
    public ResponseEntity<TarefaDto> salvarTarefaPorIdUsuario(
            @PathVariable Integer idUsuario,
            @RequestBody @Valid TarefaCadastroDto tarefaCadastroDto
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<UsuarioEntity> usuario = usuarioRepository.findById(idUsuario);

            if (usuario.isPresent()){
                UsuarioEntity usuarioEncontrado = usuario.get();
                TarefaEntity tarefaSalva = tarefaRepository.save(new TarefaEntity(tarefaCadastroDto, usuarioEncontrado));
                return ResponseEntity.status(201).body(new TarefaDto(tarefaSalva));
            }

            return ResponseEntity.status(401).build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @PutMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}")
    public ResponseEntity<TarefaDto> atualizarTarefaPorIdTarefa(
        @PathVariable Integer idUsuario,
        @PathVariable Integer idTarefa,
        @RequestBody @Valid TarefaAtualizadaDto tarefaAtualizadaDto
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefaEntity = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);

            if (tarefaEntity.isPresent()){
                TarefaEntity tarefaEncontrada = tarefaEntity.get();
                BeanUtils.copyProperties(tarefaAtualizadaDto, tarefaEncontrada);
                TarefaEntity tarefaAtualizada = tarefaRepository.save(tarefaEncontrada);
                return ResponseEntity.ok().body(new TarefaDto(tarefaAtualizada));
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }

    @PatchMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/status/concluido")
    public ResponseEntity<Void> atualizarStatusPorIdTarefa(
        @PathVariable Integer idUsuario,
        @PathVariable Integer idTarefa
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefaEntity = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);

            if (tarefaEntity.isPresent()){
                TarefaEntity tarefaAtualizada = tarefaEntity.get();
                tarefaAtualizada.setStatus(true);
                tarefaAtualizada.setDataConclusao(LocalDate.now());
                tarefaRepository.save(tarefaAtualizada);
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}")
    public ResponseEntity<Void> deletarTarefaPorIdTarefa(
        @PathVariable Integer idUsuario,
        @PathVariable Integer idTarefa
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){

            if (tarefaRepository.existsById(idTarefa)){
                tarefaRepository.deleteById(idTarefa);
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(403).build();
        }

    }
}
