package school.sptech.ido.application.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.controller.dto.Request.EtiquetaCadastroTarefaDto;
import school.sptech.ido.application.controller.dto.Request.SubTarefaCadastroDto;
import school.sptech.ido.application.controller.dto.Request.TarefaAtualizacaoDto;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.application.controller.dto.Response.SubTarefaDto;
import school.sptech.ido.application.controller.dto.Request.TarefaCadastroDto;
import school.sptech.ido.application.controller.dto.Response.TarefaDto;
import school.sptech.ido.domain.model.ListaTarefas;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.SubTarefaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Tarefa", description = "Responsável por gerir as Tarefas do usuários.")
@RestController
public class TarefaController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    SubTarefaRepository subTarefaRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

    @Autowired
    UsuarioController usuarioController;


    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Não há Tarefas cadastradas.", content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(responseCode = "200", description = "Tarefas encontradas."),
            @ApiResponse(responseCode = "201", description = "Tarefa cadastrada."),
            @ApiResponse(responseCode = "401", description = "O conteúdo não é autorizado."),
            @ApiResponse(responseCode = "403", description = "O conteúdo dessa página é proibido.")
        })


        @GetMapping("/usuarios/{idUsuario}/tarefas")
        public ResponseEntity<List<TarefaDto>> listarTarefasPorIdUsuario(@PathVariable Integer idUsuario){

            Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
            if (isAutenticado){
                List<TarefaEntity> tarefas = tarefaRepository.findByFkUsuario(idUsuario);

            if (tarefas.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            List<TarefaDto> tarefaDtos = new ArrayList<>();
                for (TarefaEntity tarefa: tarefas) {
                    List<SubTarefaDto> subTarefaDtos = subTarefaRepository.getSubtarefasDto(tarefa.getIdTarefa());
                    List<EtiquetaDto> etiquetaDtos = etiquetaRepository.getEtiquetasDto(tarefa.getIdTarefa());

                    tarefaDtos.add(new TarefaDto(tarefa, subTarefaDtos, etiquetaDtos));
                }

            return ResponseEntity.ok().body(tarefaDtos);
        }

        return ResponseEntity.status(403).build();


    }

    @GetMapping("/usuarios/{idUsuario}/tarefas/ordenado")
    public ResponseEntity<List<TarefaDto>> listarTarefasPorIdUsuarioOrdenadas(@PathVariable Integer idUsuario){


        List<TarefaEntity> tarefas = tarefaRepository.findByFkUsuario(idUsuario);

        if (tarefas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        ListaTarefas listaTarefas = new ListaTarefas(tarefas.size());

        for (TarefaEntity tarefa: tarefas){
            listaTarefas.adiciona(tarefa);
        }

        listaTarefas.ordenar();

        List<TarefaDto> tarefasDtos = new ArrayList<TarefaDto>();

        for (int i = 0; i < listaTarefas.getTamanho(); i++) {
            TarefaEntity tarefaEntity = listaTarefas.getElemento(i);

            List<SubTarefaDto> subTarefaDtos = subTarefaRepository.getSubtarefasDto(tarefaEntity.getIdTarefa());
            List<EtiquetaDto> etiquetaDtos = etiquetaRepository.getEtiquetasDto(tarefaEntity.getIdTarefa());

            tarefasDtos.add(new TarefaDto(tarefaEntity, subTarefaDtos, etiquetaDtos));
        }

        return ResponseEntity.ok().body(tarefasDtos);
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
                List<SubTarefaDto> subTarefaDtos = subTarefaRepository.getSubtarefasDto(idTarefa);

                List<EtiquetaDto> etiquetaDtos = etiquetaRepository.getEtiquetasDto(idTarefa);

                TarefaDto tarefaDto = new TarefaDto(tarefaEntity.get(), subTarefaDtos, etiquetaDtos);

                return ResponseEntity.ok().body(tarefaDto);
            }

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.status(403).build();


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

                for ( SubTarefaCadastroDto subTarefaCadastroDto: tarefaCadastroDto.getSubTarefas()) {
                    subTarefaRepository.save(new SubTarefaEntity(subTarefaCadastroDto, tarefaSalva));
                }

                for (EtiquetaCadastroTarefaDto etiquetaCadastroTarefaDto: tarefaCadastroDto.getEtiquetas()) {
                    Optional<EtiquetaEntity> etiqueta = etiquetaRepository
                            .findById(etiquetaCadastroTarefaDto.getIdEtiqueta());

                    if (etiqueta.isPresent()){
                        EtiquetaEntity etiquetaEntity = etiqueta.get();
                        List<TarefaEntity> tarefas = etiquetaEntity.getTarefa();
                        List<EtiquetaEntity> etiquetasTarefa = tarefaSalva.getEtiquetasTarefa();
                        etiquetasTarefa.add(etiquetaEntity);
                        tarefaSalva.setEtiquetasTarefa(etiquetasTarefa);
                        tarefas.add(tarefaSalva);
                        etiquetaEntity.setTarefa(tarefas);
                        etiquetaRepository.save(etiquetaEntity);
                    }
                }

                List<SubTarefaDto> subTarefaDtos = subTarefaRepository.getSubtarefasDto(tarefaSalva.getIdTarefa());

                List<EtiquetaDto> etiquetaDtos = etiquetaRepository.getEtiquetasDto(tarefaSalva.getIdTarefa());

                return ResponseEntity.status(201).body(new TarefaDto(tarefaSalva, subTarefaDtos, etiquetaDtos));
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
        @RequestBody @Valid TarefaAtualizacaoDto tarefaAtualizadaDto
    ){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);
        if (isAutenticado){
            Optional<TarefaEntity> tarefaEntity = tarefaRepository.findByFkUsuarioAndIdTarefa(idUsuario, idTarefa);

            if (tarefaEntity.isPresent()){
                TarefaEntity tarefaEncontrada = tarefaEntity.get();

                List<EtiquetaEntity> etiquetas = new ArrayList<>();
                List<SubTarefaEntity> subTarefas = new ArrayList<>();

                tarefaEncontrada.setTitulo(tarefaAtualizadaDto.getTitulo());
                tarefaEncontrada.setDescricao(tarefaAtualizadaDto.getDescricao());
                tarefaEncontrada.setStatus(tarefaAtualizadaDto.getStatus());
                tarefaEncontrada.setDataInicio(tarefaAtualizadaDto.getDataInicio());
                tarefaEncontrada.setDataFinal(tarefaAtualizadaDto.getDataFinal());
                tarefaEncontrada.setDataConclusao(tarefaAtualizadaDto.getDataConclusao());
                tarefaEncontrada.setUrgencia(tarefaAtualizadaDto.getUrgencia());
                tarefaEncontrada.setImportancia(tarefaAtualizadaDto.getImportancia());

                if (!tarefaAtualizadaDto.getEtiquetas().isEmpty()){
                    for (EtiquetaCadastroTarefaDto etiquetaDto: tarefaAtualizadaDto.getEtiquetas()) {
                        Optional<EtiquetaEntity> etiqueta = etiquetaRepository
                                .findById(etiquetaDto.getIdEtiqueta());

                        if (etiqueta.isPresent()){
                            EtiquetaEntity etiquetaEntity = etiqueta.get();
                            etiquetas.add(etiquetaEntity);
                        }
                    }
                }

                tarefaEncontrada.setEtiquetasTarefa(etiquetas);

                if(!tarefaAtualizadaDto.getSubTarefas().isEmpty()){
                    for ( SubTarefaDto subTarefa: tarefaAtualizadaDto.getSubTarefas()) {
                        SubTarefaEntity sub = subTarefaRepository.save(new SubTarefaEntity(subTarefa, tarefaEncontrada));
                        subTarefas.add(sub);
                    }
                    tarefaEncontrada.setSubTarefas(subTarefas);
                }

                TarefaEntity tarefaAtualizada = tarefaRepository.save(tarefaEncontrada);

                List<SubTarefaDto> subTarefaDtos = subTarefaRepository.getSubtarefasDto(idTarefa);
                List<EtiquetaDto> etiquetaDtos = etiquetaRepository.getEtiquetasDto(idTarefa);

                return ResponseEntity.ok().body(new TarefaDto(tarefaAtualizada, subTarefaDtos, etiquetaDtos));
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
