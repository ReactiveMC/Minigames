package net.thelightmc.minigames.commands;

import com.google.common.collect.ImmutableList;
import net.thelightmc.minigames.player.PlayerRegistery;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CmdGame implements CommandExecutor {
    private List<SubCommand> commands;

    public CmdGame(SubCommand... subCommands) {
        this.commands = ImmutableList.copyOf(Arrays.asList(subCommands));
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "This command is not enabled for console.");
            return false;
        }
        if (strings.length > 0) {
            for (SubCommand subCommand : commands) {
                if (!subCommand.getCommand().equalsIgnoreCase(strings[0])) {
                    continue;
                }
                if (!subCommand.getPermissionLevel().hasLevel(commandSender.isOp() ? PermissionLevel.OP : PermissionLevel.PLAYER)) {
                    commandSender.sendMessage(ChatColor.RED + "No permission. You must have at least " + ChatColor.BLUE +
                            subCommand.getPermissionLevel().name() + ChatColor.RED +" to do this.");
                    continue;
                }
                subCommand.execute(Arrays.copyOfRange(strings,1,strings.length),PlayerRegistery.getPlayer(commandSender.getName()));
                return true;
            }
            commandSender.sendMessage(ChatColor.RED + "No sub commands found.");
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder(ChatColor.GOLD + "Commands: ");
        for (SubCommand subCommand : commands) {
            stringBuilder.append(subCommand.getCommand()).append(", ");
        }
        commandSender.sendMessage(stringBuilder.toString().substring(0,stringBuilder.length()-2));
        return true;
    }
}
