package org.example;

public class sms_messages {
    private String telephone;
    private String message;

    public void sendSMS(String telephone, String message){
        System.out.println(telephone + "  " + message);
    }

}
