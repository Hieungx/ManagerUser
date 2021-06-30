package com.example.demo.utils;

import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Component
public class SendMailHelper {
    String to = "hieuu99@gmail.com";

    // Add sender
    String from = "hvt311097@gmail.com";
    final String username = "hvt311097@gmail.com";//your Gmail username
    final String password = "truong311097";//your Gmail password

    String host = "smtp.gmail.com";

    @SneakyThrows
    public void sendEmail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        //Create a new message
        MimeMessage msg = new MimeMessage(session);
        //Set from address
        msg.setFrom(new InternetAddress(from));
        //Setting the "to recipients"
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
        msg.setSubject("Send mail attach file");
        MimeBodyPart mbp1 = new MimeBodyPart();
        mbp1.setText("Hello " + to);
        MimeBodyPart mbp2 = new MimeBodyPart();
        File temp = null;
        File temp1 = null;

        temp = new File("D:/test.xlsx");
        temp1 = new File("C:/Users/nthieu10/Downloads/users_2021-06-30.xlsx");
        boolean ch = temp.createNewFile();
        FileDataSource fds = new FileDataSource(temp1);
        mbp2.setDataHandler(new DataHandler(fds));
        mbp2.setFileName(fds.getName());
        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp1);
        mp.addBodyPart(mbp2);
        msg.setContent(mp);
        msg.saveChanges();
// Set the Date: header
        msg.setSentDate(new java.util.Date());
        Transport.send(msg);
    }
}

