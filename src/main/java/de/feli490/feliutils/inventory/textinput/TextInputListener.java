package de.feli490.feliutils.inventory.textinput;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import de.feli490.feliutils.inventory.ActionInventory;

public class TextInputListener implements Listener {

    private final JavaPlugin javaPlugin;
    private final TextInputInventoryContainer textInputInventoryContainer;

    public TextInputListener(JavaPlugin javaPlugin) {

        this.javaPlugin = javaPlugin;
        textInputInventoryContainer = TextInputInventoryContainer.getInstance();
    }

    @EventHandler
    public void closeInventory(InventoryCloseEvent e) {

        if (!(e.getInventory()instanceof AnvilInventory anvilInventory))
            return;

        TextInputInventory textInputInventory = textInputInventoryContainer.getTextInputInventory(anvilInventory);
        if (textInputInventory == null)
            return;

        HumanEntity player = e.getPlayer();

        if (e.getViewers().isEmpty())
            textInputInventoryContainer.removeTextInputInventory(textInputInventory);

        ActionInventory actionInventory = textInputInventory.getActionInventory();
        if (actionInventory != null && InventoryCloseEvent.Reason.PLAYER.equals(e.getReason()))
            javaPlugin.getServer().getScheduler().scheduleSyncDelayedTask(javaPlugin,
                    () -> player.openInventory(actionInventory.getInventory()));
    }

    @EventHandler
    public void prepareAnvil(PrepareAnvilEvent e) {

        TextInputInventory textInputInventory = textInputInventoryContainer.getTextInputInventory(e.getInventory());
        if (textInputInventory == null)
            return;

        String renameText;

        ItemStack result = e.getResult();
        if (result == null || !result.hasItemMeta()) {
            renameText = "";
        } else {

            ItemMeta itemMeta = result.getItemMeta();
            if (!itemMeta.hasDisplayName())
                renameText = "";
            else
                renameText = itemMeta.getDisplayName();
        }

        ItemStack resultItemStack = textInputInventory.getResultItemStack(renameText);
        e.setResult(resultItemStack);
    }

    @EventHandler
    public void clickInventory(InventoryClickEvent e) {

        InventoryView view = e.getView();
        if (!(view.getTopInventory()instanceof AnvilInventory anvilInventory))
            return;

        TextInputInventory textInputInventory = textInputInventoryContainer.getTextInputInventory(anvilInventory);
        if (textInputInventory == null)
            return;

        e.setCancelled(true);

        if (e.getSlot() == 2)
            textInputInventory.confirmClick();
    }
}
