package de.feli490.feliutils.inventory.textinput.validator;

public interface TextValidator {

    boolean validate(String text);

    default String getErrorText() {
        return "";
    }
}
