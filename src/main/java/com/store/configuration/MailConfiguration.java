package com.store.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:mail.properties")
@ComponentScan("com.store.component.mail")
public class MailConfiguration {

//
//    private final Environment environment;
//
//    @Autowired
//    public MailConfiguration(Environment environment) {
//        this.environment = environment;
//    }
//
//    @Bean
//    public JavaMailSender getJavaMailSender(){
//        Properties properties = new Properties();
//
//        properties.setProperty("host",environment.getProperty("mail.host"));
//        properties.setProperty("port",environment.getProperty("mail.port"));
//        properties.setProperty("username",environment.getProperty("mail.username"));
//        properties.setProperty("password",environment.getProperty("mail.password"));
//        properties.setProperty("protocol",environment.getProperty("mail.protocol"));
//
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setJavaMailProperties(properties);
//
//        return mailSender;
//    }
//
//    @Bean
//    public SimpleMailMessage simpleMailMessage(){
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//
//        simpleMailMessage.setFrom(environment.getProperty("mail.from"));
//        simpleMailMessage.setSubject("Mail subject");
//
//        return simpleMailMessage;
//    }

}
