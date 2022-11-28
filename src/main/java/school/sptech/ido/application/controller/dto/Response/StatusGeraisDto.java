package school.sptech.ido.application.controller.dto.Response;

public class InfoAdicionaisPerfilDto {

    private Long qtdTarefas;

    private Long qtdTarefasPendentes;

    private Long qtdTarefasConcluidas;

    public InfoAdicionaisPerfilDto(Long qtdTarefas, Long qtdTarefasPendentes, Long qtdTarefasConcluidas) {
        this.qtdTarefas = qtdTarefas;
        this.qtdTarefasPendentes = qtdTarefasPendentes;
        this.qtdTarefasConcluidas = qtdTarefasConcluidas;
    }

    public Long getQtdTarefas() {
        return qtdTarefas;
    }

    public void setQtdTarefas(Long qtdTarefas) {
        this.qtdTarefas = qtdTarefas;
    }

    public Long getQtdTarefasPendentes() {
        return qtdTarefasPendentes;
    }

    public void setQtdTarefasPendentes(Long qtdTarefasPendentes) {
        this.qtdTarefasPendentes = qtdTarefasPendentes;
    }

    public Long getQtdTarefasConcluidas() {
        return qtdTarefasConcluidas;
    }

    public void setQtdTarefasConcluidas(Long qtdTarefasConcluidas) {
        this.qtdTarefasConcluidas = qtdTarefasConcluidas;
    }
}
