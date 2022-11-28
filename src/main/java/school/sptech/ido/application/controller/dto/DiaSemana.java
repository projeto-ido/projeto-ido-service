package school.sptech.ido.application.controller.dto;

import java.time.LocalDate;

public class DiaSemana {

    LocalDate diaDaSemana;

    Long qtdConcluidas;

    public DiaSemana(LocalDate diaDaSemana, Long qtdConcluidas) {
        this.diaDaSemana = diaDaSemana;
        this.qtdConcluidas = qtdConcluidas;
    }

    public LocalDate getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(LocalDate diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public Long getQtdConcluidas() {
        return qtdConcluidas;
    }

    public void setQtdConcluidas(Long qtdConcluidas) {
        this.qtdConcluidas = qtdConcluidas;
    }

}
