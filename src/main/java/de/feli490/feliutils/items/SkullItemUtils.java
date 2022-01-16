package de.feli490.feliutils.items;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;

public class SkullItemUtils {

    public static ItemStack createHead(String uuid, String name, String texturesProperty) {
        return createHead(UUID.fromString(uuid), name, texturesProperty);
    }

    public static ItemStack createHead(UUID uuid, String name, String texturesProperty) {

        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        PlayerProfile playerProfile = Bukkit.createProfile(uuid, name);
        playerProfile.setProperty(new ProfileProperty("textures", texturesProperty));

        skullMeta.setPlayerProfile(playerProfile);
        itemStack.setItemMeta(skullMeta);

        return itemStack;
    }
}
