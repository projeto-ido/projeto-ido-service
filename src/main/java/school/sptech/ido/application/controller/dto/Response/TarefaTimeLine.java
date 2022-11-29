package school.sptech.ido.application.controller.dto.Response;

import school.sptech.ido.resources.repository.entity.TarefaEntity;

import java.time.LocalDate;

public class TarefaTimeLine {


    private Boolean importancia;

    private Boolean urgencia;

    private LocalDate dataInicio;

    private LocalDate dataFinal;

    public TarefaTimeLine(TarefaEntity tarefa) {
        this.importancia = tarefa.getImportancia();
        this.urgencia = tarefa.getUrgencia();
        this.dataInicio = LocalDate.from(tarefa.getDataInicio());
        this.dataFinal = LocalDate.from(tarefa.getDataFinal());
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
}
