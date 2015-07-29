package net.thelightmc.minigames.commands.sub;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.commands.PermissionLevel;
import net.thelightmc.minigames.commands.SubCommand;
import net.thelightmc.minigames.player.GamePlayer;
import org.bukkit.ChatColor;

public class CmdEnd extends SubCommand {
    public CmdEnd() {
        super("end");
        this.setPermissionLevel(PermissionLevel.ADMIN);
    }
    @Override
    protected void execute(String[] args, GamePlayer gamePlayer) {
        Minigames minigames = Minigames.getMinigames();
        if (!minigames.getMinigame().getGameModule().isRunning()) {
            gamePlayer.getPlayer().get().sendMessage(ChatColor.RED + "The game is not running!");
            return;
        }
        minigames.getMinigame().getGameModule().endGame();
        gamePlayer.getPlayer().get().sendMessage(ChatColor.BLUE + "You have ended the game.");
    }
}
