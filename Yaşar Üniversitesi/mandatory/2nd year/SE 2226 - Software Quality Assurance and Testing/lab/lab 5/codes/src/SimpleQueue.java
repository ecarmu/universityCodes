package week5;

import week5.ATM.CircularQueue;


public class SimpleQueue {
    // How to store last 3 value with Queue
    public static void main(String[] args) {
        CircularQueue<String> passwords = new CircularQueue<>(3);
        System.out.println("Empty Queue");
        System.out.println(passwords);
        passwords.add("1");
        passwords.add("2");
        passwords.add("3");
        System.out.println("Added 3 password to the queue");
        System.out.println(passwords);
        System.out.println("Added a value '4' to the queue");
        passwords.add("4");
        System.out.println(passwords);
        System.out.println("Added a value '5' to the queue");
        passwords.add("5");
        System.out.println(passwords);
        System.out.println("Added a value '6' to the queue");
        passwords.add("6");
        System.out.println(passwords);
        System.out.println("Added a value '7' to the queue");
        passwords.add("7");
        System.out.println(passwords);
        System.out.println("Iterate over elements");
        for (int i = 0; i < passwords.size(); i++) {
            System.out.println(passwords.get(i));
        }
//        for (String password : passwords) {
//            System.out.println(password);
//        }

    }
}
