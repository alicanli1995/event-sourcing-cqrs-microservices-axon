package com.cqrsbank.user_cmd_api.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class RemoveUserCommand {

    @TargetAggregateIdentifier
    private String id;

}
