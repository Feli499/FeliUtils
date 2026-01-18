package de.feli490.utils.spigot.entities;

import org.bukkit.util.Vector;

public class RotationUtils {

    public static Vector rotateVector(Vector vector, float yaw) {
        float cosYaw = (float) Math.cos(Math.toRadians(yaw));
        float sinYaw = (float) Math.sin(Math.toRadians(yaw));

        double x = vector.getX();
        double z = vector.getZ();

        double xRotation = cosYaw * x - sinYaw * z;
        double zRotation = sinYaw * x + cosYaw * z;

        return new Vector(xRotation, vector.getY(), zRotation);
    }
}
