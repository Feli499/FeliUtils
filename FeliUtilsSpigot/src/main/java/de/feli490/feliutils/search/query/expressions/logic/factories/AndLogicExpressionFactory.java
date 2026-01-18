package de.feli490.feliutils.search.query.expressions.logic.factories;

import de.feli490.feliutils.search.ExpressionParser;
import de.feli490.feliutils.search.StringIterator;
import de.feli490.feliutils.search.exceptions.ParseException;
import de.feli490.feliutils.search.query.expressions.Expression;
import de.feli490.feliutils.search.query.expressions.logic.AndExpression;

public class AndLogicExpressionFactory extends AbstractLogicExpressionFactory {

    public static final String IDENTIFIER = "and";

    @Override
    public Expression create(Expression expression1, StringIterator characterIterator, ExpressionParser expressionParser)
            throws ParseException {

        Expression expression2 = expressionParser.parse(characterIterator);

        return new AndExpression(expression1, expression2);
    }
}
