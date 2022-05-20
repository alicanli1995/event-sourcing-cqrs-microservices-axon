package com.example.usercore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "accounts")
public class Account {

    @Size(min = 2, message = "username must have a min 2 char...")
    private String username;

    @Size(min = 7, message = "username must have a min 7 char...")
    private String password;

    @NotNull(message = "Specify at least 1 role ...")
    private List<Role> roles;

}
