//package school.sptech.ido.api;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.io.UnsupportedEncodingException;
//import java.util.Properties;
//
//public class Email {
//
//    public static void sendEmail(){
//        Properties props = new Properties();
//        Session session = Session.getDefaultInstance(props);
//
//        Message msg = new MimeMessage(session);
//
//        try {
//            msg.setFrom(new InternetAddress("yan.torquato01@gmail.com", "iDo"));
//            msg.setRecipient(Message.RecipientType.TO, new InternetAddress("yan.hudson23@gmail.com"));
//
//            msg.setSubject("Tarefas Proximas do Prazo");
//            msg.setText("Olá Yan Hudson, tudo bem?\n\n" +
//                    "Nós da iDo identificamos que voce possui tarefas proximas para XX/XX/XXXX \n\n" +
//                    "Atensiosamente,\n" +
//                    "iDo");
//
//            System.out.println("mensagem sendo enviada");
//            Transport.send(msg);
//        }
//        catch (AddressException error){
//            System.out.println("Erro ao enviar o email");
//            error.printStackTrace();
//        }
//        catch (MessagingException error){
//            System.out.println("Erro ao sla mensagem");
//            error.printStackTrace();
//        }
//        catch (UnsupportedEncodingException error){
//            System.out.println("Erro na decodificação, não suportado");
//            error.printStackTrace();
//        }
//
//
//
//    }
//
//}
