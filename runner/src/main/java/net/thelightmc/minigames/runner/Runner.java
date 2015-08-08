package net.thelightmc.minigames.runner;

import net.thelightmc.minigames.Minigame;

public class Runner extends Minigame {
    public Runner() {
        super(new RunnerGame(),new RunnerListener());
    }
}
