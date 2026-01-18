package de.feli490.feliutils.search.query.expressions;

import java.util.HashMap;
import java.util.Map;

import de.feli490.feliutils.search.query.expressions.basic.factories.AbstractBasicExpressionFactory;
import de.feli490.feliutils.search.query.expressions.basic.factories.EqualsBasicExpressionFactory;
import de.feli490.feliutils.search.query.expressions.basic.factories.LikeBasicExpressionFactory;
import de.feli490.feliutils.search.query.expressions.logic.factories.AbstractLogicExpressionFactory;
import de.feli490.feliutils.search.query.expressions.logic.factories.AndLogicExpressionFactory;
import de.feli490.feliutils.search.query.expressions.logic.factories.OrLogicExpressionFactory;
import de.feli490.feliutils.search.query.expressions.logic.factories.XOrLogicExpressionFactory;

public class ExpressionSymbols {

    private static final Map<String, AbstractBasicExpressionFactory> BASIC_SYMBOL_EXPRESSION_MAP = new HashMap<>();
    private static final Map<String, AbstractLogicExpressionFactory> LOGIC_SYMBOL_EXPRESSION_MAP = new HashMap<>();

    static {

        BASIC_SYMBOL_EXPRESSION_MAP.put(EqualsBasicExpressionFactory.IDENTIFIER, new EqualsBasicExpressionFactory());
        BASIC_SYMBOL_EXPRESSION_MAP.put(LikeBasicExpressionFactory.IDENTIFIER, new LikeBasicExpressionFactory());

        LOGIC_SYMBOL_EXPRESSION_MAP.put(AndLogicExpressionFactory.IDENTIFIER, new AndLogicExpressionFactory());
        LOGIC_SYMBOL_EXPRESSION_MAP.put(OrLogicExpressionFactory.IDENTIFIER, new OrLogicExpressionFactory());
        LOGIC_SYMBOL_EXPRESSION_MAP.put(XOrLogicExpressionFactory.IDENTIFIER, new XOrLogicExpressionFactory());

    }

    public static Map<String, AbstractBasicExpressionFactory> getBasicExpressionFactorySymbols() {
        return new HashMap<>(BASIC_SYMBOL_EXPRESSION_MAP);
    }

    public static AbstractBasicExpressionFactory getBasicExpressionFactory(String symbol) {
        return BASIC_SYMBOL_EXPRESSION_MAP.get(symbol);
    }

    public static Map<String, AbstractLogicExpressionFactory> getLogicExpressionFactorySymbols() {
        return new HashMap<>(LOGIC_SYMBOL_EXPRESSION_MAP);
    }

    public static AbstractLogicExpressionFactory getLogicExpressionFactory(String symbol) {
        return LOGIC_SYMBOL_EXPRESSION_MAP.get(symbol);
    }
}
