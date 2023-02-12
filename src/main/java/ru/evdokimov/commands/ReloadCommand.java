package ru.evdokimov.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.evdokimov.Main;
import ru.evdokimov.utils.ConfigUtils;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Main.getInstance().reloadConfig();
        sender.sendMessage(ConfigUtils.reloaded);
        return true;
    }
}