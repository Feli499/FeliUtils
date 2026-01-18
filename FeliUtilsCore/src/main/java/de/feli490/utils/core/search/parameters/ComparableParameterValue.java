package de.feli490.utils.core.search.parameters;

import de.feli490.utils.core.search.parameters.identifier.ComparableParameterIdentifier;

public abstract class ComparableParameterValue<T extends Comparable<T>, P extends ComparableParameterIdentifier<T>>
        extends ParameterValue<T, P> {

    protected ComparableParameterValue(P parameterIdentifier) {
        super(parameterIdentifier);
    }

    public int compare(T contains) {
        return getParameterIdentifier().compare(getValue(), contains);
    }
}
