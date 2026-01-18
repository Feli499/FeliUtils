package de.feli490.utils.spigot.bdstudio;

import de.feli490.utils.spigot.bdstudio.exceptions.BdStudioModelDataMissingException;
import de.feli490.utils.spigot.entities.MultipleEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;
import org.joml.Matrix4f;

public class BDModel {

    private String name;

    private List<BDComponent> components;

    public BDModel(String name, List<BDComponent> components) {
        this.name = name;
        this.components = components;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BDComponent> getComponents() {
        return this.components;
    }

    public void setComponents(List<BDComponent> components) {
        this.components = components;
    }

    public MultipleEntity spawn(Location baseLocation) {
        return this.spawn(baseLocation, false);
    }

    public MultipleEntity spawn(Location baseLocation, boolean persistent) {
        BlockDisplay baseEntity = baseLocation.getWorld().spawn(baseLocation, BlockDisplay.class);
        for (BDComponent component : this.components) {
            component.spawn(baseEntity, new Matrix4f());
        }
        baseEntity.setPersistent(persistent);

        MultipleEntity multipleEntity = new MultipleEntity(baseLocation, 0);
        multipleEntity.addEntity(new Vector(), baseEntity);
        return multipleEntity;
    }

    public static List<UUID> getModelUUIDS(BDModelLoader bdModelLoader, BlockDisplay model) throws BdStudioModelDataMissingException {
        PersistentDataContainer dataContainer = model.getPersistentDataContainer();
        if (!dataContainer.has(bdModelLoader.getModelEntitiesKey(), PersistentDataType.LONG_ARRAY)) {
            return List.of();
        }

        long[] uuidBytes = dataContainer.get(bdModelLoader.getModelEntitiesKey(), PersistentDataType.LONG_ARRAY);
        if (uuidBytes == null) {
            bdModelLoader.getLogger().warning("UUID array of model " + model.getUniqueId() + " is null.");
            throw new BdStudioModelDataMissingException("The model data is damaged. Cannot select.");
        }
        if (uuidBytes.length % 2 != 0) {
            bdModelLoader.getLogger().warning("UUID array of model " + model.getUniqueId() + " is uneven in length, is it damaged?");
            throw new BdStudioModelDataMissingException("The model data is damaged. Cannot select.");
        }
        List<UUID> result = new ArrayList<>();
        for (int i = 0; i < uuidBytes.length; i += 2) {
            UUID current = new UUID(uuidBytes[i], uuidBytes[i + 1]);
            result.add(current);
        }
        return result;
    }

    public static List<Display> getModelDisplayEntities(BDModelLoader bdStudioLoader, BlockDisplay model)
            throws BdStudioModelDataMissingException {
        List<UUID> modelUUIDs = getModelUUIDS(bdStudioLoader, model);
        List<Display> result = new ArrayList<>();

        for (UUID current : modelUUIDs) {
            Entity entity = Bukkit.getEntity(current);
            if (entity == null) {
                bdStudioLoader.getLogger().warning("Invalid entity UUID in model " + model + ": " + current);
                continue;
            }
            if (!(entity instanceof Display display)) {
                bdStudioLoader.getLogger().warning("Entity with UUID " + current + " in model " + model + " is not a Display entity");
                continue;
            }
            result.add(display);
        }

        return result;
    }

}
