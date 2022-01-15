package de.feli490.feliutils.search.query.expressions.logic;

import de.feli490.feliutils.search.ParameterValueContainer;
import de.feli490.feliutils.search.query.expressions.Expression;

public abstract class AbstractLogicalExpression implements Expression {

    private final Expression expression1;
    private final Expression expression2;

    protected AbstractLogicalExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public boolean isTrue(ParameterValueContainer parameterValueContainer) {

        boolean result1 = expression1.isTrue(parameterValueContainer);
        boolean result2 = expression2.isTrue(parameterValueContainer);

        return isTrue(result1, result2);
    }

    protected abstract boolean isTrue(boolean result1, boolean result2);
}
