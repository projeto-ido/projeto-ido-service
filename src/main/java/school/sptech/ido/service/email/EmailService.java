package school.sptech.ido.service.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmailService {


    public static void enviarEmail (String nome, String emial) {
        String menssagem = "<p>Olá " + nome + ", tudo bem?\n\n" +
                "Nós da iDo identificamos que voce possui tarefas proximas \n\n" +
                "Atenciosamente\n" +
                "iDO - " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + "</p>\n\n\n" +
                "<img style='height: 100px;' src='https://i.imgur.com/1hwDkxU.png'>";

        HtmlEmail email = new HtmlEmail();

        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("yan.torquato01@gmail.com", "sfpzrfkomhfoejyf"));
        email.setSSLOnConnect(true);

        try {
            email.setFrom("yan.torquato01@gmail.com");
            email.setSubject("iDo - Tarefas pendentes");
            email.setMsg(menssagem);
            email.addTo(emial);
            email.send();
            System.out.println("Email enviado com sucesso");
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }

    }
}
