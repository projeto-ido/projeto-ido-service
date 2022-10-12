package school.sptech.ido.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.dto.EtiquetaDto;
import school.sptech.ido.repository.EtiquetaRepository;
import school.sptech.ido.repository.TarefaRepository;
import school.sptech.ido.repository.UsuarioRepository;
import school.sptech.ido.repository.entity.EtiquetaEntity;
import school.sptech.ido.repository.entity.TarefaEntity;
import school.sptech.ido.repository.entity.UsuarioEntity;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class EtiquetaController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioController usuarioController;

    @GetMapping("/usuarios/{idUsuario}/etiquetas")
    public ResponseEntity<List<EtiquetaDto>> listarEtiquetas(@PathVariable Integer idUsuario) {
        usuarioController.isUsuarioAutenticado(idUsuario);

        List<EtiquetaEntity> etiquetas = etiquetaRepository.findByFkUsuario(idUsuario);

        if (etiquetas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(etiquetas.stream().map(EtiquetaDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/etiquetas")
    public ResponseEntity<List<EtiquetaDto>> buscarPorIdTarefa(
       @PathVariable Integer idUsuario,
       @PathVariable Integer idTarefa
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

        List<EtiquetaEntity> etiquetas = etiquetaRepository.findByFkUsuario(idUsuario);

        if (etiquetas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(etiquetas.stream().map(EtiquetaDto::new).collect(Collectors.toList()));
    }

    @PostMapping("/usuarios/{idUsuario}/etiquetas")
    public ResponseEntity<EtiquetaDto> salvarEtiqueta(
        @PathVariable Integer idUsuario,
        @RequestBody @Valid EtiquetaDto etiquetaDto
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

        Optional<UsuarioEntity> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isPresent()){
            UsuarioEntity usuarioEncontrado = usuario.get();
            EtiquetaEntity etiquetaSalva =
                etiquetaRepository.save(new EtiquetaEntity(etiquetaDto, usuarioEncontrado));
            return ResponseEntity.status(201).body(new EtiquetaDto(etiquetaSalva));
        }

        return ResponseEntity.status(401).build();
    }

    @DeleteMapping("/usuarios/{idUsuario}/etiquetas/{idEtiqueta}")
    public ResponseEntity<Void> deletarEtiquetaPorId(
        @PathVariable Integer idUsuario,
        @PathVariable Integer idEtiqueta
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

        Optional<EtiquetaEntity> etiqueta = etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta);
        if (etiqueta.isPresent()){
            etiquetaRepository.deletaAllEtiquetaById(idEtiqueta);
            etiquetaRepository.deleteById(idEtiqueta);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/etiquetas/{idEtiqueta}")
    public ResponseEntity<List<EtiquetaDto>> associarEtiquetaPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa,
            @PathVariable Integer idEtiqueta
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

        Optional<TarefaEntity> tarefa = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);
        if (tarefa.isPresent()){
            TarefaEntity tarefaEncontrada = tarefa.get();
            Optional<EtiquetaEntity> etiqueta = etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta);
            if (etiqueta.isPresent()){
                EtiquetaEntity etiquetaEncontrada = etiqueta.get();
                etiquetaRepository.saveEtiquetaTarefa(idTarefa, idEtiqueta);
                return ResponseEntity.status(201).build();
            }
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuarios/{idUsuario}/tarefas/{idTarefa}/etiquetas/{idEtiqueta}")
    public ResponseEntity<Void> desassociarEtiquetaPorIdTarefa(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTarefa,
            @PathVariable Integer idEtiqueta
    ){
        usuarioController.isUsuarioAutenticado(idUsuario);

        Optional<TarefaEntity> tarefa = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);
        if (tarefa.isPresent()){
            TarefaEntity tarefaEncontrada = tarefa.get();
            Optional<EtiquetaEntity> etiqueta = etiquetaRepository.findByFkUsuarioAndIdEtiqueta(idUsuario, idEtiqueta);
            if (etiqueta.isPresent()){
                EtiquetaEntity etiquetaEncontrada = etiqueta.get();
                etiquetaRepository.deleteEtiquetaByIdAndIdTarefa(idTarefa, idEtiqueta);
                return ResponseEntity.status(200).build();
            }
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.notFound().build();
    }
}
