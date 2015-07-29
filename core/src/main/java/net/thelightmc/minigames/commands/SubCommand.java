package net.thelightmc.minigames.commands;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.thelightmc.minigames.player.GamePlayer;

@Setter(AccessLevel.PROTECTED)
@Getter(AccessLevel.PROTECTED)
public abstract class SubCommand {
    private final String command;
    private int requiredArgs = 0;
    private PermissionLevel permissionLevel = PermissionLevel.PLAYER;

    protected SubCommand(String command) {
        this.command = command;
    }

    protected abstract void execute(String[] args,GamePlayer gamePlayer);
}
