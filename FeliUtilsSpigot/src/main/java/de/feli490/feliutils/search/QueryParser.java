package de.feli490.feliutils.search;

import java.text.MessageFormat;

import de.feli490.feliutils.search.exceptions.ParseException;
import de.feli490.feliutils.search.exceptions.UnknownExpressionParseException;
import de.feli490.feliutils.search.exceptions.UnknownParameterIdentifierParseException;
import de.feli490.feliutils.search.parameters.identifier.ParameterIdentifier;
import de.feli490.feliutils.search.query.AppendedQuerry;
import de.feli490.feliutils.search.query.ExpressionQuerry;
import de.feli490.feliutils.search.query.Query;
import de.feli490.feliutils.search.query.expressions.Expression;
import de.feli490.feliutils.search.query.expressions.ExpressionSymbols;
import de.feli490.feliutils.search.query.expressions.basic.factories.AbstractBasicExpressionFactory;
import de.feli490.feliutils.search.query.expressions.logic.factories.AbstractLogicExpressionFactory;

public class QueryParser implements ExpressionParser {

    public static final char SPACE_SEPPERATOR = ' ';
    private final ParameterIdentifierContainer parameterIdentifierContainer;

    public QueryParser(ParameterIdentifierContainer parameterIdentifierContainer) {
        this.parameterIdentifierContainer = parameterIdentifierContainer;
    }

    public Query append(Expression expression, String toAppend) throws ParseException {
        return new AppendedQuerry(expression, parse(toAppend));
    }

    public Query parse(String toParse) throws ParseException {

        StringIterator iterator = new StringIterator(toParse);

        return new ExpressionQuerry(parse(iterator));
    }

    @Override
    public Expression parse(StringIterator stringIterator) throws ParseException {

        try {

            char c = stringIterator.nextWithoutIncrease();

            if (c == '(') {

                stringIterator.next();

                Expression expression = parse(stringIterator);

                if (stringIterator.isAtEnd() || stringIterator.next().equals(')')) {
                    return expression;
                }

                return logicExpression(expression, stringIterator);
            }

            Expression expression = basicExpression(stringIterator);

            if (stringIterator.isAtEnd() || stringIterator.next().equals(')')) {
                return expression;
            }
            return logicExpression(expression, stringIterator);

        } catch (StringIndexOutOfBoundsException e) {
            throw new ParseException("Der StringIterator war leer.", stringIterator, e);
        }
    }

    private Expression logicExpression(Expression expression, StringIterator stringIterator) throws ParseException {

        String symbol = stringIterator.nextUntil(SPACE_SEPPERATOR);

        AbstractLogicExpressionFactory factory = ExpressionSymbols.getLogicExpressionFactory(symbol);

        if (factory == null) {
            throw new UnknownExpressionParseException(MessageFormat.format("Unbekannter Ausdruck: {0}", symbol), stringIterator, symbol);
        }

        return factory.create(expression, stringIterator, this);
    }

    private Expression basicExpression(StringIterator stringIterator) throws ParseException {

        String parameter = stringIterator.nextUntil(SPACE_SEPPERATOR);
        if (parameter == null) {
            throw new ParseException("Ein Parameter wurder nicht korrekt angegeben", stringIterator);
        }

        ParameterIdentifier<?> parameterIdentifier = parameterIdentifierContainer.getParameterIdentifier(parameter);
        if (parameterIdentifier == null) {
            throw new UnknownParameterIdentifierParseException(MessageFormat.format("Unbekannter ParameterIdentifier: {0}", parameter),
                    stringIterator, parameter);
        }

        String symbol = stringIterator.nextUntil(SPACE_SEPPERATOR);
        if (symbol == null) {
            throw new ParseException("Kein Expression-Symbol angegeben.", stringIterator);
        }

        AbstractBasicExpressionFactory factory = ExpressionSymbols.getBasicExpressionFactory(symbol);

        if (factory == null) {
            throw new UnknownExpressionParseException(MessageFormat.format("Unbekannter Ausdruck: {0}", symbol), stringIterator, symbol);
        }

        return factory.create(parameterIdentifier, stringIterator, this);
    }

}
