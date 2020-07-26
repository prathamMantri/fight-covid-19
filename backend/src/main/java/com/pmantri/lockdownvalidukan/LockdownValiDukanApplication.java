package com.pmantri.lockdownvalidukan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
public class LockdownValiDukanApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

    public LockdownValiDukanApplication() {
        super();
        setRegisterErrorPageFilter(false);
    }

    public static void main(String[] args) {
        SpringApplication.run(LockdownValiDukanApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LockdownValiDukanApplication.class);
    }

}
