package com.example.bankacccmdapi.dto;

import com.example.bankcore.dto.BaseResponse;
import lombok.Getter;
import lombok.Setter;


public class OpenAccountResponse extends BaseResponse {

    @Getter
    @Setter
    private String id;

    public OpenAccountResponse(String id, String message) {
        super(message);
        this.id = id;
    }


}
