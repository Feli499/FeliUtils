package de.feli490.feliutils.inventory.textinput;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.inventory.AnvilInventory;

public class TextInputInventoryContainer {

    private static TextInputInventoryContainer INSTANCE;

    private final Set<TextInputInventory> textInputInventories = new HashSet<>();

    private TextInputInventoryContainer() {}

    public static TextInputInventoryContainer getInstance() {

        if (INSTANCE == null)
            INSTANCE = new TextInputInventoryContainer();

        return INSTANCE;
    }

    public void addTextInputInventory(TextInputInventory textInputInventory) {
        textInputInventories.add(textInputInventory);
    }

    void removeTextInputInventory(TextInputInventory textInputInventory) {
        textInputInventories.remove(textInputInventory);
    }

    public TextInputInventory getTextInputInventory(AnvilInventory anvilInventory) {

        for (TextInputInventory textInputInventory : textInputInventories) {
            if (textInputInventory.getAnvilInventory() == anvilInventory) {
                return textInputInventory;
            }
        }
        return null;
    }
}
