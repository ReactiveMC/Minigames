package net.thelightmc.minigames;

import lombok.Getter;
import net.thelightmc.minigames.game.GameModule;
import net.thelightmc.minigames.game.GameListener;

@SuppressWarnings("unused")
public abstract class Minigame {
    @Getter private final GameModule gameModule;
    @Getter private final GameListener gameListener;

    public Minigame(GameModule gameModule, GameListener gameListener) {
        this.gameModule = gameModule;
        this.gameListener = gameListener;
    }
    public void onEnable(){}
    public void onDisable(){}
}
