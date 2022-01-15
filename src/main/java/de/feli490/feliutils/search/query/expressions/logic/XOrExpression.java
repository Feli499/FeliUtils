package de.feli490.feliutils.search.query.expressions.logic;

import de.feli490.feliutils.search.query.expressions.Expression;

public class XOrExpression extends AbstractLogicalExpression {

    public XOrExpression(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    @Override
    protected boolean isTrue(boolean result1, boolean result2) {
        return (result1 && !result2) || (!result1 && result2);
    }
}
