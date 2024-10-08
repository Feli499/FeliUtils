package de.feli490.feliutils.entities;

import org.bukkit.util.Vector;
import org.junit.Assert;
import org.junit.Test;

public class MultipleEntityUtilsTest {

    @Test
    public void testRotationYaw() {

        /* Given */
        Vector vector = new Vector(1, 0, 1);

        /* When */
        Vector result45 = MultipleEntityUtils.rotateVector(vector, 45);
        Vector result90 = MultipleEntityUtils.rotateVector(vector, 90);
        Vector result135 = MultipleEntityUtils.rotateVector(vector, 135);
        Vector result180 = MultipleEntityUtils.rotateVector(vector, 180);
        Vector result225 = MultipleEntityUtils.rotateVector(vector, 225);
        Vector result270 = MultipleEntityUtils.rotateVector(vector, 270);
        Vector result315 = MultipleEntityUtils.rotateVector(vector, 315);
        Vector result360 = MultipleEntityUtils.rotateVector(vector, 360);

        /* Then */
        Assert.assertEquals(new Vector(0, 0, 1).clone().normalize(), result45.clone().normalize());
        Assert.assertEquals(new Vector(-1, 0, 1).clone().normalize(), result90.clone().normalize());
        Assert.assertEquals(new Vector(-1, 0, 0).clone().normalize(), result135.clone().normalize());
        Assert.assertEquals(new Vector(-1, 0, -1).clone().normalize(), result180.clone().normalize());
        Assert.assertEquals(new Vector(0, 0, -1).clone().normalize(), result225.clone().normalize());
        Assert.assertEquals(new Vector(1, 0, -1).clone().normalize(), result270.clone().normalize());
        Assert.assertEquals(new Vector(1, 0, 0).clone().normalize(), result315.clone().normalize());
        Assert.assertEquals(new Vector(1, 0, 1).clone().normalize(), result360.clone().normalize());

        double length = vector.length();
        Assert.assertEquals(length, result45.length(), 0.05);
        Assert.assertEquals(length, result90.length(), 0.05);
        Assert.assertEquals(length, result135.length(), 0.05);
        Assert.assertEquals(length, result180.length(), 0.05);
        Assert.assertEquals(length, result225.length(), 0.05);
        Assert.assertEquals(length, result270.length(), 0.05);
        Assert.assertEquals(length, result315.length(), 0.05);
        Assert.assertEquals(length, result360.length(), 0.05);
    }
}
