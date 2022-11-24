package school.sptech.ido.application.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.ido.application.controller.dto.EtiquetaExportacaoDto;
import school.sptech.ido.application.controller.dto.Response.UsuarioDto;
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



    @PostMapping("/usuarios/{idUsuario}/exportacao/grava/csv/{nomeArq}")
    public ResponseEntity<Resource> gravar(@PathVariable Integer idUsuario, @PathVariable String nomeArq) throws IOException {

        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);

        if(isAutenticado){
            List<TarefaEntity> tarefas = tarefaRepository.findByFkUsuario(idUsuario);

            List<TarefaExportacaoDto> tarefasExportacao = new ArrayList<>();

            if(tarefas.isEmpty()){
                return ResponseEntity.status(204).build();
            }

            for (TarefaEntity tarefa : tarefas) {
                List<SubTarefaExportacaoDto> subTarefaExportacaoDtos = subTarefaRepository.getSubTarefaExportacaoDto(tarefa.getIdTarefa());
                List<EtiquetaExportacaoDto> etiquetasExportacao = etiquetaRepository.getEtiquestaExportacaoDto(tarefa.getIdTarefa());

                tarefasExportacao.add(new TarefaExportacaoDto(
                        tarefa,
                        subTarefaExportacaoDtos,
                        etiquetasExportacao.size() == 1 ? etiquetasExportacao.get(0).getTitulo() : null,
                        etiquetasExportacao.size() == 2 ? etiquetasExportacao.get(1).getTitulo() : null));

            }

            Exportacao.gravarCsv(tarefasExportacao, nomeArq);

            File path = new File(nomeArq + ".csv");

            return ResponseEntity.status(200).body(new ByteArrayResource(Files.readAllBytes(path.toPath())));

        }

        return ResponseEntity.status(403).build();
    }

    @PostMapping("/usuarios/{idUsuario}/exportacao/grava/txt/{nomeArq}")
    public ResponseEntity<Resource> gravarTxt(@PathVariable Integer idUsuario, @PathVariable String nomeArq) throws IOException {
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);

        if (isAutenticado){

            List<TarefaEntity> tarefas = tarefaRepository.findByFkUsuario(idUsuario);

            if (tarefas.isEmpty()){
                return ResponseEntity.noContent().build();
            }

            UsuarioDto usuario = usuarioController.getUsuarioDto(idUsuario);

            List<TarefaExportacaoDto> tarefasExportacao = new ArrayList<>();

            for (TarefaEntity tarefa: tarefas) {
                List<SubTarefaExportacaoDto> subTarefaExportacaoDtos = subTarefaRepository.getSubTarefaExportacaoDto(tarefa.getIdTarefa());
                List<EtiquetaExportacaoDto> etiquetasExportacao = etiquetaRepository.getEtiquestaExportacaoDto(tarefa.getIdTarefa());


                tarefasExportacao.add(new TarefaExportacaoDto(
                        tarefa,
                        subTarefaExportacaoDtos,
                        etiquetasExportacao.size() == 1 ? etiquetasExportacao.get(0).getTitulo() : null,
                        etiquetasExportacao.size() == 2 ? etiquetasExportacao.get(1).getTitulo() : null));
            }

            Exportacao.gravaArquivoTxt(tarefasExportacao, usuario.getNome(), nomeArq);


            File path = new File(nomeArq + ".txt");

            return ResponseEntity.status(200).body(new ByteArrayResource(Files.readAllBytes(path.toPath())));


        }
        return ResponseEntity.status(403).build();
    }

    @PostMapping("/usuarios/{idUsuario}/exportacao/le/txt/{nomeArq}")
    public ResponseEntity<Void> lerTxt(@PathVariable Integer idUsuario, @PathVariable String nomeArq){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);

        if (isAutenticado){
            Exportacao.leArquivoTxt(nomeArq);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(403).build();
    }



}
