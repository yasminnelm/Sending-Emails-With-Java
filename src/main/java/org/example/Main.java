package org.example;

import static org.example.App.sendEmail;

public class Main {
    public static void main(String[] args) {
        String emails="DummyEmail1@gmail.com;DummyEmail2@ensa.ac.ma";
        try {
            sendEmail("Emails sent successfully", "Test", emails);
        } catch (EmailInvalidException e) {
            System.err.println("Email sending failed: " + e.getMessage());
        }
    }
}