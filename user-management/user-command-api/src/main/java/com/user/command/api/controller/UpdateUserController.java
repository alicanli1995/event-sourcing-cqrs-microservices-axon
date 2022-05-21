package com.cqrsbank.controller;


import com.cqrsbank.commands.UpdateUserCommand;
import com.cqrsbank.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Log4j2
@RequestMapping("/api/v1/updateUser")
@RequiredArgsConstructor
public class UpdateUserController {

    private final CommandGateway commandGateway;

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE')")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable("id") String  id ,
                                                   @Valid @RequestBody UpdateUserCommand updateUserCommand){
        try{
            updateUserCommand.setId(id);
            commandGateway.send(updateUserCommand);
            return new ResponseEntity<>(new BaseResponse("User successfully updated ... "),HttpStatus.OK);
        }catch (Exception e){
            var safeError = "Error while process update user request for id ->" + id;
            log.error(safeError);
            return new ResponseEntity<>(new BaseResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
