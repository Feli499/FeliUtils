package de.feli490.utils.core.search;

import de.feli490.utils.core.search.exceptions.ParseException;
import de.feli490.utils.core.search.query.expressions.Expression;

public interface ExpressionParser {

    Expression parse(StringIterator stringIterator) throws ParseException;
}
