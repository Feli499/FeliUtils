package de.feli490.feliutils.entities;

import org.bukkit.util.Vector;
import org.junit.Assert;
import org.junit.Test;

public class MultipleEntityUtilsTest {

    @Test
    public void testRotationYaw() {

        /* Given */
        Vector vector = new Vector(1, 0, 1).normalize();

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
        Assert.assertEquals(new Vector(0, 0, 1).normalize(), result45);
        Assert.assertEquals(new Vector(-1, 0, 1).normalize(), result90);
        Assert.assertEquals(new Vector(-1, 0, 0).normalize(), result135);
        Assert.assertEquals(new Vector(-1, 0, -1).normalize(), result180);
        Assert.assertEquals(new Vector(0, 0, -1).normalize(), result225);
        Assert.assertEquals(new Vector(1, 0, -1).normalize(), result270);
        Assert.assertEquals(new Vector(1, 0, 0).normalize(), result315);
        Assert.assertEquals(new Vector(1, 0, 1).normalize(), result360);
    }
}
