package com.example.userqueryapi.controller;

import com.example.userqueryapi.dto.UserLookupResponse;
import com.example.userqueryapi.queries.FindAllUsersQuery;
import com.example.userqueryapi.queries.FindUserByIdQuery;
import com.example.userqueryapi.queries.SearchUserQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
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
@RequestMapping("/api/v1/userLookup")
public class UserLookupController {

    private final QueryGateway queryGateway;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponse> getAllUser(){
        try {

            var query = new FindAllUsersQuery();
            var response
                    = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if (response == null || response.getUsers() == null )
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Error while process query request. -> Find All Query";
            log.error(safeError);
            return new ResponseEntity<>(new UserLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponse> getUserById(@PathVariable("id") String id){
        try {

            var query = new FindUserByIdQuery(id);
            var response
                    = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if (response == null || response.getUsers() == null )
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Error while process query request. -> By Id Query";
            log.error(safeError);
            return new ResponseEntity<>(new UserLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/filter/{filter}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookupResponse> searchUserByFilter(@PathVariable("filter") String filter){
        try {

            var query = new SearchUserQuery(filter);
            var response
                    = queryGateway.query(query, ResponseTypes.instanceOf(UserLookupResponse.class)).join();
            if (response == null || response.getUsers() == null )
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Error while process query request. -> Filter Query";
            log.error(safeError);
            return new ResponseEntity<>(new UserLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
