package de.feli490.feliutils;

import java.util.logging.Level;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import de.feli490.feliutils.inventory.ActionInventoryEventListener;
import de.feli490.feliutils.inventory.textinput.TextInputListener;

public class FeliUtilsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        this.getServer().getPluginManager().registerEvents(new TextInputListener(this), this);

        try {
            ActionInventoryEventListener.startListener(this);
        } catch (IllegalAccessException e) {
            getLogger().log(Level.WARNING, "Exception while trying to Initializing ActionInventoryEventListener: (Already Initialized?)",
                    e);
        }

        PluginDescriptionFile description = getDescription();
        getLogger().log(Level.INFO, "Plugin v%s by %s loaded.".formatted(description.getVersion(), description.getAuthors()));
    }
}
