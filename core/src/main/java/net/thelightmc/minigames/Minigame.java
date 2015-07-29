package net.thelightmc.minigames;

import lombok.Getter;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.game.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class Minigame {
    @Getter private final GameModule gameModule;
    @Getter private final GameListener gameListener;
    private final ItemStack icon;

    public Minigame(GameModule gameModule, GameListener gameListener) {
        this(gameModule, gameListener, new ItemStack(Material.GOLD_SWORD));
    }
    public Minigame(GameModule gameModule, GameListener gameListener,ItemStack icon) {
        this.gameModule = gameModule;
        this.gameListener = gameListener;
        this.icon = icon;
        Bukkit.getScheduler().runTaskLater(Minigames.getMinigames().getPlugin(), () -> {
            ItemMeta itemMeta = icon.getItemMeta();
            itemMeta.setDisplayName(gameModule.getGameMeta().name());
            icon.setItemMeta(itemMeta);
        }, 5);
    }
    public void onEnable(){}
    public void onDisable(){}

    public ItemStack getIcon() {
        return icon;
    }
}
