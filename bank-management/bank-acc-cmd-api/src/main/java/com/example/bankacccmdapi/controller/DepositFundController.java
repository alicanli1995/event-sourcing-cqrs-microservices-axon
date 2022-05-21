package com.example.bankacccmdapi.controller;

import com.example.bankacccmdapi.commands.DepositFundsCommand;
import com.example.bankcore.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;

@RestController
@RequestScope
@CrossOrigin
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/depositFunds")
public class DepositFundController {

    private final CommandGateway commandGateway;

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> depositFunds(@PathVariable("id") String id,
                                                     @RequestBody @Valid DepositFundsCommand command){
        try {
            command.setId(id);
            commandGateway.send(command).get();
            return new ResponseEntity<>(new BaseResponse("Account deposit successfully."), HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Error while process deposit request for id ->" + id;
            log.error(safeError);
            return new ResponseEntity<>(new BaseResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
