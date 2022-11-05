package school.sptech.ido.service.subject;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import school.sptech.ido.application.controller.dto.TarefaDto;
import school.sptech.ido.application.controller.dto.UsuarioDto;
import school.sptech.ido.resources.repository.entity.TarefaEntity;
import school.sptech.ido.resources.repository.entity.UsuarioEntity;
import school.sptech.ido.service.email.EmailService;
import school.sptech.ido.service.observer.ApplicationObsever;

import java.time.LocalDate;
import java.util.List;

public class UsuarioSubject {

    private UsuarioDto usuario;
    private List<TarefaDto> tarefas;


    private ApplicationObsever observer = new ApplicationObsever();


    public UsuarioSubject(UsuarioDto usuario, List<TarefaDto> tarefas) {
        this.usuario = usuario;
        this.tarefas = tarefas;
    }

    public void verificarData(LocalDate dataAtual){
        Boolean hasAtividadesProximas = false;

        for (TarefaDto tarefa: tarefas) {
            if(datasProximas(dataAtual, tarefa.getDataFinal())){
                hasAtividadesProximas = true;
//                observer.notificarPorEmail(tarefa.getDataFinal());
            }
        }

        if (!hasAtividadesProximas) {
            System.out.println(String.format(
                    "O usuario  %s não possui nenhuma atividade proxima",
                    usuario.getNome()
            ));
        }

    }

    private boolean datasProximas(LocalDate dataAtual, LocalDate dataTarefa){
        LocalDate dataTarefaFinalMenos10Dias = dataTarefa.minusDays(11);

        if ( (dataAtual.isAfter(dataTarefaFinalMenos10Dias)
                        && dataAtual.isBefore(dataTarefa)) ){
            return true;
        }

        return true;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public List<TarefaDto> getTarefas() {
        return tarefas;
    }

    @Override
    public String toString() {
        return "UsuarioSubject{" +
                "usuario=" + usuario +
                ", observer=" + observer +
                '}';
    }
}
