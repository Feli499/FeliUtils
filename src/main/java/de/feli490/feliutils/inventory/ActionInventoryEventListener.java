package de.feli490.feliutils.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class ActionInventoryEventListener implements Listener {

    public static ActionInventoryEventListener INSTANCE;
    private JavaPlugin javaPlugin;

    /**
     * Should only be initialized by {@link ActionInventoryEventListener#startListener(JavaPlugin)}
     */
    private ActionInventoryEventListener(JavaPlugin javaPlugin) {

        this.javaPlugin = javaPlugin;
        this.javaPlugin.getServer().getPluginManager().registerEvents(this, javaPlugin);
    }

    @EventHandler
    public static void onInventoryClickEvent(InventoryClickEvent e) {

        Inventory topInventory = e.getView().getTopInventory();
        InventoryHolder holder = topInventory.getHolder();

        if (!(holder instanceof ActionInventory actionInventory)) {
            return;
        }

        e.setCancelled(true);

        if (actionInventory.closeOnClick()) {
            e.getWhoClicked().closeInventory();
        }

        if (e.getClickedInventory() == null) {
            return;
        }

        actionInventory.onClick(e);

        if (e.getClickedInventory().equals(topInventory)) {
            actionInventory.onInventoryClick(e);
        } else {
            actionInventory.onPlayerInventoryClick(e);
        }
    }

    @EventHandler
    public static void onInventoryClickEvent(InventoryCloseEvent e) {

        Inventory closed = e.getInventory();

        InventoryHolder holder = closed.getHolder();
        if (!(holder instanceof ActionInventory actionInventory)) {
            return;
        }

        actionInventory.onInventoryClose(e);
    }

    public static void startListener(JavaPlugin javaPlugin) throws IllegalAccessException {

        if (INSTANCE != null) {
            PluginDescriptionFile initializedPluginDescription = INSTANCE.getInitializedPlugin().getDescription();
            throw new IllegalAccessException("The FeliUtils ActionInventoryEventListener is already initialized by Plugin: "
                    + initializedPluginDescription.getName());
        }

        INSTANCE = new ActionInventoryEventListener(javaPlugin);
    }

    private JavaPlugin getInitializedPlugin() {
        return javaPlugin;
    }
}
