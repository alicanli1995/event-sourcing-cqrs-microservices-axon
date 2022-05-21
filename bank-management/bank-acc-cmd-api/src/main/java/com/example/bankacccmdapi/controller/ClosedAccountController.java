package com.example.bankacccmdapi.controller;


import com.example.bankacccmdapi.commands.CloseAccCommand;
import com.example.bankacccmdapi.commands.OpenAccCommand;
import com.example.bankacccmdapi.dto.OpenAccountResponse;
import com.example.bankcore.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@CrossOrigin
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/closedAccount")
public class ClosedAccountController {

    private final CommandGateway commandGateway;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> openAcc(@PathVariable("id") String id){
        try {
            var command = CloseAccCommand.builder()
                    .id(id)
                    .build();
            commandGateway.send(command);
            return new ResponseEntity<>(new BaseResponse("Account closed successfully."), HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Error while process DELETE account request";
            log.error(safeError);
            return new ResponseEntity<>(new BaseResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
