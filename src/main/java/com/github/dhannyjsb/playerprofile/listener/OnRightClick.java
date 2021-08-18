package com.github.dhannyjsb.playerprofile.listener;

import com.github.dhannyjsb.playerprofile.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class OnRightClick implements Listener {
    private Inventory inv;
    private Main plugin;

    public OnRightClick(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void onRightClickEvent(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        player.sendMessage("test");
    }





}
