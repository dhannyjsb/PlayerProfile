package com.github.dhannyjsb.playerprofile.databases;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class MethodeDatabaseFriend {
    public Boolean checkFriendShip(Player caster, Player target) {
        FriendDB query = null;
        try {
            query = new SetupDatabases().HandlerFriendDB()
                    .queryBuilder()
                    .where()
                    .eq("user_uuid", caster.getUniqueId().toString())
                    .and()
                    .eq("friend_uuid", target.getUniqueId().toString())
                    .queryForFirst();

        } catch (SQLException e) {
            //
        }
        Bukkit.getLogger().severe(String.valueOf(query));
        return query != null;
    }
}
