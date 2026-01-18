package de.feli490.utils.spigot.inventory.textinput.validator;

import org.junit.Assert;
import org.junit.Test;

public class NumberTextValidatorTest {

    @Test
    public void testNumberTextValidator() {

        /* Given */
        NumberTextValidator sut = new NumberTextValidator("ErrorText");

        /* When & Then */
        Assert.assertTrue(sut.validate("213"));
        Assert.assertTrue(sut.validate("-213"));

        Assert.assertFalse(sut.validate(".32.3"));
        Assert.assertFalse(sut.validate("32.3"));
        Assert.assertFalse(sut.validate("a32.3"));
        Assert.assertFalse(sut.validate("32.3a"));
    }

    @Test
    public void testNumberTextValidatorMinValue() {

        /* Given */
        NumberTextValidator sut = new NumberTextValidator("ErrorText", -100, Integer.MAX_VALUE);

        /* When & Then */
        Assert.assertTrue(sut.validate("213"));
        Assert.assertTrue(sut.validate("100"));
        Assert.assertTrue(sut.validate("-100"));
        Assert.assertFalse(sut.validate("-101"));
    }

    @Test
    public void testNumberTextValidatorMaxValue() {

        /* Given */
        NumberTextValidator sut = new NumberTextValidator("ErrorText", Integer.MIN_VALUE, 100);

        /* When & Then */
        Assert.assertTrue(sut.validate("-213"));
        Assert.assertTrue(sut.validate("-100"));
        Assert.assertTrue(sut.validate("100"));
        Assert.assertFalse(sut.validate("101"));
    }
}
