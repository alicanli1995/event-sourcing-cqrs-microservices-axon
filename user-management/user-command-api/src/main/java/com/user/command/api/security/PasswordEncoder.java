package com.cqrsbank.security;

public interface PasswordEncoder {
    String hasPassword(String password);
}
