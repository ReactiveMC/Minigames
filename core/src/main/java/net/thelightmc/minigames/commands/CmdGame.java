package net.thelightmc.minigames.commands;

import com.google.common.collect.ImmutableList;
import net.thelightmc.minigames.player.PlayerRegistery;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class CmdGame implements CommandExecutor {
    private List<SubCommand> commands;

    public CmdGame(SubCommand... subCommands) {
        this.commands = ImmutableList.copyOf(Arrays.asList(subCommands));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length > 0) {
            commands.stream().filter(c -> c.getCommand().equalsIgnoreCase(strings[0])).forEach(c -> c.execute(strings, PlayerRegistery.getPlayer(commandSender.getName())));
        }
        return true;
    }
}
