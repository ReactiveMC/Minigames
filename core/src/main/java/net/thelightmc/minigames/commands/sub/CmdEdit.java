package net.thelightmc.minigames.commands.sub;

import net.thelightmc.minigames.commands.SubCommand;
import net.thelightmc.minigames.map.MapLoader;
import net.thelightmc.minigames.player.GamePlayer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class CmdEdit extends SubCommand {
    public CmdEdit() {
        super("edit");
    }
    @Override
    protected void execute(String[] args, GamePlayer gamePlayer) {
        if (args.length > 2) {
            String game = args[1];
            String map = args[2];
            Player player = gamePlayer.getPlayer().get();
            if (!MapLoader.get().isMap(game,map)) {
                if (!MapLoader.get().createMap(game,map)) {
                    player.sendMessage(ChatColor.RED + "ERROR: Report this to an admin.\n" +
                            "The template map is not present and your map can't be created.");
                    return;
                }
            }
            World world = MapLoader.get().loadEditMap(game, map);
            player.teleport(new Location(world,10,100,10));
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(ChatColor.GREEN + "You have started to edit the map.");
            gamePlayer.setEditing(true);
        }
    }
}
