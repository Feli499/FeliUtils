package de.feli490.feliutils.search.query.expressions.logic.factories;

import de.feli490.feliutils.search.ExpressionParser;
import de.feli490.feliutils.search.StringIterator;
import de.feli490.feliutils.search.exceptions.ParseException;
import de.feli490.feliutils.search.query.expressions.Expression;
import de.feli490.feliutils.search.query.expressions.logic.OrExpression;

public class OrLogicExpressionFactory extends AbstractLogicExpressionFactory {

    public static final String IDENTIFIER = "or";

    @Override
    public Expression create(Expression expression1, StringIterator characterIterator, ExpressionParser expressionParser)
            throws ParseException {

        Expression expression2 = expressionParser.parse(characterIterator);

        return new OrExpression(expression1, expression2);
    }
}
