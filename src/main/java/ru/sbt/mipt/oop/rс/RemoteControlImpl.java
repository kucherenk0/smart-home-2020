package ru.sbt.mipt.oop.rс;

import remote.RemoteControl;

import java.util.Map;

public class RemoteControlImpl implements RemoteControl {

    private final Map<String, Command> commands;

    public RemoteControlImpl(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void onButtonPressed(String buttonCode) {
        commands.get(buttonCode).execute();
    }
}
