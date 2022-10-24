package school.sptech.ido.api;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioApi {

    public static void main(String[] args) {
        String ACCOUNT_SID = "AC1e44cb741107923533c059559d4855ad";
        String AUTH_TOKEN = "d00ca5fd613ac59e33a7adf8727229d3";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+5511994437209"),
                        "MGe64e8c82b97affee4a8dfd95410cd73c",
                        "Bjiadsjfiasjio")
                .create();

        System.out.println(message.getSid());
    }
}
