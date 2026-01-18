package de.feli490.utils.core.search;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.feli490.utils.core.search.parameters.ParameterValue;
import de.feli490.utils.core.search.parameters.identifier.ParameterIdentifier;

public interface ParameterValueContainer {

    Collection<ParameterValue<?, ?>> getParameterValues();

    default Collection<ParameterValue<?, ?>> getParameterValue(ParameterIdentifier<?> parameterIdentifier) {

        Set<ParameterValue<?, ?>> set = new HashSet<>();
        Collection<ParameterValue<?, ?>> parameterValues = getParameterValues();
        for (ParameterValue<?, ?> parameterValue : parameterValues) {
            if (parameterValue.getParameterIdentifier().equals(parameterIdentifier)) {
                set.add(parameterValue);
            }
        }
        return set;
    }

    default boolean hasParameterValue(ParameterIdentifier<?> parameterIdentifier) {
        return !getParameterValue(parameterIdentifier).isEmpty();
    }
}
