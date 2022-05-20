package com.example.userqueryapi.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindUserByIdQuery {
    private String id;
}
