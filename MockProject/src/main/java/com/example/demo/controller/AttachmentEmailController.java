package com.example.demo.controller;

import com.example.demo.mail.MyConstants;
import com.example.demo.utils.SendMailHelper;
import lombok.SneakyThrows;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Controller
//@RequestMapping("/SendMail")
public class AttachmentEmailController {

    @Resource
    JavaMailSender mailSender;

    @Resource
    SendMailHelper sendMailHelper;


    // chưa attach đc excel
    @SneakyThrows
    @ResponseBody
    @GetMapping("/sendAttachmentEmail")
    public String sendAttachmentEmail() {

        MimeMessage message = mailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message,multipart);

        helper.setTo(MyConstants.FRIEND_EMAIL);
        helper.setSubject("Send email with attachment");

        String path1 = "C:/Users/nthieu10/Downloads/users_2021-06-30.xlsx";
        String path2 = "D:\\a.txt";

        //Attachment1
        FileSystemResource file1 = new FileSystemResource(new File(path2));
        helper.addAttachment("List User",file1);

        mailSender.send(message);

        return "Email Sent!";
    }

    // Send mail attach excel thành công
    @GetMapping("/SpringSend")
    public String sendMail(){
        sendMailHelper.sendEmail();
        return "Sent Mail Success";
    }
}
