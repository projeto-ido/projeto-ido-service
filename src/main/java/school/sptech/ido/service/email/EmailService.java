package school.sptech.ido.service.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
public class EmailService {


    public static void enviarEmail () {
        String menssagem = "Olá Yan Hudson, tudo bem?\n\n" +
                "Nós da iDo identificamos que voce possui tarefas proximas para XX/XX/XXXX \n\n" +
                "Atensiosamente\n" +
                "iDO";

        SimpleEmail email = new SimpleEmail();

        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("yan.torquato01@gmail.com", "sfpzrfkomhfoejyf"));
        email.setSSLOnConnect(true);

        try {
            email.setFrom("yan.torquato01@gmail.com");
            email.setSubject("iDo - Tarefas pendentes");
            email.setMsg(menssagem);
            email.addTo("yan.hudson23@gmail.com");
            email.send();
            System.out.println("Email enviado com sucesso");
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }

    }
}
