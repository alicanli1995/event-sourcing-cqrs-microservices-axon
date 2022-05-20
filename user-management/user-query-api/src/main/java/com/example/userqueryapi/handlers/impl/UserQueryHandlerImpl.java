package com.example.userqueryapi.handlers.impl;

import com.example.userqueryapi.dto.UserLookupResponse;
import com.example.userqueryapi.handlers.UserQueryHandler;
import com.example.userqueryapi.queries.FindAllUsersQuery;
import com.example.userqueryapi.queries.FindUserByIdQuery;
import com.example.userqueryapi.queries.SearchUserQuery;
import com.example.userqueryapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    @QueryHandler
    @Override
    public UserLookupResponse getUserById(FindUserByIdQuery query) {
        var user = (userRepository.findById(query.getId()));
        return user.map(UserLookupResponse::new).orElse(null);
    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUsersByParameter(SearchUserQuery query) {
        return new UserLookupResponse(new ArrayList<>(userRepository.findByFilterRegex(query.getFilter())));
    }


    @QueryHandler
    @Override
    public UserLookupResponse findAllUsers(FindAllUsersQuery query) {
        var users = new ArrayList<>(userRepository.findAll());
        return new UserLookupResponse(users);
    }
}
