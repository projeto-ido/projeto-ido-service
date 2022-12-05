package school.sptech.ido.api;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.ido.resources.repository.TarefaRepository;
import school.sptech.ido.resources.repository.entity.TarefaEntity;

import java.time.LocalDate;
import java.util.List;

@Service
public class TwilioService {

    @Autowired
    TarefaRepository tarefaRepository;

    public String enviarMensagem(int diasFaltando) {
        List<TarefaEntity> tarefasComDataFinal = tarefaRepository.getQtdTarefasComDataFinal(
            LocalDate.now().plusDays(diasFaltando)
        );

        Twilio.init(
            "AC1e44cb741107923533c059559d4855ad",
            "d38d9c9dee7a7403139e16605038ec97"
        );

        for (TarefaEntity t : tarefasComDataFinal) {
            String body = tarefasComDataFinal.size() > 0 ? " daqui " + diasFaltando + " dia(s)!" : " hoje!";

            Message message = Message.creator(
                new com.twilio.type.PhoneNumber(t.getUsuario().getTelefone()),
                "MGe64e8c82b97affee4a8dfd95410cd73c",
                "Você tem " + tarefasComDataFinal + " tarefa(s) para " + body
            ).create();
            System.out.println(message.getSid());
        }

        return "Mensagem enviada";
    }
}
