package de.feli490.feliutils.search.query.expressions;

import de.feli490.feliutils.search.ExpressionParser;
import de.feli490.feliutils.search.StringIterator;
import de.feli490.feliutils.search.exceptions.ParseException;

public interface ExpressionFactory<T> {

    Expression create(T object, StringIterator characterIterator, ExpressionParser expressionParser) throws ParseException;

    boolean isLogical();
}
