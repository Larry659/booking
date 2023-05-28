package com.book.shop.route;

import com.book.shop.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailRoute {
    private final MailService mailService;
    @GetMapping("/send-mail")
    public String sendEmail(){
        mailService.sendMailTest();
        return "successful";
    }
}
