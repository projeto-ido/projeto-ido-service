package school.sptech.ido.application.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.controller.dto.EtiquetaExportacaoDto;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.application.controller.dto.SubTarefaExportacaoDto;
import school.sptech.ido.application.controller.dto.TarefaExportacaoDto;
import school.sptech.ido.domain.model.Exportacao;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.SubTarefaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.entity.EtiquetaEntity;
import school.sptech.ido.resources.repository.entity.SubTarefaEntity;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExportacaoController {

    @Autowired
    UsuarioController usuarioController;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    SubTarefaRepository subTarefaRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;



    @PostMapping("/usuarios/{idUsuario}/exportacao/{nomeArq}")
    public ResponseEntity<Resource> gravar(@PathVariable Integer idUsuario, @PathVariable String nomeArq) throws IOException {

        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);

        if(isAutenticado){
            List<TarefaEntity> tarefas = tarefaRepository.findByFkUsuario(idUsuario);

            List<TarefaExportacaoDto> tarefasExportacao = new ArrayList<>();

            if(tarefas.isEmpty()){
                return ResponseEntity.status(204).build();
            }

            for (int i = 0; i < tarefas.size(); i++) {
                TarefaEntity tarefa = tarefas.get(i);

                List<SubTarefaEntity> subtarefas = subTarefaRepository.findAllByIdTarefa(tarefa.getIdTarefa());
                List<EtiquetaDto> etiquetas = etiquetaRepository.getEtiquetasDto(tarefa.getIdTarefa());

                List<SubTarefaExportacaoDto> subTarefaExportacaoDtos = subtarefas.stream().map(SubTarefaExportacaoDto::new).collect(Collectors.toList());
                List<EtiquetaExportacaoDto> etiquetasExportacao = etiquetas.stream().map(EtiquetaExportacaoDto::new).collect(Collectors.toList());


                tarefasExportacao.add(new TarefaExportacaoDto(tarefa ,subTarefaExportacaoDtos, etiquetasExportacao));
            }

            Exportacao.gravarCsv(tarefasExportacao, nomeArq);

            File path = new File(nomeArq + ".csv");

            return ResponseEntity.status(200).body(new ByteArrayResource(Files.readAllBytes(path.toPath())));

        }

        return ResponseEntity.status(403).build();
    }
}
