package de.feli490.feliutils.inventory.textinput.validator;

public class NumberTextValidator implements TextValidator {

    private final String errorText;
    private final boolean allowNegative;

    public NumberTextValidator(String errorText) {
        this(errorText, true);
    }

    public NumberTextValidator(String errorText, boolean allowNegative) {
        this.allowNegative = allowNegative;
        this.errorText = errorText;
    }

    @Override
    public boolean validate(String text) {
        return text.matches("%s[0-9]+".formatted(allowNegative ? "-?" : ""));
    }

    public String getErrorText() {
        return errorText;
    }
}
