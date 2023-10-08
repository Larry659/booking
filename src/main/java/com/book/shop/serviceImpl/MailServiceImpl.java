package com.book.shop.serviceImpl;

import com.book.shop.dto.BookingRequest;
import com.book.shop.model.Accounts;
import com.book.shop.repo.AccountRepo;
import com.book.shop.service.MailService;
import com.book.shop.service.ThymleafService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSender mailSender;
    private final AccountRepo accountRepo;

    @Autowired
    ThymleafService thymleafService;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Override
    public void sendMailTest() {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper( message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setFrom(fromEmail);
            helper.setTo("larryfunmbi26@gmail.com");
            helper.setText(thymleafService.createContent( "hello.html",null),true);
            helper.setCc("kunlebalo247@gmail.com");
//            helper.setText("Hello Kunle, This is just a test of the email.");
            helper.setSubject("Just Test with Template");
            mailSender.send(message);
        }catch(Exception e){
        e.printStackTrace();
        }

    }

    @Override
    public void sendMailForBooking(BookingRequest payload) {
        Accounts acct =accountRepo.findAccountsByEmail(payload.getEmail()).get();
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper( message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(payload.getEmail());
           Map<String, Object> variables = new HashMap<>();
           variables.put("name",acct.getFirstName());
           variables.put("email",payload.getEmail());
           variables.put("full_name",acct.getFirstName() + " " + acct.getLastName());
           variables.put("last_name",acct.getLastName());
           variables.put("phone",payload.getPhone());
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            variables.put("appointment_date",LocalDate.parse(payload.getAppointmentDate()));
            helper.setText(thymleafService.createContent( "hello.html",variables),true);
            helper.setFrom(fromEmail);
            helper.setSubject("Welcome Email");
            mailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
