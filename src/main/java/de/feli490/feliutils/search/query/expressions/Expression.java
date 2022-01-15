package de.feli490.feliutils.search.query.expressions;

import de.feli490.feliutils.search.ParameterValueContainer;

public interface Expression {

    boolean isTrue(ParameterValueContainer parameterValueContainer);

}
