package de.feli490.feliutils.bdstudio.components;

import com.google.common.collect.ImmutableList;
import de.feli490.feliutils.bdstudio.BDComponent;
import de.feli490.feliutils.bdstudio.BDComponentType;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Entity;
import org.joml.Matrix4f;

public class BDCollection extends BDBase {

    private List<BDComponent> children = new ArrayList<>();

    public BDCollection(String name, String nbt, Matrix4f transform) {
        super(name, nbt, transform);
    }

    @Override
    public void spawn(Entity baseEntity, Matrix4f parentTransform) {
        Matrix4f childMatrix = new Matrix4f(parentTransform);
        childMatrix.mul(this.transform());
        for (BDComponent child : children) {
            child.spawn(baseEntity, childMatrix);
        }
    }

    @Override
    public BDComponentType type() {
        return BDComponentType.COLLECTION;
    }

    public void addChild(BDComponent child) {
        if (child != null) {
            this.children.add(child);
        }
    }

    public List<BDComponent> children() {
        return ImmutableList.copyOf(children);
    }

}
