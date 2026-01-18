package de.feli490.feliutils.inventory.abbruch;

import org.bukkit.event.inventory.InventoryClickEvent;

import de.feli490.feliutils.inventory.ActionInventory;
import de.feli490.feliutils.inventory.ActionInventoryClickEvent;

public class AbbrechenActionInventoryClickEvent implements ActionInventoryClickEvent {

    private final AbbruchActionInventory abbruchActionInventory;

    public AbbrechenActionInventoryClickEvent(ActionInventory headcaseActionInventory) {
        abbruchActionInventory = new AbbruchActionInventory(headcaseActionInventory);
    }

    @Override
    public void onAction(InventoryClickEvent inventoryClickEvent) {
        abbruchActionInventory.open(inventoryClickEvent.getWhoClicked());
    }
}
