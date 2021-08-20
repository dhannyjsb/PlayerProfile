package com.github.dhannyjsb.playerprofile.listener;

import com.github.dhannyjsb.playerprofile.Main;
import com.github.dhannyjsb.playerprofile.databases.MethodeDatabaseUser;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Supplier;

public class ChatInput implements Listener {


    private Main plugin;

    public ChatInput(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public HashMap<UUID, String> settings = new HashMap<>();
    public ArrayList<String> chat = new ArrayList<>();

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent e) {

        if (settings.containsKey(e.getPlayer().getUniqueId())) {
            String str = String.join(" ", chat);
            int checkLimit = str.length() + e.getMessage().length();

            if (e.getMessage().contentEquals("@cancel")) {
                chat.clear();
                settings.remove(e.getPlayer().getUniqueId());
                e.getPlayer().sendMessage(ChatColor.RED + "Cancelled");
                e.setCancelled(true);
                return;
            }
            if (e.getMessage().contentEquals("@done")) {
                new MethodeDatabaseUser().setDescription(e.getPlayer(), str);
                settings.remove(e.getPlayer().getUniqueId());
                chat.clear();
                e.getPlayer().sendMessage(ChatColor.GREEN + "Player description has been change");
                e.setCancelled(true);
                return;
            }

            if(checkLimit > 750){
                e.getPlayer().sendMessage(ChatColor.RED + e.getPlayer().getDisplayName() + " : Description exceeds 750 characters");
            }else{
                chat.add(e.getMessage());
                e.getPlayer().sendMessage(ChatColor.AQUA + e.getPlayer().getDisplayName() + " : Add line success");
            }
            e.setCancelled(true);

        }
    }

}
