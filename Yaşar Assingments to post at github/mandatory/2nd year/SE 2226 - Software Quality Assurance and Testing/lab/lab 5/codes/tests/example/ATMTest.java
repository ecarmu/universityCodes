package week5.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import week5.ATM.Customer;
import week5.ATM.CustomerConverter;

import static org.junit.jupiter.api.Assertions.*;

class ATMTest {
    Customer customer = new Customer("Arda", "3045", 10340);


    @ParameterizedTest
    @CsvSource({
            "'Arda, 3045, 2000', 1000",
            "'Jane, 3789, 8000', 3000"
    })
    void deposit(@ConvertWith(CustomerConverter.class) Customer customer, int depositedMoney) {
        double initialBalance = customer.getBalance();
        double newBalance = calculateNewBalance(initialBalance, depositedMoney);
        customer.setBalance(newBalance);
        assertEquals(initialBalance + depositedMoney, customer.getBalance());
    }

    double calculateNewBalance(double initialBalance, int depositedMoney) {
        return initialBalance + depositedMoney;
    }

    @Test
    void withdraw() {
    }

    @Test
    void transferToCustomer() {
    }

    @Test
    void changePassword() {
    }
}