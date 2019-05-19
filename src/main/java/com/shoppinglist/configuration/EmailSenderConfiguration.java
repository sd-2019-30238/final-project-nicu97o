package com.shoppinglist.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource({"mail.properties"})
public class EmailSenderConfiguration {
    private Environment environment;

    @Autowired
    public EmailSenderConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));
        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", environment.getProperty("spring.mail.properties.mail.transport.protocol"));
        properties.put("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
        properties.put("mail.debug", environment.getProperty("spring.mail.properties.mail.debug"));
        return mailSender;
    }
}
