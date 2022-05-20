package com.cqrsbank.user_cmd_api.security;

public interface PasswordEncoder {
    String hasPassword(String password);
}
