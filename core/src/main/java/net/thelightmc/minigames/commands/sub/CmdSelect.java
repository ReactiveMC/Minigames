package net.thelightmc.minigames.commands.sub;

import net.thelightmc.minigames.Minigame;
import net.thelightmc.minigames.Minigames;
import net.thelightmc.minigames.commands.SubCommand;
import net.thelightmc.minigames.menu.InventoryClickType;
import net.thelightmc.minigames.menu.Menu;
import net.thelightmc.minigames.menu.MenuAPI;
import net.thelightmc.minigames.menu.MenuItem;
import net.thelightmc.minigames.player.GamePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class CmdSelect extends SubCommand {
    public CmdSelect() {
        super("select");
    }
    @Override
    protected void execute(String[] args, GamePlayer gamePlayer) {
        final Minigames minigames = Minigames.getMinigames();
        if (minigames.getTimer() != null) {
            minigames.getTimer().cancel();
        }
        Menu menu = MenuAPI.getMenuAPI().createMenu("Game selector",1);
        menu.openMenu(gamePlayer.getPlayer().get());
        List<Minigame> minigameList = minigames.getMinigameList();
        for (int i = 0; i < minigameList.size(); i++) {
            final Minigame minigame = minigameList.get(i);
            menu.addMenuItem(new MenuItem(minigame.getIcon()) {
                @Override
                public void onClick(Player player, InventoryClickType clickType) {
                    minigames.startMinigame(minigame);
                    menu.closeMenu(player);
                }
            },i);
        }
    }
}
