package de.feli490.utils.core.search.query.expressions.basic.factories;

import de.feli490.utils.core.search.parameters.identifier.ParameterIdentifier;
import de.feli490.utils.core.search.query.expressions.ExpressionFactory;

public abstract class AbstractBasicExpressionFactory implements ExpressionFactory<ParameterIdentifier<?>> {

    @Override
    public boolean isLogical() {
        return false;
    }
}
