package week5.ATM.rules;

import week5.ATM.Customer;

public interface Rule {
    /*
      Maybe this interface can be used with only single parameter
      So that you don't have to force arguments to match with function signature
     */
    boolean validate(Customer customer, Object input);
    String message();
}
