package de.feli490.feliutils.search.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.feli490.feliutils.search.ParameterValueContainer;
import de.feli490.feliutils.search.query.expressions.Expression;

public class AppendedQuerry implements Query {

    private final Expression first;
    private final Expression second;

    public AppendedQuerry(Expression first, Expression second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public <T extends ParameterValueContainer> List<T> filter(Collection<T> containers) {

        List<T> filtered = new ArrayList<>();

        for (T parameterValueContainer : containers) {
            if (isTrue(parameterValueContainer)) {
                filtered.add(parameterValueContainer);
            }
        }

        return filtered;
    }

    @Override
    public boolean isTrue(ParameterValueContainer parameterValueContainer) {
        return first.isTrue(parameterValueContainer) && second.isTrue(parameterValueContainer);
    }
}
