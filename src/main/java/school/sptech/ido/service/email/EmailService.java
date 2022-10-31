package school.sptech.ido.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

    public void enviarEmail ()
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);

        helper.setFrom("yan.torquato01@gmail.com", "iDo");
        helper.setTo("yan.torquato01@gmail.com");

        helper.setSubject("Tarefas Proximas do Prazo");
        helper.setText("Olá Yan Hudson, tudo bem?\n\n" +
                "Nós da iDo identificamos que voce possui tarefas proximas para XX/XX/XXXX \n\n" +
                "Atensiosamente\n" +
                "iDO", false);

        mailSender.send(msg);

    }
}
