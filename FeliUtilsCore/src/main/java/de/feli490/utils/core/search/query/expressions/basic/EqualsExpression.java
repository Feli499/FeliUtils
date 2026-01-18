package de.feli490.utils.core.search.query.expressions.basic;

import de.feli490.utils.core.search.ParameterValueContainer;
import de.feli490.utils.core.search.parameters.ParameterValue;
import de.feli490.utils.core.search.parameters.identifier.ParameterIdentifier;
import de.feli490.utils.core.search.query.expressions.Expression;

public class EqualsExpression<T> implements Expression {

    private final ParameterIdentifier<T> parameterIdentifier;
    private final T value;

    public EqualsExpression(ParameterIdentifier<T> parameterIdentifier, T value) {

        this.parameterIdentifier = parameterIdentifier;
        this.value = value;
    }

    @Override
    public boolean isTrue(ParameterValueContainer parameterValueContainer) {

        if (!parameterValueContainer.hasParameterValue(parameterIdentifier)) {
            return false;
        }

        for (ParameterValue<?, ?> parameterValue : parameterValueContainer.getParameterValue(parameterIdentifier)) {
            if (parameterIdentifier.equals(((ParameterValue<T, ?>) parameterValue).getValue(), value)) {
                return true;
            }
        }
        return false;
    }
}
