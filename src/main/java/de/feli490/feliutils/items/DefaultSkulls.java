package de.feli490.feliutils.items;

import java.util.UUID;

import org.bukkit.inventory.ItemStack;

public enum DefaultSkulls {

    RED_CROSS("5ecfabf0-5253-47b0-a44d-9a0c924081b9", "RED_CROSS",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmViNTg4YjIxYTZmOThhZDFmZjRlMDg1YzU1MmRjYjA1MGVmYzljYWI0MjdmNDYwNDhmMThmYzgwMzQ3NWY3In19fQ=="),
    GREEN_CHECKMARK("034a0e9e-8745-4fc6-8639-84abc48e9f72", "GREEN_CHECKMARK",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTkyZTMxZmZiNTljOTBhYjA4ZmM5ZGMxZmUyNjgwMjAzNWEzYTQ3YzQyZmVlNjM0MjNiY2RiNDI2MmVjYjliNiJ9fX0="),
    OPEN_CHEST("6cea9d9c-a908-4eec-a16e-0fdb0ce11fb8", "OPEN_CHEST",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjZhY2ZjNjQzZjYwOGUxNmRlMTkzMzVkZGNhNzFhODI4ZGZiOGRhY2E1NzkzZWI1YmJjYjBjN2QxNTU5MjQ5In19fQ==");

    private ItemStack head;

    DefaultSkulls(String ownerUUIDString, String ownerName, String texturesProperty) {
        head = SkullItemUtils.createHead(UUID.fromString(ownerUUIDString), ownerName, texturesProperty);
    }

    public ItemStack getItemStack() {
        return head.clone();
    }
}
