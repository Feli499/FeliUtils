package de.feli490.feliutils.search.parameters;

import de.feli490.feliutils.search.parameters.identifier.ParameterIdentifier;

public abstract class ParameterValue<T extends Object, P extends ParameterIdentifier<T>> {

    private final P parameterIdentifier;

    protected ParameterValue(P parameterIdentifier) {

        this.parameterIdentifier = parameterIdentifier;
    }

    public P getParameterIdentifier() {
        return parameterIdentifier;
    }

    public abstract T getValue();

}
