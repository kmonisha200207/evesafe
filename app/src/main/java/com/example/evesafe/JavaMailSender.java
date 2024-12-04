package com.example.evesafe;
import android.os.AsyncTask;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailSender {
    private String email;
    private String subject;
    private String message;

    public JavaMailSender(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public void sendEmail() {
        AsyncTask.execute(() -> {
            try {
                // Email properties
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                // Email credentials
                final String senderEmail = "your-email@gmail.com";
                final String senderPassword = "your-password";

                // Session
                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

                // Create the message
                Message mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(senderEmail));
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(message);

                // Send the email
                Transport.send(mimeMessage);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
