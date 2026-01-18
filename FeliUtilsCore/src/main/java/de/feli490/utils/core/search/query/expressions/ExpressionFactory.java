package de.feli490.utils.core.search.query.expressions;

import de.feli490.utils.core.search.ExpressionParser;
import de.feli490.utils.core.search.StringIterator;
import de.feli490.utils.core.search.exceptions.ParseException;

public interface ExpressionFactory<T> {

    Expression create(T object, StringIterator characterIterator, ExpressionParser expressionParser) throws ParseException;

    boolean isLogical();
}
