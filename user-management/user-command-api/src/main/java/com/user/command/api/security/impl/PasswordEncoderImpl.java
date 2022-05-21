package com.cqrsbank.security.impl;


import com.cqrsbank.security.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderImpl implements PasswordEncoder {


    @Override
    public String hasPassword(String password) {
        var encoder = new BCryptPasswordEncoder(12);
        return encoder.encode(password);
    }
}
