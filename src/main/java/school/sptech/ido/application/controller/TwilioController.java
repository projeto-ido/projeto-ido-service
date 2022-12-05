package school.sptech.ido.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.ido.api.TwilioService;

@RestController
@RequestMapping("/twilio")
public class TwilioController {

    @Autowired
    private TwilioService servico;

    @GetMapping("/{diasFaltando}")
    public ResponseEntity<String> sendMessage(@PathVariable int diasFaltando) {
        return ResponseEntity.ok(servico.enviarMensagem(diasFaltando));
    }
}
