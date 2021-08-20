package com.github.dhannyjsb.playerprofile.inventory;

import com.github.dhannyjsb.playerprofile.databases.MethodeDatabaseFriend;
import com.github.dhannyjsb.playerprofile.databases.MethodeDatabaseUser;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.upperlevel.spigot.book.BookUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class OtherPlayerPanel {


    public void frontPanel(ChestGui gui, Player player, StaticPane frontPage, Player caster) {

        getBackgroundFront(gui);

        getEquipmentBySettings(gui, player, caster, frontPage);

        getOtherProfile(gui, player, frontPage, caster);

        closeButton(gui, frontPage);


    }

    private void getEquipmentBySettings(ChestGui gui, Player player, Player caster, StaticPane frontPage) {
        if (new MethodeDatabaseUser().checkSettingEquipINT(player) == 1) {
            getEquipment(gui, player, frontPage);
        }
        if (new MethodeDatabaseUser().checkSettingEquipINT(player) == 2) {
            if (new MethodeDatabaseFriend().checkFriendShip(caster, player)) {
                getEquipment(gui, player, frontPage);
            } else {
                getNullEquipment(gui, frontPage);
            }
        }
        if (new MethodeDatabaseUser().checkSettingEquipINT(player) == 3) {
            getNullEquipment(gui, frontPage);
        }
    }

    public void getBackgroundFront(ChestGui gui) {
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
        gui.addPane(background);
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

    public void getNullEquipment(ChestGui gui, StaticPane frontPage) {
        ItemStack nulled = new ItemStack(Material.BARRIER);
        ItemMeta nulledMeta = nulled.getItemMeta();
        if (nulledMeta != null) {
            nulledMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "PRIVATE");
            List<String> loreFriend = new ArrayList<>();
            loreFriend.add(ChatColor.AQUA + "Private Profile");
            nulledMeta.setLore(loreFriend);

        }
        nulled.setItemMeta(nulledMeta);

        // Helm
        frontPage.addItem(new GuiItem(new ItemStack(nulled), event -> event.setCancelled(true)), 1, 1);
        // Armor
        frontPage.addItem(new GuiItem(new ItemStack(nulled), event -> event.setCancelled(true)), 1, 2);
        // Right hand
        frontPage.addItem(new GuiItem(new ItemStack(nulled), event -> event.setCancelled(true)), 0, 2);
        // Left hand
        frontPage.addItem(new GuiItem(new ItemStack(nulled), event -> event.setCancelled(true)), 2, 2);
        // Leggings
        frontPage.addItem(new GuiItem(new ItemStack(nulled), event -> event.setCancelled(true)), 1, 3);
        // Boots
        frontPage.addItem(new GuiItem(new ItemStack(nulled), event -> event.setCancelled(true)), 1, 4);
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

    public void getOtherProfile(ChestGui gui, Player player, StaticPane frontPage, Player caster) {


        // Profile
        getStatusProfileBySettings(frontPage, player, caster);
        // Friend list
        getDesc(frontPage, player, gui, caster);
        // Settings
        // Empty line
        getKosong(frontPage);
        // Add Pane Settings
    }

    private void getStatusProfileBySettings(StaticPane frontPage, Player player, Player caster) {
        if (new MethodeDatabaseUser().checkSettingStatusINT(player) == 1) {
            getStatusProfile(frontPage, player);
        }
        if (new MethodeDatabaseUser().checkSettingStatusINT(player) == 2) {
            if (new MethodeDatabaseFriend().checkFriendShip(caster, player)) {
                getStatusProfile(frontPage, player);
            } else {
                getStatusProfileNull(frontPage, player);
            }
        }
        if (new MethodeDatabaseUser().checkSettingStatusINT(player) == 3) {
            getStatusProfileNull(frontPage, player);
        }
    }

    public void getStatusProfileNull(StaticPane frontPage, Player player) {
        ItemStack playerProfile = new ItemStack(Material.OAK_SIGN);
        ItemMeta playerProfileItemMeta = playerProfile.getItemMeta();
        String x = "Private";
        String y = "Private";
        String z = "Private";
        String playerWorld = "Private";
        if (playerProfileItemMeta != null) {
            playerProfileItemMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Status");
            List<String> lorePlayer = new ArrayList<>();
            lorePlayer.add(ChatColor.GOLD + "Name : " + ChatColor.AQUA + "Private");
            lorePlayer.add(ChatColor.GOLD + "IP : " + ChatColor.AQUA + "Private");
            lorePlayer.add(ChatColor.GOLD + "Level : " + ChatColor.AQUA + "Private");
            lorePlayer.add(ChatColor.GOLD + "Location : "
                    + ChatColor.AQUA + playerWorld
                    + ChatColor.GOLD + " X: "
                    + ChatColor.DARK_PURPLE + x
                    + ChatColor.GOLD + " Y: "
                    + ChatColor.DARK_PURPLE + y
                    + ChatColor.GOLD + " Z: "
                    + ChatColor.DARK_PURPLE + z);
            lorePlayer.add(ChatColor.GOLD + "Health : " + "Private");
            lorePlayer.add(ChatColor.GOLD + "Food : " + "Private");
            playerProfileItemMeta.setLore(lorePlayer);
        }
        playerProfile.setItemMeta(playerProfileItemMeta);
        frontPage.addItem(new GuiItem(new ItemStack(playerProfile), event -> event.setCancelled(true)), 4, 1);

    }

    public void getStatusProfile(StaticPane frontPage, Player player) {
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

    public void getDesc(StaticPane frontPage, Player player, ChestGui gui, Player caster) {
        ItemStack friendList = new ItemStack(Material.BOOK);
        ItemMeta friendListMeta = friendList.getItemMeta();
        if (friendListMeta != null) {
            friendListMeta.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Player Description");
            List<String> loreFriend = new ArrayList<>();
            loreFriend.add(ChatColor.AQUA + "Open Player Description");
            friendListMeta.setLore(loreFriend);

        }
        friendList.setItemMeta(friendListMeta);
        // frontPage.addItem(new GuiItem(new ItemStack(friendList), getBook(player), 4, 2);
        frontPage.addItem(new GuiItem(friendList, event -> getBook(player, caster)), 5, 1);

    }

    public void getBook(Player player, Player caster) {

        int lenght = new MethodeDatabaseUser().getDescriptionPage1(player).length();
        if (lenght < 254) {

            ItemStack book = BookUtil.writtenBook()
                    .author("SnowyCoder")
                    .title("The Test-ament")
                    .pages(
                            new BaseComponent[]{
                                    new TextComponent(" Profile of " + new MethodeDatabaseUser().getPlayerName(player))
                            },
                            new BookUtil.PageBuilder()
                                    .add(new TextComponent(new MethodeDatabaseUser().getDescriptionPage1(player).substring(0, lenght)))

                                    .build()
                    )
                    .build();

            BookUtil.openPlayer(caster, book);
        }
        if (lenght > 254 && lenght < 500) {

            ItemStack book = BookUtil.writtenBook()
                    .author("SnowyCoder")
                    .title("The Test-ament")
                    .pages(
                            new BaseComponent[]{
                                    new TextComponent(" Profile of " + new MethodeDatabaseUser().getPlayerName(player))
                            },
                            new BookUtil.PageBuilder()
                                    .add(new TextComponent(new MethodeDatabaseUser().getDescriptionPage1(player).substring(0, 254)))

                                    .build(),
                            new BookUtil.PageBuilder()
                                    .add(new TextComponent(new MethodeDatabaseUser().getDescriptionPage1(player).substring(255, lenght)))

                                    .build()
                    )
                    .build();

            BookUtil.openPlayer(caster, book);
        }
        if (lenght > 500 && lenght < 750) {
            ItemStack book = BookUtil.writtenBook()
                    .author("SnowyCoder")
                    .title("The Test-ament")
                    .pages(
                            new BaseComponent[]{
                                    new TextComponent(" Profile of " + new MethodeDatabaseUser().getPlayerName(player))
                            },
                            new BookUtil.PageBuilder()
                                    .add(new TextComponent(new MethodeDatabaseUser().getDescriptionPage1(player).substring(0, 254)))

                                    .build(),
                            new BookUtil.PageBuilder()
                                    .add(new TextComponent(new MethodeDatabaseUser().getDescriptionPage1(player).substring(255, 500)))

                                    .build()
                            ,
                            new BookUtil.PageBuilder()
                                    .add(new TextComponent(new MethodeDatabaseUser().getDescriptionPage1(player).substring(501, lenght)))

                                    .build()
                    )
                    .build();

            BookUtil.openPlayer(caster, book);
        }
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
