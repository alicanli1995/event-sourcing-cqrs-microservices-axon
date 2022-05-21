package com.example.bankacccmdapi.controller;

import com.example.bankacccmdapi.commands.OpenAccCommand;
import com.example.bankacccmdapi.dto.OpenAccountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestScope
@CrossOrigin
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/openAccount")
public class OpenAccController {

    private final CommandGateway commandGateway;

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<OpenAccountResponse> openAcc(@RequestBody @Valid OpenAccCommand command){
        var id = UUID.randomUUID().toString();
        command.setId(id);

        try {
            commandGateway.send(command);
            return new ResponseEntity<>(new OpenAccountResponse(id,"Account open successfully."),HttpStatus.CREATED);

        }catch (Exception e){
            var safeError = "Error while process open account request for id ->" + id;
            log.error(safeError);
            return new ResponseEntity<>(new OpenAccountResponse(id,safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
