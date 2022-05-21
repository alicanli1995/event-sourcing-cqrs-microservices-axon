package com.user.command.api;

import com.example.usercore.config.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@Import({AxonConfig.class})
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserCmdApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCmdApiApplication.class, args);
    }

}