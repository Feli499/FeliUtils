package de.feli490.feliutils.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemStackUtils {

    private ItemStackUtils() {}

    public static String getStringValue(ItemStack itemStack, NamespacedKey namespacedKey) {
        return getValue(itemStack, namespacedKey, PersistentDataType.STRING);
    }

    public static <T, Z> Z getValue(ItemStack itemStack, NamespacedKey namespacedKey, PersistentDataType<T, Z> persistentDataType) {

        if (!itemStack.hasItemMeta()) {
            return null;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();

        if (!persistentDataContainer.has(namespacedKey, persistentDataType)) {
            return null;
        }

        return persistentDataContainer.get(namespacedKey, persistentDataType);
    }
}
