package week5.ATM.rules.password;

import week5.ATM.Customer;
import week5.ATM.rules.Rule;

public class YearCheck implements Rule {

    private static final int yearLowerLimit = 1900;
    private static final int yearUpperLimit = 2000;

    @Override
    public boolean validate(Customer customer, Object input) {
        assert input instanceof String : "Input must be a string";
        try {
            int password = Integer.parseInt(String.valueOf(input));
            return ((password < yearLowerLimit) || (password) > yearUpperLimit);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String message() {
        return "The password can not have values between " + yearLowerLimit + " - " + yearUpperLimit;
    }
}
