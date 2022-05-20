package com.example.usercore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;

    @NotEmpty(message = "Username is mandatory")
    private String firstName;

    @NotEmpty(message = "Lastname is mandatory")
    private String lastName;

    @Email(message = "Please enter the valid mail address")
    private String emailAddress;

    @NotNull(message = "Please provide account credentials")
    @Valid
    private Account account;
}
