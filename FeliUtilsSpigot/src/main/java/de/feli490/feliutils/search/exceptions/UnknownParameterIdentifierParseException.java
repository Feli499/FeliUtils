package de.feli490.feliutils.search.exceptions;

import de.feli490.feliutils.search.StringIterator;

public class UnknownParameterIdentifierParseException extends ParseException {

    private final String parameterIdentifierID;

    public UnknownParameterIdentifierParseException(String message, StringIterator stringIterator, String parameterIdentifierID) {

        super(message, stringIterator);
        this.parameterIdentifierID = parameterIdentifierID;
    }

    public String getParameterIdentifierID() {
        return parameterIdentifierID;
    }
}
