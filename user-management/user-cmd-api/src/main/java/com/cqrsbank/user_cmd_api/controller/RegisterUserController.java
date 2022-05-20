package com.cqrsbank.user_cmd_api.controller;

import com.cqrsbank.user_cmd_api.commands.RegisterUserCommand;
import com.cqrsbank.user_cmd_api.dto.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/registerUser")
public class RegisterUserController {

    private final CommandGateway commandGateway;


    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<RegisterUserResponse> saveUser(@RequestBody @Valid RegisterUserCommand registerUserCommand){
        var uuid = UUID.randomUUID().toString();
        registerUserCommand.setId(uuid);

        try {
            commandGateway.send(registerUserCommand);
            return new ResponseEntity<>
                    (new RegisterUserResponse(uuid,"User was successfully registered"),HttpStatus.CREATED);

        } catch (Exception e){
            var safeError = "Error while process register user request for id ->" + uuid;
            log.error(safeError);
            return new ResponseEntity<>(new RegisterUserResponse(uuid,safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
