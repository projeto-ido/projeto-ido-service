package school.sptech.ido.application.controller;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.ido.application.controller.dto.EtiquetaExportacaoDto;
import school.sptech.ido.application.controller.dto.Request.PathImportacao;
import school.sptech.ido.application.controller.dto.Response.UsuarioDto;
import school.sptech.ido.application.controller.dto.SubTarefaExportacaoDto;
import school.sptech.ido.application.controller.dto.TarefaExportacaoDto;
import school.sptech.ido.domain.model.Exportacao;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.SubTarefaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import org.springframework.core.io.Resource;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
public class ExportacaoController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    UsuarioController usuarioController;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    SubTarefaRepository subTarefaRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

    @Autowired
    Exportacao exportacao;



    @PostMapping(value = "/usuarios/{idUsuario}/exportacao/grava/csv/{nomeArq}", produces = "application/csv")
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
                        etiquetasExportacao.size() == 1 || etiquetasExportacao.size() == 2 ?
                                etiquetasExportacao.get(0).getTitulo() : null,
                        etiquetasExportacao.size() == 2 ? etiquetasExportacao.get(1).getTitulo() : null));

            }

            exportacao.gravarCsv(tarefasExportacao, nomeArq);

            File path = new File(nomeArq + ".csv");


            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"relatoria-tarefas.csv\"");
            headers.set(HttpHeaders.CONTENT_TYPE ,"application/csv");

            ByteArrayResource by = new ByteArrayResource(Files.readAllBytes(path.toPath()));

            return new ResponseEntity<>(
                    by,
                    headers,
                    CREATED
                    );

        }

        return ResponseEntity.status(FORBIDDEN).build();
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
                        etiquetasExportacao.size() == 1 || etiquetasExportacao.size() == 2
                                ? etiquetasExportacao.get(0).getTitulo() : null,
                        etiquetasExportacao.size() == 2 ? etiquetasExportacao.get(1).getTitulo() : null));
            }

            exportacao.gravaArquivoTxt(tarefasExportacao, usuario.getNome(), nomeArq);


            File path = new File(nomeArq + ".txt");

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"relatoria-tarefas.txt\"");
            headers.set(HttpHeaders.CONTENT_TYPE ,"application/txt");

            ByteArrayResource by = new ByteArrayResource(Files.readAllBytes(path.toPath()));

            return new ResponseEntity<>(
                    by,
                    headers,
                    CREATED
                    );


        }
        return ResponseEntity.status(FORBIDDEN).build();
    }

    @PostMapping("/usuarios/{idUsuario}/exportacao/le/txt")
    public ResponseEntity<String> lerTxt(@PathVariable Integer idUsuario, @RequestBody PathImportacao path){
        Boolean isAutenticado = usuarioController.isUsuarioAutenticado(idUsuario);

        if (isAutenticado){
            Optional<UsuarioEntity> usuario = usuarioRepository.findById(idUsuario);

            if (!usuario.isPresent())
                   return ResponseEntity.status(NOT_FOUND).body("\"Usuario não encontrado\"");

            exportacao.leArquivoTxt(path.getPath(), usuario.get());

            return ResponseEntity.status(CREATED).build();
        }

        return ResponseEntity.status(FORBIDDEN).build();
    }



}
