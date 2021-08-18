package com.github.dhannyjsb.playerprofile.listener;

import com.github.dhannyjsb.playerprofile.Main;
import com.github.dhannyjsb.playerprofile.databases.getDatabase;
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
        player.sendMessage("nanti disini check database, apakah player sudah ada datanya atau tidak");
        new getDatabase(this.plugin).checkPlayerData(player);
    }

}
