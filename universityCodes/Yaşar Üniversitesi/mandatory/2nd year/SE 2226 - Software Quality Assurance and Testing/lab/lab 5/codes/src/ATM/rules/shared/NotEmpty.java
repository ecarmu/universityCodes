package week5.ATM.rules.shared;

import week5.ATM.Customer;
import week5.ATM.rules.Rule;

public class NotEmpty implements Rule {
    @Override
    public boolean validate(Customer customer, Object input) {
        return (customer != null) && (input != null);
    }

    @Override
    public String message() {
        return "Parameter is null";
    }
}
