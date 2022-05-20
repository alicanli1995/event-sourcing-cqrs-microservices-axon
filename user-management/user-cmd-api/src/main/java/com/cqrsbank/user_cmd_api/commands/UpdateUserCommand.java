package com.cqrsbank.user_cmd_api.commands;

import com.example.usercore.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "Has error on user fields at least one ...")
    @Valid
    private User user;

}
