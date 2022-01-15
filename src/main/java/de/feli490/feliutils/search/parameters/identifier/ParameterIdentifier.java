package de.feli490.feliutils.search.parameters.identifier;

import java.util.Locale;
import java.util.Objects;

public abstract class ParameterIdentifier<T> {

    private final String parameterID;
    private final Class<T> parameterType;

    protected ParameterIdentifier(String parameterID, Class<T> clazz) {

        this.parameterID = Objects.requireNonNull(parameterID).toLowerCase(Locale.ENGLISH);
        parameterType = Objects.requireNonNull(clazz);

        if (parameterID.contains(" ")) {
            throw new IllegalArgumentException("Parameter Identifier are not allowed to contain spaces.");
        }
    }

    public Class<T> getParameterType() {
        return parameterType;
    }

    public String getParameterID() {
        return parameterID;
    }

    public boolean equals(T base, T isEquals) {
        return base.equals(isEquals);
    }

    public abstract T parse(String toParse) throws Exception;

    @Override
    public String toString() {
        return "ParameterIdentifier{" + "parameterID='" + parameterID + '\'' + ", parameterType=" + parameterType + '}';
    }
}
