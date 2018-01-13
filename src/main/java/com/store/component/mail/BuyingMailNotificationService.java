package com.store.component.mail;

import com.store.database.model.Booking;
import com.store.database.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class BuyingMailNotificationService {

//    private final static Logger logger = LogManager.getLogger(BuyingMailNotificationService.class);

    private final JavaMailSender mailSender;
//    private final SimpleMailMessage simpleMailMessage;

    @Autowired
    public BuyingMailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
//    }  @Autowired
//    public BuyingMailNotificationService(JavaMailSender mailSender, SimpleMailMessage simpleMailMessage) {
//        this.mailSender = mailSender;
//        this.simpleMailMessage = simpleMailMessage;
//    }


    public void notice(User user, Booking booking) {

        String sentText;

        if (user.getName() != null)
            sentText = NOTICE.replace("#user", user.getName());
        else
            sentText = NOTICE.replace("#user", user.getUsername());

        sentText = sentText.replace("#order", Long.toString(booking.getId()));



        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("webstoredavid@gmail.com");
        message.setSubject("Yes");
        message.setTo(user.getMail());
        message.setText(sentText);

        mailSender.send(message);
    }

    private final static String NOTICE = "Hello #user!\n " +
            "Thanks for shopping with us!\n" +
            "Please control your order, you can do it in your account #link, other way you can do it on page #link2" +
            "Your order number is #order.";

}
