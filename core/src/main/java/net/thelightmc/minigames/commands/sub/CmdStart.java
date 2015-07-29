package net.thelightmc.minigames.commands.sub;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.commands.PermissionLevel;
import net.thelightmc.minigames.commands.SubCommand;
import net.thelightmc.minigames.player.GamePlayer;
import org.bukkit.ChatColor;

public class CmdStart extends SubCommand {
    public CmdStart() {
        super("start");
        this.setPermissionLevel(PermissionLevel.ADMIN);
    }

    @Override
    protected void execute(String[] args, GamePlayer gamePlayer) {
        Minigames minigames = Minigames.getMinigames();
        if (minigames.getMinigame().getGameModule().isRunning()) {
            gamePlayer.getPlayer().get().sendMessage(ChatColor.RED + "The game is already running!");
            return;
        }
        minigames.getTimer().setTime(0);
        gamePlayer.getPlayer().get().sendMessage(ChatColor.BLUE + "You have force started the game.");
    }
}
