package de.feli490.feliutils.inventory.textinput.validator;

public class NumberTextValidator implements TextValidator {

    private final String errorText;

    public NumberTextValidator(String errorText) {
        this.errorText = errorText;
    }

    @Override
    public boolean validate(String text) {
        return text.matches("[0-9]+");
    }

    public String getErrorText() {
        return errorText;
    }
}
