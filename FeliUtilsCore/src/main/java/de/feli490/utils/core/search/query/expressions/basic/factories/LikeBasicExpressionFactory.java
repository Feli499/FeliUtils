package de.feli490.utils.core.search.query.expressions.basic.factories;

import java.text.MessageFormat;

import de.feli490.utils.core.search.ExpressionParser;
import de.feli490.utils.core.search.StringIterator;
import de.feli490.utils.core.search.exceptions.ParseException;
import de.feli490.utils.core.search.parameters.identifier.ParameterIdentifier;
import de.feli490.utils.core.search.query.expressions.Expression;
import de.feli490.utils.core.search.query.expressions.basic.LikeExpression;

public class LikeBasicExpressionFactory extends AbstractBasicExpressionFactory {

    public static final String IDENTIFIER = "~";
    private static final char ESCAPE_CHAR = '"';

    @Override
    public Expression create(ParameterIdentifier<?> parameterIdentifier, StringIterator charIterator, ExpressionParser expressionParser)
            throws ParseException {

        Class<?> parameterType = parameterIdentifier.getParameterType();

        if (!parameterType.isAssignableFrom(String.class)) {
            throw new ParseException(MessageFormat.format("Es ist keine ~-Expression für {0} möglich.", parameterType.getSimpleName()),
                    charIterator);
        }

        /*
         * Hier erwarten wir das der erste String leer ist.
         * Da zwischen dem "Key" (=) und dem ersten " nur ein Leerzeichen sein sollte.
         * Das 1. Leerzeichen wurde bereits eingelesen als der Key gelesen wurde und
         * der Escapte Character ist beim Rückgabe-Wert der charIterator.nextUntil nicht enthalten.
         */
        String first = charIterator.nextUntil(ESCAPE_CHAR);
        if (first == null || !first.isEmpty()) {
            throw new ParseException("Der gleich Ausdruck muss seinen zu prüfenden Wert in \"-Umklammert angegeben bekommen.",
                    charIterator);
        }

        String value = charIterator.nextUntil(ESCAPE_CHAR);

        if (value == null) {
            throw new ParseException("Der gleich Ausdruck muss seinen zu prüfenden Wert in \"-Umklammert angegeben bekommen.",
                    charIterator);
        }

        return new LikeExpression((ParameterIdentifier<String>) parameterIdentifier, value);
    }
}
