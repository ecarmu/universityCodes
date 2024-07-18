package week5.ATM_benim;

import week5.ATM.CircularQueue;
import week5.ATM.Customer;

public class ATM_benim {

    public boolean passwordValid;
    public Customer customer;
    public int input;
    public CircularQueue<Integer> last3Passwords = new CircularQueue<Integer>(3);
    public String errorMessage = "";

    public ATM_benim(Customer customer, int input){
        this.customer = customer;

        this.input = input;

        isPasswordInputValid(this.input);

        if(passwordValid){
            last3Passwords.add(input);
        }
    }

    public boolean isPasswordInputValid(int input) {

        if(rulesChecker(input)){
            passwordValid = true;
        }
        else passwordValid = false;

        return passwordValid;


    }

    public boolean rulesChecker(int input) {
        if(2000>=input && input>=1900){
            errorMessage = "Şifre >=1900 ve <=2000 olmalı";
            return false;
        }
        if(Integer.toString(input).length() != 4){
            errorMessage = "Şifre uzunluğu 4 olmalı";
            return false;
        }
        if((Integer.toString(input).charAt(0) == Integer.toString(input).charAt(2)) && (Integer.toString(input).charAt(1) == Integer.toString(input).charAt(3)) ){
            errorMessage = "şifrenin 1. ve 3. hanesi veya 2. ve 4. hanesi aynı ";
            return false;
        }

        if(Integer.toString(input).charAt(0) == Integer.toString(input).charAt(1) && Integer.toString(input).charAt(1) == Integer.toString(input).charAt(2) && Integer.toString(input).charAt(2) == Integer.toString(input).charAt(3)){
            errorMessage = "Şifrenin tüm sayıları aynı olamaz";
            return false;
        }
        /*if(last3Passwords.get(0) == input || last3Passwords.get(1) == input || last3Passwords.get(2) == input){
            errorMessage = "Önceden girilen 3 şifre ile aynı";
            return false;
        }*/

        return true;
    }


    public void depositMoney(int moneyDeposited){
        if(moneyDeposited >= 0)
        customer.setBalance(customer.getBalance() + moneyDeposited);

        else System.out.println("Negatif sayıda para yatırılamaz");
    }
    public void withdrawMoney(int moneyWithdrawed){


        if(moneyWithdrawed <= 0)
            System.out.println("Negatif sayıda para yatırılamaz");

        else if(customer.getBalance() >= moneyWithdrawed)
        customer.setBalance(customer.getBalance() - moneyWithdrawed);

        else System.out.println("Hesapta bu kadar withdraw edecek para yok");
    }
    public void moneyTransfer (Customer customer, int money){
        customer.setBalance(customer.getBalance() + money);
    }
    public void displayAccountInfo(){
        System.out.println(customer.toString());
    }
    public void changePassword(String newPassword){
        customer.setPassword(newPassword);
    }
}
