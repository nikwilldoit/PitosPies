package com.nikolas.app.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class SMSService {

    public static final String ACCOUNT_SID = "xxx";

    public static final String AUTH_TOKEN = "xxx";

    public void send(String from, String to, String sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message.creator(
                        new com.twilio.type.PhoneNumber(to),
                        new com.twilio.type.PhoneNumber(from),
                        sms)
                .create();
    }

}
