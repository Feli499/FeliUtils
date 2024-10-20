package de.feli490.feliutils.bdstudio.components;

import de.feli490.feliutils.bdstudio.BDComponentType;
import org.bukkit.Bukkit;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.joml.Matrix4f;

public class BDBlockDisplay extends BDBase {

    public BDBlockDisplay(String name, String nbt, Matrix4f transform) {
        super(name, nbt, transform);
    }

    @Override
    public void spawn(Entity baseEntity, Matrix4f parentTransform) {
        BlockData blockData = Bukkit.createBlockData(this.name());

        BlockDisplay blockDisplay = baseEntity.getWorld().spawn(baseEntity.getLocation(), BlockDisplay.class);

        // TODO implement NBT?
        blockDisplay.setBlock(blockData);

        Matrix4f childTransform = new Matrix4f(parentTransform);
        childTransform.mul(this.transform());

        blockDisplay.setTransformationMatrix(childTransform);

        baseEntity.addPassenger(blockDisplay);
    }

    @Override
    public BDComponentType type() {
        return BDComponentType.BLOCK_DISPLAY;
    }

}
