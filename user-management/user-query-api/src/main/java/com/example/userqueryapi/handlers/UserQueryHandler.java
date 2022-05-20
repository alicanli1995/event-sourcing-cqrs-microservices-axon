package com.example.userqueryapi.handlers;

import com.example.userqueryapi.dto.UserLookupResponse;
import com.example.userqueryapi.queries.FindAllUsersQuery;
import com.example.userqueryapi.queries.FindUserByIdQuery;
import com.example.userqueryapi.queries.SearchUserQuery;

public interface UserQueryHandler {

    UserLookupResponse getUserById(FindUserByIdQuery query);

    UserLookupResponse searchUsersByParameter(SearchUserQuery query);

    UserLookupResponse findAllUsers(FindAllUsersQuery query);
}
