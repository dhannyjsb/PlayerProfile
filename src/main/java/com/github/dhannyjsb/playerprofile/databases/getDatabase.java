package com.github.dhannyjsb.playerprofile.databases;

import com.github.dhannyjsb.playerprofile.Main;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


public class getDatabase {

    protected Main plugin;

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

    public String checkSettingDesc(Player player) {
        Integer description = checkSettingDescINT(player);
        if (description == 1) {
            return "Public";
        }
        if (description == 2) {
            return "Friend";
        }
        if (description == 3) {
            return "Private";
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
}
