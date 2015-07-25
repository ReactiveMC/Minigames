package net.thelightmc.minigames.commands;

import net.thelightmc.minigames.player.GamePlayer;

public abstract class SubCommand {
    private final String command;

    protected SubCommand(String command) {
        this.command = command;
    }

    protected abstract void execute(String[] args,GamePlayer gamePlayer);
}
