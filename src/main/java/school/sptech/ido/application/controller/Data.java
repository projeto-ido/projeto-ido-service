package school.sptech.ido.application.controller;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
class Data {
    public LocalDateTime obterDiaAtual() {
        return LocalDateTime.now();
    }
}
