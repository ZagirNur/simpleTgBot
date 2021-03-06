package ru.zagamaza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        //Add this line to initialize bots context
        ApiContextInitializer.init();


        SpringApplication.run(Application.class, args);
    }
}