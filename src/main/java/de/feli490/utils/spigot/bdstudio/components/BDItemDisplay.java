package de.feli490.utils.spigot.bdstudio.components;

import de.feli490.utils.spigot.bdstudio.BDComponentType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.joml.Matrix4f;

public class BDItemDisplay extends BDBase {

    public BDItemDisplay(String name, String nbt, Matrix4f transform) {
        super(name, nbt, transform);
    }

    @Override
    public void spawn(Entity baseEntity, Matrix4f parentTransform) {
        ItemDisplay itemDisplay = baseEntity.getWorld().spawn(baseEntity.getLocation(), ItemDisplay.class);

        String materialName = this.name().substring(0, this.name().indexOf('['));
        String displayName = this.name().substring(this.name().indexOf('=') + 1, this.name().indexOf(']'));

        ItemDisplay.ItemDisplayTransform displayTransform = ItemDisplay.ItemDisplayTransform.valueOf(displayName.toUpperCase());
        ItemStack itemStack = Bukkit.getItemFactory().createItemStack(materialName + this.nbt());

        itemDisplay.setItemStack(itemStack);
        itemDisplay.setItemDisplayTransform(displayTransform);

        Matrix4f childTransform = new Matrix4f(parentTransform);
        childTransform.mul(this.transform());
        itemDisplay.setTransformationMatrix(childTransform);

        baseEntity.addPassenger(itemDisplay);
    }

    @Override
    public BDComponentType type() {
        return BDComponentType.ITEM_DISPLAY;
    }

}
