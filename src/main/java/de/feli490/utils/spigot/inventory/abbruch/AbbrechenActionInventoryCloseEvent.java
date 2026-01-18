package de.feli490.utils.spigot.inventory.abbruch;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import de.feli490.utils.spigot.inventory.ActionInventory;
import de.feli490.utils.spigot.inventory.ActionInventoryCloseEvent;

public class AbbrechenActionInventoryCloseEvent implements ActionInventoryCloseEvent {

    private final AbbruchActionInventory abbruchActionInventory;
    private JavaPlugin plugin;

    private AbbrechenActionInventoryCloseEvent(JavaPlugin plugin, ActionInventory headcaseActionInventory) {

        this.plugin = plugin;
        abbruchActionInventory = new AbbruchActionInventory(headcaseActionInventory);
    }

    public static void forActionInventory(JavaPlugin javaPlugin, ActionInventory headcaseActionInventory) {
        headcaseActionInventory.registerCloseActions(new AbbrechenActionInventoryCloseEvent(javaPlugin, headcaseActionInventory));
    }

    @Override
    public void onAction(InventoryCloseEvent e) {

        if (!InventoryCloseEvent.Reason.PLAYER.equals(e.getReason())) {
            return;
        }

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> abbruchActionInventory.open(e.getPlayer()), 1L);
    }
}
