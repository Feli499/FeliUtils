package de.feli490.utils.core.search.parameters.identifier;

import java.text.ParseException;
import java.util.Locale;

public class StringParameterIdentifier extends ComparableParameterIdentifier<String> {

    public StringParameterIdentifier(String parameterID) {
        super(parameterID, String.class);
    }

    @Override
    public int compare(String base, String toCompare) {
        return super.compare(base.toLowerCase(Locale.ENGLISH), toCompare.toLowerCase(Locale.ENGLISH));
    }

    @Override
    public String parse(String toParse) throws ParseException {
        return toParse;
    }

    @Override
    public boolean equals(String base, String isEquals) {
        return base.equalsIgnoreCase(isEquals);
    }
}
