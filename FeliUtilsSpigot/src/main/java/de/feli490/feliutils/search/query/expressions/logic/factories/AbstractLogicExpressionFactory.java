package de.feli490.feliutils.search.query.expressions.logic.factories;

import de.feli490.feliutils.search.query.expressions.Expression;
import de.feli490.feliutils.search.query.expressions.ExpressionFactory;

public abstract class AbstractLogicExpressionFactory implements ExpressionFactory<Expression> {

    @Override
    public boolean isLogical() {
        return true;
    }
}
