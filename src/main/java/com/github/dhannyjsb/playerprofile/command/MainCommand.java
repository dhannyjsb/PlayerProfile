package com.github.dhannyjsb.playerprofile.command;

import com.github.dhannyjsb.playerprofile.Main;
import com.github.dhannyjsb.playerprofile.inventory.PlayerInventory;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainCommand implements CommandExecutor {

    private final Main plugin;

    public MainCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] arg) {
        if (arg.length >= 1) {
            if (arg[0].equalsIgnoreCase("open")) {
                Player player = (Player) commandSender;
                new PlayerInventory().openPlayerInventory(player);
            }
        }

        return true;
    }

}