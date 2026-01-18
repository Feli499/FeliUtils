package de.feli490.utils.spigot.inventory.textinput.validator;

public class DoubleTextValidator implements TextValidator {

    private final double minValue;
    private final double maxValue;
    private final String errorText;

    public DoubleTextValidator(String errorText) {
        this(errorText, -Double.MAX_VALUE, Double.MAX_VALUE);
    }

    public DoubleTextValidator(String errorText, double minValue, double maxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.errorText = errorText;
    }

    @Override
    public boolean validate(String text) {

        try {

            double parsedValue = Double.parseDouble(text);
            return parsedValue >= minValue && parsedValue <= maxValue;

        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorText() {
        return errorText;
    }
}
