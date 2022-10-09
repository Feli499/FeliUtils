package de.feli490.feliutils.inventory.textinput.validator;

import org.junit.Assert;
import org.junit.Test;

public class NumberTextValidatorTest {

    @Test
    public void testNumberTextValidatorWithoutNegativeParameter() {

        /* Given */
        NumberTextValidator sut = new NumberTextValidator("ErrorText");

        /* When & Then */
        Assert.assertTrue(sut.validate("213"));
        Assert.assertTrue(sut.validate("-213"));

        Assert.assertFalse(sut.validate("133.323"));
        Assert.assertFalse(sut.validate("3.23"));
        Assert.assertFalse(sut.validate("1423."));
        Assert.assertFalse(sut.validate(".543"));
        Assert.assertFalse(sut.validate("-133.323"));
        Assert.assertFalse(sut.validate("-3.23"));
        Assert.assertFalse(sut.validate("-1423."));
        Assert.assertFalse(sut.validate("-.543"));

        Assert.assertFalse(sut.validate("32.5435"));
        Assert.assertFalse(sut.validate(".32.3"));
        Assert.assertFalse(sut.validate("a32.3"));
        Assert.assertFalse(sut.validate("32.3a"));
    }

    @Test
    public void testNumberTextValidatorWithNegative() {

        /* Given */
        NumberTextValidator sut = new NumberTextValidator("ErrorText", true);

        /* When & Then */
        Assert.assertTrue(sut.validate("213"));
        Assert.assertTrue(sut.validate("-213"));

        Assert.assertFalse(sut.validate("133.323"));
        Assert.assertFalse(sut.validate("3.23"));
        Assert.assertFalse(sut.validate("1423."));
        Assert.assertFalse(sut.validate(".543"));

        Assert.assertFalse(sut.validate("-133.323"));
        Assert.assertFalse(sut.validate("-3.23"));
        Assert.assertFalse(sut.validate("-1423."));
        Assert.assertFalse(sut.validate("-.543"));

        Assert.assertFalse(sut.validate("32.5435"));
        Assert.assertFalse(sut.validate(".32.3"));
        Assert.assertFalse(sut.validate("a32.3"));
        Assert.assertFalse(sut.validate("32.3a"));
        Assert.assertFalse(sut.validate("323a"));
    }

    @Test
    public void testNumberTextValidatorWithoutNegative() {

        /* Given */
        NumberTextValidator sut = new NumberTextValidator("ErrorText", false);

        /* When & Then */
        Assert.assertTrue(sut.validate("213"));
        Assert.assertFalse(sut.validate("-213"));

        Assert.assertFalse(sut.validate("133.323"));
        Assert.assertFalse(sut.validate("3.23"));
        Assert.assertFalse(sut.validate("1423."));
        Assert.assertFalse(sut.validate(".543"));

        Assert.assertFalse(sut.validate("-133.323"));
        Assert.assertFalse(sut.validate("-3.23"));
        Assert.assertFalse(sut.validate("-1423."));
        Assert.assertFalse(sut.validate("-.543"));

        Assert.assertFalse(sut.validate("32.5435"));
        Assert.assertFalse(sut.validate(".32.3"));
        Assert.assertFalse(sut.validate("a32.3"));
        Assert.assertFalse(sut.validate("32.3a"));
        Assert.assertFalse(sut.validate("323a"));
    }
}
