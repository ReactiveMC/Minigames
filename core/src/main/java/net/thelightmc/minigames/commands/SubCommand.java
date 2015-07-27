package net.thelightmc.minigames.commands;

import lombok.AccessLevel;
import lombok.Getter;
import net.thelightmc.minigames.player.GamePlayer;

public abstract class SubCommand {
    @Getter(value = AccessLevel.PACKAGE)
    private final String command;

    protected SubCommand(String command) {
        this.command = command;
    }

    protected abstract void execute(String[] args,GamePlayer gamePlayer);
}
