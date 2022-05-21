package com.example.bankaccqueryapi.controller;


import com.example.bankaccqueryapi.dto.AccountLookupResponse;
import com.example.bankaccqueryapi.dto.EqualityType;
import com.example.bankaccqueryapi.queries.FindAccountByHolderIdQuery;
import com.example.bankaccqueryapi.queries.FindAccountByIdQuery;
import com.example.bankaccqueryapi.queries.FindAccountsWithBalanceQuery;
import com.example.bankaccqueryapi.queries.FindAllAccountsQuery;
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
@RequestMapping("/api/v1/bankAccountLookup")
public class AccountLookupController {

    private final QueryGateway queryGateway;

    @GetMapping(path = "/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAllAccounts(){
        try {
            var accounts = new FindAllAccountsQuery();
            var response =
                    queryGateway.query(accounts, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Failed to query for all accounts get.";
            log.error(safeError);
            return new ResponseEntity<>(new AccountLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccById(@PathVariable("id") String id){

        try {
            var accounts = new FindAccountByIdQuery(id);
            var response =
                    queryGateway.query(accounts, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Failed to query for by id accounts get.";
            log.error(safeError);
            return new ResponseEntity<>(new AccountLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/byHolderId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccByHolderId(@PathVariable("id") String id){
        try {
            var accounts = new FindAccountByHolderIdQuery(id);
            var response =
                    queryGateway.query(accounts, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Failed to query for by holder id accounts get.";
            log.error(safeError);
            return new ResponseEntity<>(new AccountLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(path = "/balance/{equalityType}/{balance}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<AccountLookupResponse> getAccByBalance(@PathVariable("equalityType") EqualityType equalityType,
                                                                 @PathVariable("balance") double balance){
        try {
            var accounts = new FindAccountsWithBalanceQuery(equalityType,balance);
            var response =
                    queryGateway.query(accounts, ResponseTypes.instanceOf(AccountLookupResponse.class)).join();
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e){
            var safeError = "Failed to query for balance accounts get.";
            log.error(safeError);
            return new ResponseEntity<>(new AccountLookupResponse(safeError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
