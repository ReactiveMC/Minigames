package net.thelightmc.minigames.commands.sub;

import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.commands.SubCommand;
import net.thelightmc.minigames.listeners.EditListener;
import net.thelightmc.minigames.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class CmdCancel extends SubCommand {
    private final EditListener editListener;
    public CmdCancel(EditListener editListener) {
        super("cancel");
        this.editListener = editListener;
    }
    @Override
    protected void execute(String[] args, GamePlayer gamePlayer) {
        if (!gamePlayer.isEditing()) {
            gamePlayer.getPlayer().get().sendMessage(ChatColor.RED + "You are not editing.");
            return;
        }
        gamePlayer.getPlayer().get().sendMessage(ChatColor.BLUE + "You are no longer editing.");
        gamePlayer.getPlayer().get().teleport(Minigames.getMinigames().getLobby());
        editListener.getSpawns().clear();
        editListener.setMinimumLocation(0);
        Bukkit.unloadWorld("Edit",false);
    }
}
