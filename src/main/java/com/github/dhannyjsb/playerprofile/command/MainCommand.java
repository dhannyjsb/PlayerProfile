package com.github.dhannyjsb.playerprofile.command;

import com.github.dhannyjsb.playerprofile.Main;
import com.github.dhannyjsb.playerprofile.inventory.PlayerInventory;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MainCommand implements CommandExecutor {

    private final Main plugin;

    public MainCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] arg) {

        Player player = (Player) commandSender;
        if (arg.length == 0) {
            new PlayerInventory().openPlayerInventory(player);
            return true;
        } else {
            //Player typed something more
            Player target = Bukkit.getPlayer(arg[0]);
            if (target == null) {
                //Target is not online
                player.sendMessage("Your target " + arg[0] + " is not online!");
            } else {
                if(player == target){
                    new PlayerInventory().openPlayerInventory(player);
                    return true;
                }
                //Targets online
                new PlayerInventory().openOtherInventory(player, target);

            }
        }
        return true;

    }
}