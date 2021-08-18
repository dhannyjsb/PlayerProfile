package com.github.dhannyjsb.playerprofile.command;

import com.github.dhannyjsb.playerprofile.Main;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
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
    public MainCommand (Main plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand( CommandSender commandSender, org.bukkit.command. Command command,  String s,  String[] arg) {
            if (arg.length >= 1){
                if(arg[0].equalsIgnoreCase("reload")) {
                    Player player = (Player) commandSender;
                    ChestGui gui = new ChestGui(5, player.getDisplayName() + " Profile");
                    StaticPane profile = new StaticPane(0, 0, 9, 5);
                    //Background
                    ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
                    ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();
                    if (backgroundItemMeta != null) {
                        backgroundItemMeta.setDisplayName("|");
                    }
                    backgroundItem.setItemMeta(backgroundItemMeta);
                    OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
                    //Background


                    // Get Helmet
                    ItemStack helm = player.getInventory().getHelmet();
                    if(helm == null)
                        helm = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

                    // Get Armor
                    ItemStack armor = player.getInventory().getChestplate();
                    if(armor == null)
                        armor = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

                    // Get Right hand
                    ItemStack rightHand = player.getInventory().getItemInMainHand();
                    if(rightHand.getType().equals(Material.AIR))
                        rightHand = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

                    // Get Off  hand
                    ItemStack rightOffHand = player.getInventory().getItemInOffHand();
                    if(rightOffHand.getType().equals(Material.AIR))
                        rightOffHand = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

                    // Get leggings
                    ItemStack leggings = player.getInventory().getLeggings();
                    if(leggings == null)
                        leggings = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

                    // Get Boots
                    ItemStack boots = player.getInventory().getBoots();
                    if(boots == null)
                        boots = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                    // Armor
                    profile.addItem(new GuiItem(new ItemStack(armor), event -> event.setCancelled(true)), 1, 1);
                    // Helm
                    profile.addItem(new GuiItem(new ItemStack(helm), event -> event.setCancelled(true)), 1, 0);
                    // Right hand
                    profile.addItem(new GuiItem(new ItemStack(rightHand), event -> event.setCancelled(true)), 0, 1);
                    // Left hand
                    profile.addItem(new GuiItem(new ItemStack(rightOffHand), event -> event.setCancelled(true)), 2, 1);
                    // Leggings
                    profile.addItem(new GuiItem(new ItemStack(leggings), event -> event.setCancelled(true)), 1, 2);
                    // Boots
                    profile.addItem(new GuiItem(new ItemStack(boots), event -> event.setCancelled(true)), 1, 3);


                    background.addItem(new GuiItem(new ItemStack(backgroundItem), event -> event.setCancelled(true)));
                    background.setRepeat(true);

                    gui.addPane(background);

                    gui.addPane(profile);
                    gui.show(player);

                    gui.setOnClose(event -> player.sendMessage("Gui Close"));
                }
            }

        return true;
    }

}