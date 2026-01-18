package de.feli490.utils.core.search;

import java.util.Iterator;

public class StringIterator implements Iterator<Character> {

    private final String text;
    private int index;

    public StringIterator(String text) {
        this.text = text;
        index = 0;
    }

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean hasNext() {
        return index < text.length();
    }

    @Override
    public Character next() {
        return text.charAt(index++);
    }

    public Character nextWithoutIncrease() {
        return (index < text.length() ? text.charAt(index) : null);
    }

    public boolean isAtEnd() {
        return index >= text.length();
    }

    /**
     * @param to
     *            - der char bis zu dem weiter "gelesen" werden soll
     * @return Eingelesenen String, sollte der escapte Char nicht
     *         mehr vorhanden sein oder ist bereits bis zum ende gelesen worden wird NULL zurueck gegeben werden.
     */
    public String nextUntil(char to) {

        StringBuilder stringBuilder = new StringBuilder();
        while (hasNext()) {

            char c = next();
            if (c == to) {
                return stringBuilder.toString();
            }

            stringBuilder.append(c);
        }

        return null;
    }
}
