package com.github.dhannyjsb.playerprofile;

import com.github.dhannyjsb.playerprofile.command.MainCommand;
import com.github.dhannyjsb.playerprofile.command.TabComplete;
import com.github.dhannyjsb.playerprofile.listener.OnRightClick;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    private static Main instance;


    // Set instance for this plugin
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        Objects.requireNonNull(getCommand("playerprofile")).setExecutor(new MainCommand(this));
        Objects.requireNonNull(getCommand("playerprofile")).setTabCompleter(new TabComplete());

        new OnRightClick(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
