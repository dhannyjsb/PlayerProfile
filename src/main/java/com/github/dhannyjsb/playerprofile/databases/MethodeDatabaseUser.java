package com.github.dhannyjsb.playerprofile.databases;

import com.github.dhannyjsb.playerprofile.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


public class MethodeDatabaseUser {


    public Timestamp getDateNow() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public void checkPlayerData(Player player) {
        UserDB query = null;
        try {

            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        if (query == null) {
            newPlayer(player);
        }else{
            query.setIs_online(1);
            try {
                new SetupDatabases().HandlerUserDB().createOrUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void newPlayer(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();
        } catch (SQLException e) {
            //
        }
        if (query == null) {
            query = new UserDB();
        }

        query.setUuid(player.getUniqueId().toString());
        query.setPlayer_name(player.getDisplayName());
        query.setPlayer_first_join(player.getFirstPlayed());
        query.setPlayer_desc("No Description for this player");
        query.setDesc_show(1);
        query.setEquipment_show(1);
        query.setAllow_mail(1);
        query.setIs_online(1);
        query.setLogout_time(getDateNow());
        query.setAllow_friend_add(true);
        try {
            new SetupDatabases().HandlerUserDB().create(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLogout(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();
        } catch (SQLException e) {
            //
        }
        if (query == null) {
            query = new UserDB();
        }
        query.setIs_online(0);
        try {
            new SetupDatabases().HandlerUserDB().createOrUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String  getPlayerName(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        assert query != null;
        return query.getPlayer_name();
    }

    public void setDescription(Player player, String desc) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        assert query != null;
        query.setPlayer_desc(desc);

        try {
            new SetupDatabases().HandlerUserDB().createOrUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String  getDescriptionPage1(Player player){
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        assert query != null;
        return query.getPlayer_description().replace("&", "§").replaceAll("@n", "\n");
    }

    public void setFriendSetting(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        Boolean currentSettings =  checkSettingFriendBoolean(player);
        assert query != null;
        query.setAllow_friend_add(!currentSettings);
        try {
            new SetupDatabases().HandlerUserDB().createOrUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String checkSettingFriend(Player player) {
        Boolean description = checkSettingFriendBoolean(player);
        return description ? ChatColor.GREEN + "Allow" : ChatColor.GREEN + "Deny";
    }

    public Boolean checkSettingFriendBoolean(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        assert query != null;
        return query.getAllow_friend_add();
    }

    public String checkSettingMail(Player player) {
        Integer description = checkSettingMailINT(player);
        if (description == 1) {
            return ChatColor.GREEN + "Public";
        }
        if (description == 2) {
            return ChatColor.AQUA + "Friend";
        }
        if (description == 3) {
            return ChatColor.RED + "Private";
        }
        return null;
    }

    public Integer checkSettingMailINT(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        assert query != null;
        return query.getAllow_mail();
    }

    public void setMailSetting(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        Integer currentSettings =  checkSettingMailINT(player);
        assert query != null;
        if (currentSettings == 3){
            query.setAllow_mail(1);
        }else {
            query.setAllow_mail(currentSettings + 1);
        }
        try {
            new SetupDatabases().HandlerUserDB().createOrUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String checkSettingEquip(Player player) {
        Integer description = checkSettingEquipINT(player);
        if (description == 1) {
            return ChatColor.GREEN + "Public";
        }
        if (description == 2) {
            return ChatColor.AQUA + "Friend";
        }
        if (description == 3) {
            return ChatColor.RED + "Private";
        }
        return null;
    }

    public Integer checkSettingEquipINT(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        assert query != null;
        return query.getEquipment_show();
    }

    public void setEquipSetting(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        Integer currentSettings =  checkSettingEquipINT(player);
        assert query != null;
        if (currentSettings == 3){
            query.setEquipment_show(1);
        }else {
            query.setEquipment_show(currentSettings + 1);
        }
        try {
            new SetupDatabases().HandlerUserDB().createOrUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String checkSettingDesc(Player player) {
        Integer description = checkSettingDescINT(player);
        if (description == 1) {
            return ChatColor.GREEN + "Public";
        }
        if (description == 2) {
            return ChatColor.AQUA + "Friend";
        }
        if (description == 3) {
            return ChatColor.RED + "Private";
        }
        return null;
    }

    public Integer checkSettingDescINT(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        assert query != null;
        return query.getDesc_show();
    }

    public void setDescSetting(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        Integer currentSettings =  checkSettingDescINT(player);
        assert query != null;
        if (currentSettings == 3){
            query.setDesc_show(1);
        }else {
            query.setDesc_show(currentSettings + 1);
        }
        try {
            new SetupDatabases().HandlerUserDB().createOrUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public String checkSettingStatus(Player player) {
        Integer description = checkSettingStatusINT(player);
        if (description == 1) {
            return ChatColor.GREEN + "Public";
        }
        if (description == 2) {
            return ChatColor.AQUA + "Friend";
        }
        if (description == 3) {
            return ChatColor.RED + "Private";
        }
        return null;
    }

    public Integer checkSettingStatusINT(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        assert query != null;
        return query.getStatus();
    }

    public void setStatusSetting(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases().HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        Integer currentSettings =  checkSettingStatusINT(player);
        assert query != null;
        if (currentSettings == 3){
            query.setStatus(1);
        }else {
            query.setStatus(currentSettings + 1);
        }
        try {
            new SetupDatabases().HandlerUserDB().createOrUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
