package de.feli490.utils.core.search;

import java.text.MessageFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringIteratorTest {

    private String prefix;
    private char testEscapeChar;
    private String escapedText;
    private Character suffix;
    private StringIterator charIterator;

    @Before
    public void setup() {

        prefix = "prefix ";
        testEscapeChar = '"';
        escapedText = "halloIchSollteEscapedSein";
        suffix = 't';

        charIterator = new StringIterator(
                MessageFormat.format("{0}{1}{2}{3}{4}", prefix, testEscapeChar, escapedText, testEscapeChar, suffix));
    }

    @Test
    public void testCharIteratorNextUntil() {

        Assert.assertEquals(prefix, charIterator.nextUntil(testEscapeChar));
        Assert.assertEquals(escapedText, charIterator.nextUntil(testEscapeChar));
        Assert.assertEquals(suffix, charIterator.next());
    }

    @Test
    public void testIterator() {

        while (charIterator.hasNext()) {

            Assert.assertFalse(charIterator.isAtEnd());
            Assert.assertSame(charIterator.getText().charAt(charIterator.getIndex()), charIterator.next());
        }

        Assert.assertTrue(charIterator.isAtEnd());
    }
}
