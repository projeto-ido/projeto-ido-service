package school.sptech.ido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.application.controller.dto.Response.SubTarefaDto;
import school.sptech.ido.application.controller.dto.Response.TarefaDto;
import school.sptech.ido.application.controller.dto.Response.UsuarioDto;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.SubTarefaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import school.sptech.ido.service.subject.UsuarioSubject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    SubTarefaRepository subTarefaRepository;

    @Autowired
    EtiquetaRepository etiquetaRepository;

    private List<UsuarioSubject> subjects = new ArrayList();

    public boolean adicionarSubject(Integer id){
        Optional<UsuarioEntity> usuarioOptional = usuarioRepository.findById(id);


        if(usuarioOptional.isPresent()){
            List<TarefaEntity> tarefas = tarefaRepository.findByFkUsuario(id);

            List<TarefaDto> tarefaDtos = new ArrayList<>(25);

            for (TarefaEntity tarefa: tarefas) {
                List<SubTarefaDto> subTarefaDtos = subTarefaRepository.getSubtarefasDto(tarefa.getIdTarefa());
                List<EtiquetaDto> etiquetaDtos = etiquetaRepository.getEtiquetasDto(tarefa.getIdTarefa());

                tarefaDtos.add(new TarefaDto(tarefa, subTarefaDtos, etiquetaDtos));
            }


            UsuarioDto usuarioDto = new UsuarioDto(usuarioOptional.get());

            subjects.add(new UsuarioSubject(usuarioDto, tarefaDtos));
            return true;
        }

        return false;
    }

    public boolean removerSubject(Integer id){
        Optional<UsuarioSubject> usuarioSubjectOptional = subjects.stream().filter(usuarioSubject ->
                usuarioSubject.getUsuario().getIdUsuario().equals(id)).findAny();

        if(usuarioSubjectOptional.isPresent()){
            UsuarioSubject usuarioSubject = usuarioSubjectOptional.get();

            if (subjects.remove(usuarioSubject))
                return true;

            return false;
        }

        return false;
    }

    public void verificarData(LocalDate dataAtual){
        if (!subjects.isEmpty()){
            for (UsuarioSubject usuario : subjects) {
                usuario.verificarData(dataAtual);
            }
        }
        System.out.println("Lista vazia!");
    }


    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }
}
