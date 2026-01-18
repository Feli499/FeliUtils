package de.feli490.utils.core.search.exceptions;

import de.feli490.utils.core.search.StringIterator;

public class UnknownExpressionParseException extends ParseException {

    private final String unknownSymbol;

    public UnknownExpressionParseException(String message, StringIterator stringIterator, String unknownSymbol) {

        super(message, stringIterator);
        this.unknownSymbol = unknownSymbol;
    }

    public String getUnknownSymbol() {
        return unknownSymbol;
    }
}
