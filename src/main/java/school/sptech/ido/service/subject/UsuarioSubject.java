package school.sptech.ido.service.subject;

import school.sptech.ido.application.controller.dto.Response.TarefaDto;
import school.sptech.ido.application.controller.dto.Response.UsuarioDto;

import school.sptech.ido.service.observer.ApplicationObsever;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UsuarioSubject {

    private UsuarioDto usuario;
    private List<TarefaDto> tarefas;

    private ApplicationObsever observer = new ApplicationObsever();


    public UsuarioSubject(UsuarioDto usuario, List<TarefaDto> tarefas) {
        this.usuario = usuario;
        this.tarefas = tarefas;
    }

    public Boolean possuiTarefasProximas(LocalDateTime dataAtual){
        Boolean hasAtividadesProximas = false;

        for (TarefaDto tarefa: tarefas) {
            if(datasProximas(dataAtual, tarefa.getDataFinal())){
                return true;
            }
        }

        return false;

    }

    public void notificar(){
        observer.notificarPorEmail(usuario.getNome(), usuario.getEmail());
    }

    private boolean datasProximas(LocalDateTime dataAtual, LocalDateTime dataTarefa){
        LocalDateTime dataTarefaFinalMenos10Dias = dataTarefa.minusDays(10);

        if ( dataAtual.isAfter(dataTarefaFinalMenos10Dias) && dataAtual.isBefore(dataTarefa) ){
            return true;
        }

        return false;
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
