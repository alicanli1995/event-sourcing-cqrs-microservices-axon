package com.example.userqueryapi;

import com.example.usercore.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
public class UserQueryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserQueryApiApplication.class, args);
    }

}
