package com.example.userqueryapi.handlers;

import com.example.usercore.events.UserRegisteredEvent;
import com.example.usercore.events.UserRemovedEvent;
import com.example.usercore.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
