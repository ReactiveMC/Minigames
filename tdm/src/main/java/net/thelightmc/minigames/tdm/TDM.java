package net.thelightmc.minigames.tdm;

import net.thelightmc.minigames.Minigame;

public class TDM extends Minigame {
    public TDM() {
        super(new TDMGame(),new TDMListener());
    }
}
