package week5;

public abstract class Person {
    public String firstName;
    public String lastName;
    public String birthMonth;
    public int birthDay;
    public int birthYear;

    public Person(String firstName, String lastName, String birthMonth, int birthDay, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;
    }
}
