package de.feli490.utils.core.search.parameters;

import de.feli490.utils.core.search.parameters.identifier.StringParameterIdentifier;

public abstract class StringParameterValue extends ComparableParameterValue<String, StringParameterIdentifier> {

    public StringParameterValue(StringParameterIdentifier parameterIdentifier) {
        super(parameterIdentifier);
    }

}
