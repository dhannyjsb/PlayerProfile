package com.github.dhannyjsb.playerprofile.databases;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class SetupDatabases {
    // Database connection stuff

    // Data Mappers
    private static Dao<UserDB, Integer> UserDBMapper;
    private static Dao<FriendDB, Integer> FriendDBMapper;
    private static Dao<MailDB, Integer> MailDBMapper;



    // Data Dao Mappers
    public Dao<UserDB, Integer> HandlerUserDB() {
        return UserDBMapper;
    }

    public Dao<FriendDB, Integer> HandlerFriendDB() {
        return FriendDBMapper;
    }

    public Dao<MailDB, Integer> HandlerMailDB() {
        return MailDBMapper;
    }





    public void setupUserDB(ConnectionSource connectionSource) {
        UserDBMapper = null;
        try {
            UserDBMapper = DaoManager.createDao(connectionSource, UserDB.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setupFriendDB(ConnectionSource connectionSource) {
        FriendDBMapper = null;
        try {
            FriendDBMapper = DaoManager.createDao(connectionSource, FriendDB.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setupMailDB(ConnectionSource connectionSource) {
        MailDBMapper = null;
        try {
            MailDBMapper = DaoManager.createDao(connectionSource, MailDB.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setupAllTables(ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, UserDB.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, FriendDB.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            TableUtils.createTableIfNotExists(connectionSource, MailDB.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
