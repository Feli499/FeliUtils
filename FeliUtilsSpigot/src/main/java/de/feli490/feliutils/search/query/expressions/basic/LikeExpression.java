package de.feli490.feliutils.search.query.expressions.basic;

import java.text.MessageFormat;
import java.util.Locale;

import de.feli490.feliutils.search.ParameterValueContainer;
import de.feli490.feliutils.search.parameters.ParameterValue;
import de.feli490.feliutils.search.parameters.identifier.ParameterIdentifier;
import de.feli490.feliutils.search.query.expressions.Expression;

public class LikeExpression implements Expression {

    private final ParameterIdentifier<String> parameterIdentifier;
    private final String value;

    public LikeExpression(ParameterIdentifier<String> parameterIdentifier, String value) {

        this.parameterIdentifier = parameterIdentifier;
        this.value = value;
    }

    @Override
    public boolean isTrue(ParameterValueContainer parameterValueContainer) {

        if (!parameterValueContainer.hasParameterValue(parameterIdentifier)) {
            return false;
        }

        for (ParameterValue<?, ?> parameterValue : parameterValueContainer.getParameterValue(parameterIdentifier)) {
            if (((ParameterValue<String, ?>) parameterValue).getValue().toLowerCase(Locale.ENGLISH)
                    .contains(value.toLowerCase(Locale.ENGLISH))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return MessageFormat.format("EqualsExpression'{'comparableParameterIdentifier={0}, value={1}'}'", parameterIdentifier, value);
    }
}
