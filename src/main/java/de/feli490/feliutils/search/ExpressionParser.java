package de.feli490.feliutils.search;

import de.feli490.feliutils.search.exceptions.ParseException;
import de.feli490.feliutils.search.query.expressions.Expression;

public interface ExpressionParser {

    Expression parse(StringIterator stringIterator) throws ParseException;
}
