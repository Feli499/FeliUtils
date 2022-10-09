package de.feli490.feliutils.inventory.textinput.validator;

import org.junit.Assert;
import org.junit.Test;

public class DoubleTextValidatorTest {

    @Test
    public void testDoubleTextValidator() {

        /* Given */
        DoubleTextValidator sut = new DoubleTextValidator("ErrorText");

        /* When & Then */
        Assert.assertTrue(sut.validate("133.323"));
        Assert.assertTrue(sut.validate("213"));
        Assert.assertTrue(sut.validate("3.23"));
        Assert.assertTrue(sut.validate("1423."));
        Assert.assertTrue(sut.validate(".543"));
        Assert.assertTrue(sut.validate("-133.323"));
        Assert.assertTrue(sut.validate("-213"));
        Assert.assertTrue(sut.validate("-3.23"));
        Assert.assertTrue(sut.validate("-1423."));
        Assert.assertTrue(sut.validate("-.543"));
        Assert.assertTrue(sut.validate("32.5435"));

        Assert.assertFalse(sut.validate(".32.3"));
        Assert.assertFalse(sut.validate("a32.3"));
        Assert.assertFalse(sut.validate("32.3a"));
    }

    @Test
    public void testDoubleTextValidatorMinValue() {

        /* Given */
        DoubleTextValidator sut = new DoubleTextValidator("ErrorText", -150, Double.MAX_VALUE);

        /* When & Then */
        Assert.assertTrue(sut.validate("3.23"));
        Assert.assertTrue(sut.validate("1423."));
        Assert.assertTrue(sut.validate(".543"));
        Assert.assertTrue(sut.validate("-133.323"));
        Assert.assertTrue(sut.validate("-3.23"));
        Assert.assertTrue(sut.validate("-.543"));
        Assert.assertTrue(sut.validate("-150"));

        Assert.assertFalse(sut.validate("-150.1"));
        Assert.assertFalse(sut.validate("-213"));
        Assert.assertFalse(sut.validate("-1423."));
    }

    @Test
    public void testDoubleTextValidatorMaxValue() {

        /* Given */
        DoubleTextValidator sut = new DoubleTextValidator("ErrorText", -Double.MAX_VALUE, 100);

        /* When & Then */
        Assert.assertTrue(sut.validate("3.23"));
        Assert.assertTrue(sut.validate(".543"));
        Assert.assertTrue(sut.validate("-133.32"));
        Assert.assertTrue(sut.validate("-3.23"));
        Assert.assertTrue(sut.validate("-.543"));
        Assert.assertTrue(sut.validate("-150"));
        Assert.assertTrue(sut.validate("-150.1"));
        Assert.assertTrue(sut.validate("-213"));
        Assert.assertTrue(sut.validate("100"));

        Assert.assertFalse(sut.validate("1423."));
        Assert.assertFalse(sut.validate("100.1"));
    }
}
