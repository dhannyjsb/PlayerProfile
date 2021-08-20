package com.github.dhannyjsb.playerprofile.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;

public class ChatInput implements Listener {


    public HashMap<String,String> settings = new HashMap<String,String>();

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent e){
        if(settings.containsKey(e.getPlayer().getUniqueId().toString())) {
            if(e.getMessage().contentEquals("cancel")) {
                settings.remove(e.getPlayer().getUniqueId().toString());
                e.getPlayer().sendMessage("Cancelled");
                e.setCancelled(true);
                return;
            }
            if(settings.get(e.getPlayer().getUniqueId().toString()).split(":").length>1){
                //buildRequests.projectDataConfig.set("projects."+settings.get(e.getPlayer().getUniqueId().toString()).split(":")[0]+"."+settings.get(e.getPlayer().getUniqueId().toString()).split(":")[1], e.getMessage());
                settings.remove(e.getPlayer().getUniqueId().toString());
                e.setCancelled(true);
            }}
    }

    private class BuildRequests {
    }
}
