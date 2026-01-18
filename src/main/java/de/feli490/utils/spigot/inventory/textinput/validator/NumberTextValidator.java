package de.feli490.utils.spigot.inventory.textinput.validator;

public class NumberTextValidator implements TextValidator {

    private final String errorText;
    private final int minValue;
    private final int maxValue;

    public NumberTextValidator(String errorText) {
        this(errorText, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public NumberTextValidator(String errorText, int minValue, int maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.errorText = errorText;
    }

    @Override
    public boolean validate(String text) {

        try {

            int parsedValue = Integer.parseInt(text);
            return parsedValue >= minValue && parsedValue <= maxValue;

        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorText() {
        return errorText;
    }
}
