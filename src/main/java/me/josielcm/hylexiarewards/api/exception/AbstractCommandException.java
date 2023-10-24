package me.josielcm.hylexiarewards.api.exception;

import me.josielcm.hylexiarewards.api.commands.AbstractCommand;

public class AbstractCommandException extends Exception {
    public AbstractCommandException(String message) {super(message);}
}
