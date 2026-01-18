package de.feli490.utils.spigot.inventory.textinput.validator;

public interface TextValidator {

    boolean validate(String text);

    default String getErrorText() {
        return "";
    }
}
