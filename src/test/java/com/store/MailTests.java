package com.store;

import com.store.configuration.MailConfiguration;
import com.store.database.model.Booking;
import com.store.database.model.User;
import com.store.component.mail.BuyingMailNotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MailConfiguration.class,loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("update")
public class MailTests {

    @Autowired
    private BuyingMailNotificationService service;

    @Test
    public void sendTest(){

        User user = new User();

        user.setUsername("david");
        user.setName("david");
        user.setMail("madhawk007@yandex.ru");

        Booking booking = new Booking();
        booking.setId(15L);

        service.notice(user,booking);

    }

}
