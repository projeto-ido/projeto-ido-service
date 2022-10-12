package school.sptech.ido.service.observer;

import java.time.LocalDate;

public class ApplicationObsever {

    private LocalDate dataAtual;

    public ApplicationObsever(LocalDate dataAtual) {
        this.dataAtual = dataAtual;
    }

    public LocalDate getDataAtual() {
        return dataAtual;
    }

    public void notificarEmail(LocalDate dataAtual){
        if (dataAtual)
    }
}
