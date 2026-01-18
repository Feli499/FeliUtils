package de.feli490.feliutils.bdstudio.components;

import de.feli490.feliutils.bdstudio.BDComponent;
import org.joml.Matrix4f;

public abstract class BDBase implements BDComponent {

    private String name;
    private String nbt;
    private Matrix4f transform;

    public BDBase(String name, String nbt, Matrix4f transform) {
        this.name = name;
        this.nbt = nbt;
        this.transform = transform;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String nbt() {
        return this.nbt;
    }

    @Override
    public Matrix4f transform() {
        return this.transform;
    }

    @Override
    public void name(String name) {
        this.name = name;
    }

    @Override
    public void nbt(String nbt) {
        this.nbt = nbt;
    }

    @Override
    public void transform(Matrix4f transform) {
        this.transform = transform;
    }

}
