package com.github.dhannyjsb.playerprofile.command;

import com.github.dhannyjsb.playerprofile.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    private Main plugin;
    public MainCommand (Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand( CommandSender commandSender, org.bukkit.command. Command command,  String s,  String[] arg) {
        if(commandSender.hasPermission("playerprofile.admin")){
            if (arg.length >= 1){
                if(arg[0].equalsIgnoreCase("reload")) {
                    commandSender.sendMessage(ChatColor.YELLOW + " Plugin has been reloaded");
                }
            }
        }
        return true;
    }

}