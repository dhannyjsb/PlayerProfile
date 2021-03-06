package com.github.dhannyjsb.playerprofile.listener;

import com.github.dhannyjsb.playerprofile.Main;
import com.github.dhannyjsb.playerprofile.databases.MethodeDatabaseUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    private Main plugin;


    public PlayerJoin(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        new MethodeDatabaseUser().checkPlayerData(player);
    }

}
