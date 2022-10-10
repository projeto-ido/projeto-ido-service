package school.sptech.ido.application.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.dto.TarefaAtualizadaDto;
import school.sptech.ido.application.dto.TarefaCadastroDto;
import school.sptech.ido.application.dto.TarefaDto;
import school.sptech.ido.repository.TarefaRepository;
import school.sptech.ido.repository.UsuarioRepository;
import school.sptech.ido.repository.entity.TarefaEntity;
import school.sptech.ido.repository.entity.UsuarioEntity;

import javax.validation.Valid;
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

        usuarioController.isUsuarioAutenticado(idUsuario);

        List<TarefaEntity> tarefas = tarefaRepository.findByFkUsuario(idUsuario);

        if (tarefas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(tarefas.stream().map(TarefaDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}")
    public ResponseEntity<TarefaDto> buscarPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

        Optional<TarefaEntity> tarefaEntity = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);

        if (tarefaEntity.isPresent()){
            return ResponseEntity.ok().body(new TarefaDto(tarefaEntity.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/usuarios/{idUsuario}/tarefas")
    public ResponseEntity<TarefaDto> salvarTarefaPorIdUsuario(
            @PathVariable Integer idUsuario,
            @RequestBody @Valid TarefaCadastroDto tarefaCadastroDto
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

        Optional<UsuarioEntity> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()){
            UsuarioEntity usuarioEncontrado = usuario.get();
            TarefaEntity tarefaSalva = tarefaRepository.save(new TarefaEntity(tarefaCadastroDto, usuarioEncontrado));
            return ResponseEntity.status(201).body(new TarefaDto(tarefaSalva));
        }

        return ResponseEntity.status(401).build();
    }

    @PutMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}")
    public ResponseEntity<TarefaDto> atualizarTarefaPorIdTarefa(
        @PathVariable Integer idUsuario,
        @PathVariable Integer idTarefa,
        @RequestBody @Valid TarefaAtualizadaDto tarefaAtualizadaDto
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

        Optional<TarefaEntity> tarefaEntity = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);

        if (tarefaEntity.isPresent()){
            TarefaEntity tarefaEncontrada = tarefaEntity.get();
            BeanUtils.copyProperties(tarefaAtualizadaDto, tarefaEncontrada);
            TarefaEntity tarefaAtualizada = tarefaRepository.save(tarefaEncontrada);
            return ResponseEntity.ok().body(new TarefaDto(tarefaAtualizada));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}")
    public ResponseEntity<Void> deletarTarefaPorIdTarefa(
        @PathVariable Integer idUsuario,
        @PathVariable Integer idTarefa
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

        if (tarefaRepository.existsById(idTarefa)){
            tarefaRepository.deleteById(idTarefa);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
