package school.sptech.ido.api;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioApi {

    public static void main(String[] args) {
        String ACCOUNT_SID = "";
        String AUTH_TOKEN = "";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+5511989970024"),
                        "MGe64e8c82b97affee4a8dfd95410cd73c",
                        "")
                .create();

        System.out.println(message.getSid());
    }
}
