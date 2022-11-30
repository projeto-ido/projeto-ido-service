package school.sptech.ido.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import school.sptech.ido.application.controller.dto.Response.EtiquetaDto;
import school.sptech.ido.application.controller.dto.Response.SubTarefaDto;
import school.sptech.ido.application.controller.dto.Response.TarefaDto;
import school.sptech.ido.application.controller.dto.Response.UsuarioDto;
import school.sptech.ido.domain.model.FilaObj;
import school.sptech.ido.resources.repository.EtiquetaRepository;
import school.sptech.ido.resources.repository.SubTarefaRepository;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.UsuarioRepository;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import school.sptech.ido.service.subject.UsuarioSubject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @EnableScheduling
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

    private FilaObj<UsuarioSubject> filaNotificacao = new FilaObj<>(50);

    public ResponseEntity<Void> atualizarTarefa(int idUsuario, TarefaDto tarefaDto){
        Optional<UsuarioSubject> usuarioOpt = subjects.stream().filter(usuarioSubject -> usuarioSubject.getUsuario().getIdUsuario() == idUsuario).findAny();

        if (usuarioOpt.isPresent()){
            UsuarioSubject usuarioSubject = usuarioOpt.get();

            usuarioSubject.getTarefas().add(tarefaDto);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(404).build();
    }

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

    public Boolean removerSubject(Integer id){
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

    @Async
    @Scheduled(cron = "0 0 6 * * *", zone = "UTC")
    public void verificarData(){
        if (!subjects.isEmpty()){
            for (UsuarioSubject usuario : subjects) {
               if (usuario.possuiTarefasProximas(LocalDateTime.now()))
                   filaNotificacao.insert(usuario);
            }

            if (filaNotificacao.isEmpty())
                System.out.println("Fila vazia");
            else
                this.notificarUsuario();
        }
        else
            System.out.println("Lista vazia!");
    }

    public void verificarData(LocalDateTime data){
        if (!subjects.isEmpty()){
            for (UsuarioSubject usuario : subjects) {
                if (usuario.possuiTarefasProximas(data))
                    filaNotificacao.insert(usuario);
            }

            if (filaNotificacao.isEmpty())
                System.out.println("Fila vazia");
            else
                this.notificarUsuario();
        }
        else
            System.out.println("Lista vazia!");
    }

    private void notificarUsuario() {
        for (int i = filaNotificacao.getTamanho(); i > 0; i--) {
            filaNotificacao.poll().notificar();
        }
    }


    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }
}
