package de.feli490.utils.core.search.query.expressions;

import de.feli490.utils.core.search.ParameterValueContainer;

public interface Expression {

    boolean isTrue(ParameterValueContainer parameterValueContainer);

}
