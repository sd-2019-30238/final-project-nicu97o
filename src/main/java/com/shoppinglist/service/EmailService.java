package com.shoppinglist.service;


import javax.mail.MessagingException;

public interface EmailService {
    void sendMail(String mailOfTheReceiver, String subject, String message) throws MessagingException;
}
