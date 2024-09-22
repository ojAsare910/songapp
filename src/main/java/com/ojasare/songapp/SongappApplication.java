package com.ojasare.songapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SongappApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));
        System.out.println("SERVER_PORT: " + System.getenv("SERVER_PORT"));
        SpringApplication.run(SongappApplication.class, args);
    }

}
