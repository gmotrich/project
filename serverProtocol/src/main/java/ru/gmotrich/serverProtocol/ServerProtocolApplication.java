package ru.gmotrich.serverProtocol;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ServerProtocolApplication {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-server.xml");
        ServerService server = (ServerService) context.getBean("serverService");

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
