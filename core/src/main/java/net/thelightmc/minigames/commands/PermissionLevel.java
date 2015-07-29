package net.thelightmc.minigames.commands;

import lombok.Getter;

public enum PermissionLevel {
    PLAYER(1),DONOR(2),ADMIN(3),OP(4);
    @Getter
    private final int rank;
    PermissionLevel(int rank) {
        this.rank = rank;
    }
    public boolean hasLevel(PermissionLevel permissionLevel) {
        return this.getRank() >= permissionLevel.getRank();
    }
}
