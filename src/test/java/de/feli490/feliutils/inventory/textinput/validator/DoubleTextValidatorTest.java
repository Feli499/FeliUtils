package de.feli490.feliutils.inventory.textinput.validator;

import org.junit.Assert;
import org.junit.Test;

public class DoubleTextValidatorTest {

    @Test
    public void testDoubleTextValidatorWithoutNegativeParameter() {

        /* Given */
        DoubleTextValidator sut = new DoubleTextValidator(3, "ErrorText");

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

        Assert.assertFalse(sut.validate("32.5435"));
        Assert.assertFalse(sut.validate(".32.3"));
        Assert.assertFalse(sut.validate("a32.3"));
        Assert.assertFalse(sut.validate("32.3a"));
    }

    @Test
    public void testDoubleTextValidatorWithNegative() {

        /* Given */
        DoubleTextValidator sut = new DoubleTextValidator(3, "ErrorText", true);

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

        Assert.assertFalse(sut.validate("32.5435"));
        Assert.assertFalse(sut.validate(".32.3"));
        Assert.assertFalse(sut.validate("a32.3"));
        Assert.assertFalse(sut.validate("32.3a"));
    }

    @Test
    public void testDoubleTextValidatorWithoutNegative() {

        /* Given */
        DoubleTextValidator sut = new DoubleTextValidator(3, "ErrorText", false);

        /* When & Then */
        Assert.assertTrue(sut.validate("133.323"));
        Assert.assertTrue(sut.validate("213"));
        Assert.assertTrue(sut.validate("3.23"));
        Assert.assertTrue(sut.validate("1423."));
        Assert.assertTrue(sut.validate(".543"));

        Assert.assertFalse(sut.validate("-133.323"));
        Assert.assertFalse(sut.validate("-213"));
        Assert.assertFalse(sut.validate("-3.23"));
        Assert.assertFalse(sut.validate("-1423."));
        Assert.assertFalse(sut.validate("-.543"));
        Assert.assertFalse(sut.validate("32.5435"));
        Assert.assertFalse(sut.validate(".32.3"));
        Assert.assertFalse(sut.validate("a32.3"));
        Assert.assertFalse(sut.validate("32.3a"));
    }
}
