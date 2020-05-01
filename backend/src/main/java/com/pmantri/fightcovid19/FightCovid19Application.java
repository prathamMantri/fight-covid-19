package com.pmantri.fightcovid19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FightCovid19Application {

    public static void main(String[] args) {
        SpringApplication.run(FightCovid19Application.class, args);
    }

}
