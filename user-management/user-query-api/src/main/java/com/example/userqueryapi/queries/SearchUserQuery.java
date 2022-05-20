package com.example.userqueryapi.queries;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchUserQuery {
    private String filter;
}
