package school.sptech.ido.application.controller.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DiaSemana {

    LocalDateTime diaDaSemana;

    Long qtdConcluidas;

    public DiaSemana(LocalDateTime diaDaSemana, Long qtdConcluidas) {
        this.diaDaSemana = diaDaSemana;
        this.qtdConcluidas = qtdConcluidas;
    }

    public LocalDateTime getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(LocalDateTime diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public Long getQtdConcluidas() {
        return qtdConcluidas;
    }

    public void setQtdConcluidas(Long qtdConcluidas) {
        this.qtdConcluidas = qtdConcluidas;
    }

}
