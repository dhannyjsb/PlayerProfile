package com.github.dhannyjsb.playerprofile.inventory;

import com.github.dhannyjsb.playerprofile.databases.getDatabase;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SettingPanel {

    public void playerSettingsPanel(ChestGui gui, Player player, StaticPane settingsPage, PaginatedPane pane) {

        desc(player, settingsPage, gui);
        equip(player, settingsPage, gui);

        settingsPage.addItem(new GuiItem(new ItemStack(Material.BARRIER), event -> event.setCancelled(true)), 4, 1);
        settingsPage.addItem(new GuiItem(new ItemStack(Material.BARRIER), event -> event.setCancelled(true)), 5, 1);
        settingsPage.addItem(new GuiItem(new ItemStack(Material.BARRIER), event -> event.setCancelled(true)), 6, 1);

        settingsPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 2, 2);
        settingsPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 3, 2);
        settingsPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 4, 2);
        settingsPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 5, 2);
        settingsPage.addItem(new GuiItem(new ItemStack(Material.AIR), event -> event.setCancelled(true)), 6, 2);

        getBackgroundFront(gui, pane);
        backButton(gui, settingsPage, pane);


    }

    public void equip(Player player, StaticPane settingsPage, ChestGui gui){
        ItemStack equipment = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta equipmentMeta = equipment.getItemMeta();
        if (equipmentMeta != null) {
            equipmentMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Equipment");
            List<String> loreDesc = new ArrayList<>();
            loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new getDatabase().checkSettingEquip(player));
            equipmentMeta.setLore(loreDesc);
        }
        equipment.setItemMeta(equipmentMeta);

        settingsPage.addItem(new GuiItem(equipment, event -> {
            new getDatabase().setEquipSetting(player);
            if (equipmentMeta != null) {
                equipmentMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Equipment");
                List<String> loreDesc = new ArrayList<>();
                loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new getDatabase().checkSettingEquip(player));
                equipmentMeta.setLore(loreDesc);
            }
            equipment.setItemMeta(equipmentMeta);
            gui.update();
        }), 3, 1);
    }

public void desc(Player player, StaticPane settingsPage, ChestGui gui){
    ItemStack desc = new ItemStack(Material.BOOK);
    ItemMeta descMeta = desc.getItemMeta();
    if (descMeta != null) {
        descMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Desc");
        List<String> loreDesc = new ArrayList<>();
        loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new getDatabase().checkSettingDesc(player));
        descMeta.setLore(loreDesc);
    }
    desc.setItemMeta(descMeta);

    settingsPage.addItem(new GuiItem(desc, event -> {
        new getDatabase().setDescSetting(player);
        if (descMeta != null) {
            descMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Desc");
            List<String> loreDesc = new ArrayList<>();
            loreDesc.add(ChatColor.GOLD + "Status : " + ChatColor.AQUA + new getDatabase().checkSettingDesc(player));
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

