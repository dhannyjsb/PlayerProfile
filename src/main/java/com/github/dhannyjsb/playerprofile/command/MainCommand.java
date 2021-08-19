package com.github.dhannyjsb.playerprofile.command;

import com.github.dhannyjsb.playerprofile.Main;
import com.github.dhannyjsb.playerprofile.inventory.PlayerInventory;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MainCommand implements CommandExecutor {

    private final Main plugin;

    public MainCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] arg) {
        if (arg.length >= 1) {
            if (arg[0].equalsIgnoreCase("open")) {
                Player player = (Player) commandSender;
                new PlayerInventory().openPlayerInventory(player);
            }
            if (arg[0].equalsIgnoreCase("test")) {
                Player player = (Player) commandSender;
                ChestGui gui = new ChestGui(6, "Pages!");

                PaginatedPane pane = new PaginatedPane(0, 0, 9, 5);

                //page one
                StaticPane pageOne = new StaticPane(0, 0, 9, 5);
                pageOne.addItem(new GuiItem(new ItemStack(Material.BONE), event -> event.getWhoClicked().sendMessage("Bone")), 0, 0);
                pane.addPane(0, pageOne);

                //page two
                StaticPane pageTwo = new StaticPane(0, 0, 9, 5);
                pageTwo.addItem(new GuiItem(new ItemStack(Material.GLASS), event -> event.getWhoClicked().sendMessage("Glass")), 0, 0);
                pane.addPane(1, pageTwo);

                //page three
                StaticPane pageThree = new StaticPane(0, 0, 9, 5);
                pageThree.addItem(new GuiItem(new ItemStack(Material.BLAZE_ROD), event -> event.getWhoClicked().sendMessage("Blaze rod")), 0, 0);
                pane.addPane(2, pageThree);

                gui.addPane(pane);

                //page selection
                StaticPane back = new StaticPane(2, 5, 1, 1);
                StaticPane forward = new StaticPane(6, 5, 1, 1);

                back.addItem(new GuiItem(new ItemStack(Material.ARROW), event -> {
                    pane.setPage(pane.getPage() - 1);

                    if (pane.getPage() == 0) {
                        back.setVisible(false);
                    }

                    forward.setVisible(true);
                    gui.update();
                }), 0, 0);

                back.setVisible(false);

                forward.addItem(new GuiItem(new ItemStack(Material.ARROW), event -> {
                    pane.setPage(pane.getPage() + 1);

                    if (pane.getPage() == pane.getPages() - 1) {
                        forward.setVisible(false);
                    }

                    back.setVisible(true);
                    gui.update();
                }), 0, 0);

                gui.addPane(back);
                gui.addPane(forward);

                gui.show(player);
            }
        }

        return true;
    }

}