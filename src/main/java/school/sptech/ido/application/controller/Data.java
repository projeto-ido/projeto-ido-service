package school.sptech.ido.application.controller;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
class Data {
    public LocalDate obterDiaAtual() {
        return LocalDate.now();
    }
}
