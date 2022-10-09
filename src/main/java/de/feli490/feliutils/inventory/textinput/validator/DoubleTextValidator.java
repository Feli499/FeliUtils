package de.feli490.feliutils.inventory.textinput.validator;

public class DoubleTextValidator implements TextValidator {

    private final int decimalPlace;
    private final String errorText;
    private final boolean allowNegative;

    public DoubleTextValidator(int decimalPlace, String errorText) {
        this(decimalPlace, errorText, true);
    }

    public DoubleTextValidator(int decimalPlace, String errorText, boolean allowNegative) {
        this.decimalPlace = decimalPlace;
        this.errorText = errorText;
        this.allowNegative = allowNegative;
    }

    @Override
    public boolean validate(String text) {
        return text.matches("%s[0-9]*\\.?[0-9]{0,%d}".formatted(allowNegative ? "-?" : "", decimalPlace));
    }

    public String getErrorText() {
        return errorText;
    }
}
