package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class App {

    public static void sendEmail(String msg, String subject, String emails) throws EmailInvalidException {
        // You can send a singular email to multiples addresses at once
        List<String> emailList = Arrays.asList(emails.split(";"));
        for (String email : emailList) {
            // Looping through all the emails to see if they're all valid
            // If one of the emails or more isn't written in a correct format, no emails are sent
            if (!email.contains("@")) {
                throw new EmailInvalidException("Invalid email: " + email);
            }
        }

        String username = "YourGmailAddress@gmail.com";
        String password = "Your Generated Password";
        // For more information on how and where to generate this password, check README.md

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            for (String to : emailList) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            }
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);
            System.out.println("Email(s) sent successfully.");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}

