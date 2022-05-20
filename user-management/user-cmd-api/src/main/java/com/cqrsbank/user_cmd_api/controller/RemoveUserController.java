package com.cqrsbank.user_cmd_api.controller;

import com.cqrsbank.user_cmd_api.commands.RemoveUserCommand;
import com.cqrsbank.user_cmd_api.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/api/v1/deleteUser")
@RequiredArgsConstructor
public class RemoveUserController {
    private final CommandGateway commandGateway;

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> deleteUser(@PathVariable("id") String id){
        try {
            commandGateway.send(new RemoveUserCommand(id));
            return new ResponseEntity<>(new BaseResponse("User successfully deleted ... "),HttpStatus.OK);

        }
        catch (Exception e){
            var safeError = "Error while process delete user request for id ->" + id;
            log.error(safeError);
            return new ResponseEntity<>(new BaseResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
