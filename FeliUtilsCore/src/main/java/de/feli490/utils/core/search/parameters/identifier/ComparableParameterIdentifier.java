package de.feli490.utils.core.search.parameters.identifier;

public abstract class ComparableParameterIdentifier<T extends Comparable<T>> extends ParameterIdentifier<T> {

    protected ComparableParameterIdentifier(String parameterID, Class<T> clazz) {
        super(parameterID, clazz);
    }

    public int compare(T base, T toCompare) {
        return base.compareTo(toCompare);
    }
}
