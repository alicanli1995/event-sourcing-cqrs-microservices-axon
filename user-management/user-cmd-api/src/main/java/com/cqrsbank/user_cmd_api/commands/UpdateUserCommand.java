package com.cqrsbank.user_cmd_api.commands;

import com.example.usercore.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String id;

    private User user;

}
