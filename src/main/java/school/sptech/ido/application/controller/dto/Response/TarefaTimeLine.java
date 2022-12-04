package school.sptech.ido.application.controller.dto.Response;

import school.sptech.ido.resources.repository.entity.TarefaEntity;
import java.time.LocalDateTime;

public class TarefaTimeLine {


    private Boolean importancia;

    private Boolean urgencia;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFinal;

    public TarefaTimeLine(TarefaEntity tarefa) {
        this.importancia = tarefa.getImportancia();
        this.urgencia = tarefa.getUrgencia();
        this.dataInicio = LocalDateTime.from(tarefa.getDataInicio());
        this.dataFinal = LocalDateTime.from(tarefa.getDataFinal());
    }

    public String getPrioridade(){
        if (importancia && urgencia)
            return "Fazer Agora";
        else if (!importancia && urgencia)
            return "Delegar";
        else if (importancia && !urgencia)
            return "Agendar";
        else
            return "Não Priorizar";
    }

    public void setUrgencia(Boolean urgencia) {
        this.urgencia = urgencia;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }
}
