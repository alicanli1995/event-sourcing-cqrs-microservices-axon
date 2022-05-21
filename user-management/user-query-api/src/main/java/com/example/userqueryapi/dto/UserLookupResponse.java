package com.example.userqueryapi.dto;

import com.example.usercore.dto.BaseResponse;
import com.example.usercore.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class UserLookupResponse extends BaseResponse {

    @Getter
    @Setter
    private List<User> users;

    public UserLookupResponse(String message){
        super(message);
    }

    public UserLookupResponse(List<User> users){
        super(null);
        this.users = users;
    }

    public UserLookupResponse(String message,User user){
        super(message);
        this.users = new ArrayList<>();
        users.add(user);
    }


    public UserLookupResponse(User user){
        super(null);
        this.users = new ArrayList<>();
        users.add(user);
    }

}
