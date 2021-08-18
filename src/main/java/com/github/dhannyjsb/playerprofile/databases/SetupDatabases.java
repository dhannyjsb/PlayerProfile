package com.github.dhannyjsb.playerprofile.databases;

import com.github.dhannyjsb.playerprofile.Main;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.sql.SQLException;

public class SetupDatabases {
    private ConnectionSource connectionSource;
    // Database connection stuff

    // Data Mappers
    private Dao<UserDB, Integer> UserDBMapper;
    private Dao<FriendDB, Integer> FriendDBMapper;
    private Dao<MailDB, Integer> MailDBMapper;

    public SetupDatabases(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    // Data Dao Mappers
    public Dao<UserDB, Integer> HandlerUserDB() {
        return this.UserDBMapper;
    }

    public Dao<FriendDB, Integer> HandlerFriendDB() {
        return this.FriendDBMapper;
    }

    public Dao<MailDB, Integer> HandlerMailDB() {
        return this.MailDBMapper;
    }





    public void setupUserDB() {
        UserDBMapper = null;
        try {
            UserDBMapper = DaoManager.createDao(connectionSource, UserDB.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setupFriendDB() {
        FriendDBMapper = null;
        try {
            FriendDBMapper = DaoManager.createDao(connectionSource, FriendDB.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setupMailDB() {
        MailDBMapper = null;
        try {
            MailDBMapper = DaoManager.createDao(connectionSource, MailDB.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setupAllTables() {
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
