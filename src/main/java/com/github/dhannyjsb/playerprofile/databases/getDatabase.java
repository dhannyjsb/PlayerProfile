package com.github.dhannyjsb.playerprofile.databases;

import com.github.dhannyjsb.playerprofile.Main;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class getDatabase {

    protected Main plugin;

    public getDatabase(Main plugin) {
        this.plugin = plugin;
    }



    public void checkPlayerData(Player player) {
        UserDB query = null;
        try {
            query = new SetupDatabases(Main.getDatabaseMain()).HandlerUserDB()
                    .queryBuilder()
                    .where()
                    .eq("uuid", player.getUniqueId().toString())
                    .queryForFirst();
        } catch (SQLException e) {
            //
        }
        if (query == null) {
            assert false;
            query.setUuid(player.getUniqueId().toString());
            query.setPlayer_name(player.getDisplayName());
            query.setPlayer_first_join(player.getFirstPlayed());
            query.setPlayer_desc("No Description for this player");
            query.setDesc_show(1);
            query.setEquipment_show(1);
            query.setAllow_mail(1);
            query.setAllow_friend_add(true);
        }
    }
}
