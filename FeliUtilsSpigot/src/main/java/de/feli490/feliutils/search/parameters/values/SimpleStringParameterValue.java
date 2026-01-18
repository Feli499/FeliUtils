package de.feli490.feliutils.search.parameters.values;

import de.feli490.feliutils.search.parameters.StringParameterValue;
import de.feli490.feliutils.search.parameters.identifier.StringParameterIdentifier;

public class SimpleStringParameterValue extends StringParameterValue {

    private final String value;

    public SimpleStringParameterValue(StringParameterIdentifier stringParameterIdentifier, String value) {

        super(stringParameterIdentifier);
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
