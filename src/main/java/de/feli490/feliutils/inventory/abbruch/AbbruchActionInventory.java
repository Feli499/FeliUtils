package de.feli490.feliutils.inventory.abbruch;

import java.text.MessageFormat;

import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.feli490.feliutils.inventory.ActionInventory;
import de.feli490.feliutils.items.SkullItemUtils;

public class AbbruchActionInventory {

    private final ActionInventory headcaseActionInventory;

    public AbbruchActionInventory(ActionInventory headcaseActionInventory) {
        this.headcaseActionInventory = headcaseActionInventory;
    }

    public void open(HumanEntity player) {

        ActionInventory abbruchActionInventory = ActionInventory.createActionInventory(false, 9, "Wirklich Abbrechen?");
        Inventory inventory = abbruchActionInventory.getInventory();

        ItemStack bestaetigenItemStack = SkullItemUtils.GREEN_CHECKMARK.getItemStack();
        ItemMeta bestaetigenItemMeta = bestaetigenItemStack.getItemMeta();
        bestaetigenItemMeta.setDisplayName(MessageFormat.format("{0}Ja", ChatColor.GREEN));
        bestaetigenItemStack.setItemMeta(bestaetigenItemMeta);

        inventory.setItem(1, bestaetigenItemStack);
        abbruchActionInventory.setClickEvent(1, clickEvent -> clickEvent.getWhoClicked().closeInventory());

        ItemStack abbrechenItemStack = SkullItemUtils.RED_CROSS.getItemStack();
        ItemMeta abbrechenItemMeta = abbrechenItemStack.getItemMeta();
        abbrechenItemMeta.setDisplayName(MessageFormat.format("{0}Nein", ChatColor.RED));
        abbrechenItemStack.setItemMeta(abbrechenItemMeta);

        inventory.setItem(7, abbrechenItemStack);
        abbruchActionInventory.setClickEvent(7,
                clickEvent -> clickEvent.getWhoClicked().openInventory(headcaseActionInventory.getInventory()));

        player.openInventory(inventory);
    }
}
