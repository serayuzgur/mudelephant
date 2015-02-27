package io.mudelephant.core.command;

import io.mudelephant.core.Configuration;

public abstract class Command<T extends Configuration> {

    private final String command;

    public Command(String command) {
        this.command = command;
    }

    public abstract void initialize(T configuration);


    public abstract void execute(T configuration);


}
