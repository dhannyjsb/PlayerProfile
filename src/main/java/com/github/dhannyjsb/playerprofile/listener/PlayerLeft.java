package com.github.dhannyjsb.playerprofile.listener;

import com.github.dhannyjsb.playerprofile.Main;
import com.github.dhannyjsb.playerprofile.databases.MethodeDatabaseUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeft implements Listener {

    private Main plugin;

    public PlayerLeft(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event){
        Player player = event.getPlayer();
        new MethodeDatabaseUser().setLogout(player);
    }
}
