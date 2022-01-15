package de.feli490.feliutils;

import java.util.logging.Level;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class FeliUtilsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        PluginDescriptionFile description = getDescription();
        getLogger().log(Level.INFO, "Plugin v%s by %s loaded.".formatted(description.getVersion(), description.getAuthors()));
    }
}
