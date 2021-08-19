package com.github.dhannyjsb.playerprofile.inventory;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;

import org.bukkit.entity.Player;


public class PlayerInventory {
    public void openPlayerInventory(Player player) {
        ChestGui gui = new ChestGui(5, player.getDisplayName() + " Profile");
        PaginatedPane pane = new PaginatedPane(0, 0, 9, 5);
        StaticPane frontPage = new StaticPane(0, 0, 9, 5);
        StaticPane settingsPage = new StaticPane(0, 0, 9, 5);

        // Front Panel
        new OwnFrontPanel().frontPanel(gui, player, frontPage, pane);
        new SettingPanel().playerSettingsPanel(gui, player, settingsPage, pane);


        gui.addPane(pane);


        pane.addPane(0, frontPage);
        pane.addPane(1, settingsPage);
        //Background


        gui.setOnBottomClick(event -> event.setCancelled(true));
        gui.show(player);

    }


}
