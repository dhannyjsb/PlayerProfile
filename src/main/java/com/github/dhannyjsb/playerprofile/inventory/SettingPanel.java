package com.github.dhannyjsb.playerprofile.inventory;

import com.github.dhannyjsb.playerprofile.databases.MethodeDatabaseUser;
import com.github.dhannyjsb.playerprofile.listener.ChatInput;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Consumer;

import java.util.*;

public class SettingPanel {

    private ChatInput chatInput = new ChatInput();

    public void playerSettingsPanel(ChestGui gui, Player player, StaticPane settingsPage, PaginatedPane pane) {

        desc(player, settingsPage, gui);
        equip(player, settingsPage, gui);
        mail(player, settingsPage, gui);
        friend(player, settingsPage, gui);
        setDesc(player, settingsPage, gui);


        settingsPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 2, 2);
        settingsPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 3, 2);
        settingsPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 4, 2);
        settingsPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 5, 2);
        settingsPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 6, 2);

        getBackgroundFront(gui, pane);
        backButton(gui, settingsPage, pane);


    }

    public void setDesc(Player player, StaticPane settingsPage, ChestGui gui) {
        ItemStack friends = new ItemStack(Material.ROSE_BUSH    );
        ItemMeta friendsMeta = friends.getItemMeta();
        if (friendsMeta != null) {
            friendsMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Set Description");
            List<String> loreDesc = new ArrayList<>();
            friendsMeta.setLore(loreDesc);
        }
        friends.setItemMeta(friendsMeta);

        settingsPage.addItem(new GuiItem(friends, event -> {
            event.getWhoClicked().closeInventory();
            player.sendMessage("Please input you description");
            player.sendMessage("Please type a description, or type cancel");
            chatInput.settings.put(player.getUniqueId(),ChatColor.stripColor("description"));

        }), 6, 1);
    }

    public void friend(Player player, StaticPane settingsPage, ChestGui gui) {
        ItemStack friends = new ItemStack(Material.ROSE_BUSH    );
        ItemMeta friendsMeta = friends.getItemMeta();
        if (friendsMeta != null) {
            friendsMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Friend Setting");
            List<String> loreDesc = new ArrayList<>();
            loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new MethodeDatabaseUser().checkSettingFriend(player));
            friendsMeta.setLore(loreDesc);
        }
        friends.setItemMeta(friendsMeta);

        settingsPage.addItem(new GuiItem(friends, event -> {
            new MethodeDatabaseUser().setFriendSetting(player);
            if (friendsMeta != null) {
                friendsMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Friend Setting");
                List<String> loreDesc = new ArrayList<>();
                loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new MethodeDatabaseUser().checkSettingFriend(player));
                friendsMeta.setLore(loreDesc);
            }
            friends.setItemMeta(friendsMeta);
            gui.update();
        }), 5, 1);
    }

    public void mail(Player player, StaticPane settingsPage, ChestGui gui) {
        ItemStack email = new ItemStack(Material.FILLED_MAP);
        ItemMeta emailMeta = email.getItemMeta();
        if (emailMeta != null) {
            emailMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Mail");
            List<String> loreDesc = new ArrayList<>();
            loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new MethodeDatabaseUser().checkSettingMail(player));
            emailMeta.setLore(loreDesc);
        }
        email.setItemMeta(emailMeta);

        settingsPage.addItem(new GuiItem(email, event -> {
            new MethodeDatabaseUser().setMailSetting(player);
            if (emailMeta != null) {
                emailMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Mail");
                List<String> loreDesc = new ArrayList<>();
                loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new MethodeDatabaseUser().checkSettingMail(player));
                emailMeta.setLore(loreDesc);
            }
            email.setItemMeta(emailMeta);
            gui.update();
        }), 4, 1);
    }

    public void equip(Player player, StaticPane settingsPage, ChestGui gui) {
        ItemStack equipment = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta equipmentMeta = equipment.getItemMeta();
        if (equipmentMeta != null) {
            equipmentMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Equipment");
            List<String> loreDesc = new ArrayList<>();
            loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new MethodeDatabaseUser().checkSettingEquip(player));
            equipmentMeta.setLore(loreDesc);
        }
        equipment.setItemMeta(equipmentMeta);

        settingsPage.addItem(new GuiItem(equipment, event -> {
            new MethodeDatabaseUser().setEquipSetting(player);
            if (equipmentMeta != null) {
                equipmentMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Equipment");
                List<String> loreDesc = new ArrayList<>();
                loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new MethodeDatabaseUser().checkSettingEquip(player));
                equipmentMeta.setLore(loreDesc);
            }
            equipment.setItemMeta(equipmentMeta);
            gui.update();
        }), 3, 1);
    }

    public void desc(Player player, StaticPane settingsPage, ChestGui gui) {
        ItemStack desc = new ItemStack(Material.BOOK);
        ItemMeta descMeta = desc.getItemMeta();
        if (descMeta != null) {
            descMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Desc");
            List<String> loreDesc = new ArrayList<>();
            loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new MethodeDatabaseUser().checkSettingDesc(player));
            descMeta.setLore(loreDesc);
        }
        desc.setItemMeta(descMeta);

        settingsPage.addItem(new GuiItem(desc, event -> {
            new MethodeDatabaseUser().setDescSetting(player);
            if (descMeta != null) {
                descMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Desc");
                List<String> loreDesc = new ArrayList<>();
                loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new MethodeDatabaseUser().checkSettingDesc(player));
                descMeta.setLore(loreDesc);
            }
            desc.setItemMeta(descMeta);
            gui.update();
        }), 2, 1);
    }

    public void getBackgroundFront(ChestGui gui, PaginatedPane pane) {
        OutlinePane backgroundSettings = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();
        if (backgroundItemMeta != null) {
            backgroundItemMeta.setDisplayName("|");
        }
        backgroundItem.setItemMeta(backgroundItemMeta);
        //Background
        backgroundSettings.addItem(new GuiItem(new ItemStack(backgroundItem), event -> event.setCancelled(true)));
        backgroundSettings.setRepeat(true);
        pane.addPane(1, backgroundSettings);
    }


    public void backButton(ChestGui gui, StaticPane settingsPage, PaginatedPane pane) {
        ItemStack close = new ItemStack(Material.ARROW);
        ItemMeta closeMeta = close.getItemMeta();
        if (closeMeta != null) {
            closeMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Back");
        }
        close.setItemMeta(closeMeta);
        //settingsPage.addItem(new GuiItem(new ItemStack(close), event -> event.getWhoClicked().closeInventory()), 8, 4);
        settingsPage.addItem(new GuiItem(new ItemStack(close), event -> {
            pane.setPage(0);
            gui.update();
        }), 8, 4);
    }



}

