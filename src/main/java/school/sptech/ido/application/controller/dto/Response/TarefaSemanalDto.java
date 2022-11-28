package school.sptech.ido.application.controller.dto.Response;

import java.time.LocalDate;

public class TarefaSemanalDto {

    private LocalDate diaSemana;

    private Boolean status;

    public TarefaSemanalDto(LocalDate diaSemana, Boolean status) {
        this.diaSemana = diaSemana;
        this.status = status;
    }

    public LocalDate getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(LocalDate diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
