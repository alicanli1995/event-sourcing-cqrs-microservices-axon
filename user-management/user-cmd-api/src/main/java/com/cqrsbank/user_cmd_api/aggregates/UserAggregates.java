package com.cqrsbank.user_cmd_api.aggregates;

import com.cqrsbank.user_cmd_api.commands.RegisterUserCommand;
import com.cqrsbank.user_cmd_api.commands.RemoveUserCommand;
import com.cqrsbank.user_cmd_api.commands.UpdateUserCommand;
import com.cqrsbank.user_cmd_api.security.PasswordEncoder;
import com.cqrsbank.user_cmd_api.security.impl.PasswordEncoderImpl;
import com.example.usercore.events.UserRegisteredEvent;
import com.example.usercore.events.UserRemovedEvent;
import com.example.usercore.events.UserUpdatedEvent;
import com.example.usercore.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregates {

    @AggregateIdentifier
    private String id;

    private User user;

    private final PasswordEncoder passwordEncoder;

    public UserAggregates() {
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregates(RegisterUserCommand registerUserCommand){
        var newUser = registerUserCommand.getUser();
        newUser.setId(registerUserCommand.getId());
        var password = newUser.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        var hasPass = passwordEncoder.hasPassword(password);
        newUser.getAccount().setPassword(hasPass);

        var event = UserRegisteredEvent.builder()
                .id(registerUserCommand.getId())
                .user(newUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(UpdateUserCommand updateUserCommand){
        var updatedUser = updateUserCommand.getUser();
        updatedUser.setId(updatedUser.getId());
        var password = updatedUser.getAccount().getPassword();
        var hasPass = passwordEncoder.hasPassword(password);
        updatedUser.getAccount().setPassword(hasPass);

        var event = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updatedUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand removeUserCommand){
        var event = new UserRemovedEvent();
        event.setId(removeUserCommand.getId());

        AggregateLifecycle.apply(event);

    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent userRegisteredEvent){
        this.id = userRegisteredEvent.getId();
        this.user = userRegisteredEvent.getUser();

    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent userUpdatedEvent){
        this.user = userUpdatedEvent.getUser();
    }


    @EventSourcingHandler
    public void on(UserRemovedEvent userRemovedEvent){
        AggregateLifecycle.markDeleted();
    }


}
