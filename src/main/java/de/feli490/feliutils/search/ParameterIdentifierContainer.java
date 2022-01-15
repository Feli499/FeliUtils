package de.feli490.feliutils.search;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.feli490.feliutils.search.parameters.identifier.ParameterIdentifier;

public class ParameterIdentifierContainer {

    private final Map<String, ParameterIdentifier<?>> parameters;

    public ParameterIdentifierContainer() {
        parameters = new HashMap<>();
    }

    public void registerIdentifier(ParameterIdentifier<?> parameterIdentifier) {

        if (isIdentifierRegistered(parameterIdentifier)) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Ein Parameter mit der ID {0} ist bereits registriert.", parameterIdentifier.getParameterID()));
        }

        parameters.put(parameterIdentifier.getParameterID(), parameterIdentifier);
    }

    public Collection<String> getParameterIDs() {
        return parameters.keySet();
    }

    public boolean isIdentifierRegistered(ParameterIdentifier<?> parameterIdentifier) {
        return parameters.containsKey(parameterIdentifier.getParameterID());
    }

    public void unregisterIdentifier(ParameterIdentifier<?> parameterIdentifier) {

        if (!isIdentifierRegistered(parameterIdentifier)) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Ein Parameter mit der ID {0} ist nicht registriert.", parameterIdentifier.getParameterID()));
        }
        parameters.remove(parameterIdentifier.getParameterID());
    }

    public ParameterIdentifier<?> getParameterIdentifier(String key) {
        return parameters.get(key.toLowerCase(Locale.ENGLISH));
    }
}
