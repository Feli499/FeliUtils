package de.feli490.feliutils.search.exceptions;

import de.feli490.feliutils.search.StringIterator;

public class ParseException extends Exception {

    private final String text;
    private final int index;

    public ParseException(String message, StringIterator charIterator) {
        this(message, charIterator.getText(), charIterator.getIndex());
    }

    public ParseException(String message, StringIterator charIterator, Throwable throwable) {
        this(message, charIterator.getText(), charIterator.getIndex(), throwable);
    }

    public ParseException(String message, String text, int index) {
        super(message);
        this.text = text;
        this.index = index;
    }

    public ParseException(String message, String text, int index, Throwable throwable) {
        super(message, throwable);
        this.text = text;
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return index;
    }
}
