package de.feli490.feliutils.entities;

import org.bukkit.util.Vector;

public class MultipleEntityUtils {

    public static Vector rotateVector(Vector vector, float yaw) {

        float cosYaw = (float) Math.cos(Math.toRadians(yaw));
        float sinYaw = (float) Math.sin(Math.toRadians(yaw));

        double x = vector.getX();
        double z = vector.getZ();

        double xRotation = cosYaw * x + -sinYaw * x;
        double zRotation = sinYaw * z + cosYaw * z;

        return new Vector(xRotation, vector.getY(), zRotation);
    }
}
