package de.feli490.feliutils.search.query;

import java.util.Collection;
import java.util.List;

import de.feli490.feliutils.search.ParameterValueContainer;
import de.feli490.feliutils.search.query.expressions.Expression;

public interface Query extends Expression {

    <T extends ParameterValueContainer> List<T> filter(Collection<T> containers);

}
