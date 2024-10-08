package de.feli490.feliutils.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class MultipleEntity {

    private final Map<MultipleEntityUnit, UUID> entityMap = new HashMap<>();
    private Location location;
    private float yaw;

    public MultipleEntity(Location location) {
        this(location, 0F);
    }

    public MultipleEntity(Location location, float yaw) {
        this.location = location;
        this.yaw = yaw;
    }

    public MultipleEntityUnit addEntity(Vector vector, Entity entity) {
        MultipleEntityUnit multipleEntityUnit = new MultipleEntityUnit(vector, entity);
        this.entityMap.put(multipleEntityUnit, entity.getUniqueId());
        return multipleEntityUnit;
    }

    public List<Entity> getEntities() {
        return this.entityMap.keySet().stream().map(MultipleEntityUnit::getEntity).toList();
    }

    public boolean isEntity(Entity entity) {
        return this.entityMap.containsValue(entity.getUniqueId());
    }

    public Location getLocation() {
        return this.location.clone();
    }

    public void setLocation(Location location) {
        this.setTransition(location, this.yaw);
        this.updateEntities();
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setYaw(float yaw) {
        this.setTransition(this.location, yaw);
        this.updateEntities();
    }

    public void setTransition(Location location, float yaw) {
        this.location = location.setDirection(new Vector());
        this.yaw = yaw;
        this.updateEntities();
    }

    private void updateEntities() {
        this.entityMap.forEach((multipleEntityUnit, uuid) -> multipleEntityUnit.positionEntity(this.location, this.yaw));
    }

    public void removeEntities() {
        this.entityMap.keySet().forEach(MultipleEntityUnit::remove);
        this.entityMap.clear();
    }
}
