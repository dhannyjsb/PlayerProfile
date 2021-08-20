package com.github.dhannyjsb.playerprofile;

import com.github.dhannyjsb.playerprofile.command.MainCommand;
import com.github.dhannyjsb.playerprofile.command.TabComplete;
import com.github.dhannyjsb.playerprofile.databases.SetupDatabases;
import com.github.dhannyjsb.playerprofile.listener.ChatInput;
import com.github.dhannyjsb.playerprofile.listener.PlayerJoin;
import com.github.dhannyjsb.playerprofile.listener.PlayerLeft;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Level;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.support.ConnectionSource;
import com.tchristofferson.configupdater.ConfigUpdater;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Objects;

public final class Main extends JavaPlugin {
    private static Main instance;
    boolean debug = false;

    private static  ConnectionSource connectionSource;

    // Set instance for this plugin
    public static Main getInstance() {
        return instance;
    }
    public static ConnectionSource getDatabaseMain() {
        return connectionSource;
    }
    @Override
    public void onEnable() {
        //Logger.setGlobalLogLevel(debug ? Level.TRACE : Level.OFF);
        Logger.setGlobalLogLevel(Level.TRACE);

        instance = this;
        // Plugin startup logic
        saveDefaultConfig();
        File configFile = new File(getDataFolder(), "config.yml");
        try {
            ConfigUpdater.update(this, "config.yml", configFile, Collections.emptyList());
            getLogger().fine("Config update");
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloadConfig();

        // Setup Databases
        setupAllDatabase();
        // Create table
        new SetupDatabases().setupUserDB(connectionSource);
        new SetupDatabases().setupFriendDB(connectionSource);
        new SetupDatabases().setupMailDB(connectionSource);
        new SetupDatabases().setupAllTables(connectionSource);

        // register command
        Objects.requireNonNull(getCommand("playerprofile")).setExecutor(new MainCommand(this));
        Objects.requireNonNull(getCommand("playerprofile")).setTabCompleter(new TabComplete());
        // register listener
        new PlayerJoin(this);
        new PlayerLeft(this);
        new ChatInput(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void setupAllDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        File file = new File(this.getDataFolder(), "database.db");
        String datasource = "jdbc:sqlite:" + file;
        connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(datasource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
