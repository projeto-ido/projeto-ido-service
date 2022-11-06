package school.sptech.ido.service.subject;

import school.sptech.ido.application.controller.dto.Response.TarefaDto;
import school.sptech.ido.application.controller.dto.Response.UsuarioDto;

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
                observer.notificarPorEmail(tarefa.getDataFinal());
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
        LocalDate dataTarefaFinalMenos10Dias = dataTarefa.minusDays(10);

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
