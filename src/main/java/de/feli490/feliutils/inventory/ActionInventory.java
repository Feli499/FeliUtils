package de.feli490.feliutils.inventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ActionInventory implements InventoryHolder {

    private final Map<Integer, ActionInventoryClickEvent> clickActions = new HashMap<>();
    private final Map<Integer, ActionInventoryClickEvent> clickPlayerInventoryActions = new HashMap<>();

    private final Set<ActionInventoryCloseEvent> closeActions = new HashSet<>();
    private final Set<ActionInventoryClickEvent> generalClickActions = new HashSet<>();

    private final boolean closeOnClick;
    private Inventory inv;

    private ActionInventory(boolean closeOnClick) {
        this.closeOnClick = closeOnClick;
    }

    public static ActionInventory createActionInventory(boolean closeOnClick, int size) {

        ActionInventory headcaseActionInventory = new ActionInventory(closeOnClick);

        Inventory inv = Bukkit.createInventory(headcaseActionInventory, size);
        headcaseActionInventory.setInv(inv);

        return headcaseActionInventory;
    }

    public static ActionInventory createActionInventory(boolean closeOnClick, InventoryType inventoryType) {

        ActionInventory headcaseActionInventory = new ActionInventory(closeOnClick);

        Inventory inv = Bukkit.createInventory(headcaseActionInventory, inventoryType);
        headcaseActionInventory.setInv(inv);

        return headcaseActionInventory;
    }

    public static ActionInventory createActionInventory(boolean closeOnClick, int size, String title) {

        ActionInventory headcaseActionInventory = new ActionInventory(closeOnClick);

        Inventory inv = Bukkit.createInventory(headcaseActionInventory, size, title);
        headcaseActionInventory.setInv(inv);

        return headcaseActionInventory;
    }

    public static ActionInventory createActionInventory(boolean closeOnClick, InventoryType inventoryType, String title) {

        ActionInventory headcaseActionInventory = new ActionInventory(closeOnClick);

        Inventory inv = Bukkit.createInventory(headcaseActionInventory, inventoryType, title);
        headcaseActionInventory.setInv(inv);

        return headcaseActionInventory;
    }

    public void registerCloseActions(ActionInventoryCloseEvent actionInventoryCloseEvent) {
        closeActions.add(actionInventoryCloseEvent);
    }

    public void unregisterCloseActions(ActionInventoryCloseEvent actionInventoryCloseEvent) {
        closeActions.add(actionInventoryCloseEvent);
    }

    /**
     * Nicht an Slots gebundene Click Events.
     * Werden VOR den SlotClick Events ausgeführt.
     */
    public void registerClickActions(ActionInventoryClickEvent actionInventoryClickEvent) {
        generalClickActions.add(actionInventoryClickEvent);
    }

    public void unregisterClickActions(ActionInventoryClickEvent actionInventoryClickEvent) {
        generalClickActions.add(actionInventoryClickEvent);
    }

    public void setClickEvent(int slot, ActionInventoryClickEvent actionInventoryClickEvent) {
        clickActions.put(slot, actionInventoryClickEvent);
    }

    public void unregisterSlot(int slot) {
        clickActions.remove(slot);
    }

    public void setPlayerInventoryClickEvent(int slot, ActionInventoryClickEvent actionInventoryClickEvent) {
        clickPlayerInventoryActions.put(slot, actionInventoryClickEvent);
    }

    public void unregisterPlayerInventorySlot(int slot) {
        clickPlayerInventoryActions.remove(slot);
    }

    public void onInventoryClose(InventoryCloseEvent inventoryCloseEvent) {

        for (ActionInventoryCloseEvent closeAction : closeActions) {
            closeAction.onAction(inventoryCloseEvent);
        }
    }

    public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {

        int slot = inventoryClickEvent.getSlot();
        if (clickActions.containsKey(slot)) {
            clickActions.get(slot).onAction(inventoryClickEvent);
        }
    }

    public void onPlayerInventoryClick(InventoryClickEvent inventoryClickEvent) {

        int slot = inventoryClickEvent.getSlot();
        if (clickPlayerInventoryActions.containsKey(slot)) {
            clickPlayerInventoryActions.get(slot).onAction(inventoryClickEvent);
        }
    }

    public void onClick(InventoryClickEvent inventoryClickEvent) {
        generalClickActions.forEach(actionInventoryClickEvent -> actionInventoryClickEvent.onAction(inventoryClickEvent));
    }

    public boolean closeOnClick() {
        return closeOnClick;
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    private void setInv(Inventory inv) {
        this.inv = inv;
    }
}
