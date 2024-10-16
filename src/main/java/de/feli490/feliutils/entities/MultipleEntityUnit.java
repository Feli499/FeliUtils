package de.feli490.feliutils.entities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class MultipleEntityUnit {

    private final Vector position;
    private final Entity entity;
    private final float baseYaw;
    private final float basePitch;

    public MultipleEntityUnit(Vector position, Entity entity) {
        this.position = position;
        this.entity = entity;

        Location location = entity.getLocation();
        this.baseYaw = location.getYaw();
        this.basePitch = location.getPitch();
    }

    public Vector getPosition() {
        return this.position;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public float getBaseYaw() {
        return this.baseYaw;
    }

    public float getBasePitch() {
        return this.basePitch;
    }

    public void positionEntity(Location location, float yaw) {
        Vector vector = MultipleEntityUtils.rotateVector(this.position, yaw);
        Location add = location.clone().add(vector);
        add.setYaw(this.baseYaw + yaw);
        add.setPitch(this.basePitch);
        this.entity.teleport(add);
    }

    public void remove() {
        this.entity.remove();
    }
}
