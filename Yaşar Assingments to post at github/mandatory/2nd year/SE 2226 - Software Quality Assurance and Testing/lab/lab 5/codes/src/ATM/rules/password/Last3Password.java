package week5.ATM.rules.password;

import week5.ATM.Customer;
import week5.ATM.rules.Rule;

public class Last3Password implements Rule {
    @Override
    public boolean validate(Customer customer, Object input) {
        assert input instanceof String : "Input must be a string";
        String password = (String) input;
        boolean inLastTree = false;
//        for (String customerPassword : customer.getPasswords()) {
//            if (customerPassword.equals(password)) {
//                inLastTree = true;
//                break;
//            }
//        }
        for (int i = 0; i < customer.getPasswords().size(); i++) {
            if (customer.getPasswords().get(i).equals(password)) {
                inLastTree = true;
                break;
            }
        }
        return !inLastTree;
    }

    @Override
    public String message() {
        return "New password must be different from the last 3 password";
    }
}
