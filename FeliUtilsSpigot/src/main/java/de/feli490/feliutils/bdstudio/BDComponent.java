package de.feli490.feliutils.bdstudio;

import org.bukkit.entity.Entity;
import org.joml.Matrix4f;

public interface BDComponent {

    String name();

    String nbt();

    Matrix4f transform();

    void name(String name);

    void nbt(String nbt);

    void transform(Matrix4f transform);

    void spawn(Entity baseEntity, Matrix4f parentTransform);

    BDComponentType type();

}
