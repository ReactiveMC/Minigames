package net.thelightmc.minigames.commands.sub;

import net.thelightmc.minigames.commands.PermissionLevel;
import net.thelightmc.minigames.commands.SubCommand;
import net.thelightmc.minigames.listeners.EditListener;
import net.thelightmc.minigames.map.MapLoader;
import net.thelightmc.minigames.player.GamePlayer;
import org.bukkit.ChatColor;

public class CmdSavemap extends SubCommand {

    private final EditListener editListener;
    public CmdSavemap(EditListener editListener) {
        super("save");
        this.editListener = editListener;
        this.setPermissionLevel(PermissionLevel.ADMIN);
    }

    @Override
    protected void execute(String[] args, GamePlayer gamePlayer) {
        if (!gamePlayer.isEditing()) {
            gamePlayer.getPlayer().get().sendMessage(ChatColor.RED + "You have to be editing to run this command!");
            return;
        }
        if (args.length > 1) {
            String game = args[0];
            String map = args[1];
            MapLoader.get().saveMap(game,map,editListener.getSpawns(),gamePlayer.getPlayer().get().getWorld(),gamePlayer.getPlayer().get().getName(),editListener.getMinimumLocation());
            gamePlayer.setEditing(false);
            editListener.getSpawns().clear();
            editListener.setMinimumLocation(0);
        }
    }
}
