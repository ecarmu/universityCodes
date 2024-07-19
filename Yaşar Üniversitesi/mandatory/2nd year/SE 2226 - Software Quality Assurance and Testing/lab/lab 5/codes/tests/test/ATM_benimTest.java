package week5.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import week5.ATM.Customer;
import week5.ATM_benim.ATM_benim;

import static org.junit.jupiter.api.Assertions.*;

class ATM_benimTest {

    @Test
    void isPasswordInputValid() {
    }

    @ParameterizedTest
    @ValueSource(ints = {1111, 1313, 55555, 1907, 3045})
    void rulesChecker(int value) {
        Customer customer = new Customer("Arda", Integer.toString(value), 1000);
        ATM_benim atm_benimTest = new ATM_benim(customer, value);
        assertEquals(true, atm_benimTest.passwordValid);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1000, 2000, 3000})
    void depositMoney(double deposittedMoney) {
            Customer customer = new Customer("Arda", Integer.toString(3045), 1000);
            ATM_benim atm = new ATM_benim(customer, 3045);
            double initialBalance = customer.getBalance();
            atm.depositMoney((int) deposittedMoney);
            assertEquals(initialBalance + deposittedMoney, customer.getBalance());

    }



    @Test
    void withdrawMoney() {
    }

    @Test
    void moneyTransfer() {
    }

    @Test
    void displayAccountInfo() {
    }

    @Test
    void changePassword() {
    }
}