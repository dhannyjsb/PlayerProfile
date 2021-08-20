package com.github.dhannyjsb.playerprofile.inventory;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class OwnFrontPanel {

    public void frontPanel(ChestGui gui, Player player, StaticPane frontPage, PaginatedPane pane) {

        getBackgroundFront(gui, pane);
        // Equipment
        getEquipment(gui, player, frontPage);
        // Settings
        getOwnProfile(gui, player, frontPage, pane);
        // CLose button
        closeButton(gui, frontPage);
        // Event


    }

    public void closeButton(ChestGui gui, StaticPane frontPage) {
        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        if (closeMeta != null) {
            closeMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Close");
        }
        close.setItemMeta(closeMeta);
        frontPage.addItem(new GuiItem(new ItemStack(close), event -> event.getWhoClicked().closeInventory()), 8, 4);
    }


    public void getBackgroundFront(ChestGui gui, PaginatedPane pane) {
        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();
        if (backgroundItemMeta != null) {
            backgroundItemMeta.setDisplayName("|");
        }
        backgroundItem.setItemMeta(backgroundItemMeta);
        //Background
        background.addItem(new GuiItem(new ItemStack(backgroundItem), event -> event.setCancelled(true)));
        background.setRepeat(true);
        pane.addPane(0, background);
    }


    public void getEquipment(ChestGui gui, Player player, StaticPane frontPage) {
        ItemStack helm = player.getInventory().getHelmet();
        if (helm == null)
            helm = new ItemStack(Material.AIR);
        // Get Armor
        ItemStack armor = player.getInventory().getChestplate();
        if (armor == null)
            armor = new ItemStack(Material.AIR);
        // Get Right hand
        ItemStack rightHand = player.getInventory().getItemInMainHand();
        if (rightHand.getType().equals(Material.AIR))
            rightHand = new ItemStack(Material.AIR);
        // Get Off  hand
        ItemStack rightOffHand = player.getInventory().getItemInOffHand();
        if (rightOffHand.getType().equals(Material.AIR))
            rightOffHand = new ItemStack(Material.AIR);
        // Get leggings
        ItemStack leggings = player.getInventory().getLeggings();
        if (leggings == null)
            leggings = new ItemStack(Material.AIR);
        // Get Boots
        ItemStack boots = player.getInventory().getBoots();
        if (boots == null)
            boots = new ItemStack(Material.AIR);
        // Helm
        frontPage.addItem(new GuiItem(new ItemStack(helm), event -> event.setCancelled(true)), 1, 1);
        // Armor
        frontPage.addItem(new GuiItem(new ItemStack(armor), event -> event.setCancelled(true)), 1, 2);
        // Right hand
        frontPage.addItem(new GuiItem(new ItemStack(rightHand), event -> event.setCancelled(true)), 0, 2);
        // Left hand
        frontPage.addItem(new GuiItem(new ItemStack(rightOffHand), event -> event.setCancelled(true)), 2, 2);
        // Leggings
        frontPage.addItem(new GuiItem(new ItemStack(leggings), event -> event.setCancelled(true)), 1, 3);
        // Boots
        frontPage.addItem(new GuiItem(new ItemStack(boots), event -> event.setCancelled(true)), 1, 4);
    }

    public void getOwnProfile(ChestGui gui, Player player, StaticPane frontPage, PaginatedPane pane) {

        // Add Pane
        StaticPane settings = new StaticPane(4, 1, 9, 5);
        // Profile
        getPlayerOwnProfile(frontPage, player);
        // Friend list
        getFriendList(frontPage);
        // Mailbox
        getMailbox(frontPage);
        // Settings
        getSettings(frontPage, pane, gui);
        // Empty line
        getKosong(frontPage);
        // Add Pane Settings
    }

    private void getSettings(StaticPane frontPage, PaginatedPane pane, ChestGui gui) {
        ItemStack ownSettings = new ItemStack(Material.ANVIL);
        ItemMeta ownSettingsMeta = ownSettings.getItemMeta();
        if (ownSettingsMeta != null) {
            ownSettingsMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Profile Settings");
            List<String> loreFriend = new ArrayList<>();
            loreFriend.add(ChatColor.AQUA + "Open Player Profile Setting");
            ownSettingsMeta.setLore(loreFriend);

        }
        ownSettings.setItemMeta(ownSettingsMeta);
        //frontPage.addItem(new GuiItem(new ItemStack(ownSettings), event -> event.setCancelled(true)), 7, 1);
        frontPage.addItem(new GuiItem(new ItemStack(ownSettings), event -> {
            pane.setPage(1);
            gui.update();
        }), 7, 1);
    }


    private void getMailbox(StaticPane frontPage) {
        ItemStack mailBox = new ItemStack(Material.JUKEBOX);
        ItemMeta mailBoxMeta = mailBox.getItemMeta();
        if (mailBoxMeta != null) {
            mailBoxMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Mail Box");
            List<String> loreFriend = new ArrayList<>();
            loreFriend.add(ChatColor.AQUA + "Not Implement yet");
            mailBoxMeta.setLore(loreFriend);

        }
        mailBox.setItemMeta(mailBoxMeta);
        frontPage.addItem(new GuiItem(new ItemStack(mailBox), event -> event.setCancelled(true)), 6, 1);

    }


    public void getPlayerOwnProfile(StaticPane frontPage, Player player) {
        ItemStack playerProfile = new ItemStack(Material.OAK_SIGN);
        ItemMeta playerProfileItemMeta = playerProfile.getItemMeta();
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        String playerWorld = player.getWorld().getName().toUpperCase(Locale.ROOT);
        if (playerProfileItemMeta != null) {
            playerProfileItemMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Status");
            List<String> lorePlayer = new ArrayList<>();
            lorePlayer.add(ChatColor.GOLD + "Name : " + ChatColor.AQUA + player.getDisplayName());
            lorePlayer.add(ChatColor.GOLD + "IP : " + ChatColor.AQUA + Objects.requireNonNull(player.getAddress()).getHostName());
            lorePlayer.add(ChatColor.GOLD + "Level : " + ChatColor.AQUA + player.getLevel());
            lorePlayer.add(ChatColor.GOLD + "Location : "
                    + ChatColor.AQUA + playerWorld
                    + ChatColor.GOLD + " X: "
                    + ChatColor.DARK_PURPLE + x
                    + ChatColor.GOLD + " Y: "
                    + ChatColor.DARK_PURPLE + y
                    + ChatColor.GOLD + " Z: "
                    + ChatColor.DARK_PURPLE + z);
            lorePlayer.add(ChatColor.GOLD + "Health : " + player.getHealth());
            lorePlayer.add(ChatColor.GOLD + "Food : " + player.getFoodLevel());
            playerProfileItemMeta.setLore(lorePlayer);
        }
        playerProfile.setItemMeta(playerProfileItemMeta);
        frontPage.addItem(new GuiItem(new ItemStack(playerProfile), event -> event.setCancelled(true)), 4, 1);

    }

    public void getFriendList(StaticPane frontPage) {
        ItemStack friendList = new ItemStack(Material.COBWEB);
        ItemMeta friendListMeta = friendList.getItemMeta();
        if (friendListMeta != null) {
            friendListMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Friend List");
            List<String> loreFriend = new ArrayList<>();
            loreFriend.add(ChatColor.GOLD + "Total Friend : " + ChatColor.AQUA + "Not Implement yet");
            friendListMeta.setLore(loreFriend);

        }
        friendList.setItemMeta(friendListMeta);
        frontPage.addItem(new GuiItem(new ItemStack(friendList), event -> event.setCancelled(true)), 5, 1);

    }

    public void getKosong(StaticPane frontPage) {
        frontPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 4, 2);
        frontPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 5, 2);
        frontPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 6, 2);
        frontPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 7, 2);
        frontPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 4, 3);
        frontPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 5, 3);
        frontPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 6, 3);
        frontPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 7, 3);
    }
}

