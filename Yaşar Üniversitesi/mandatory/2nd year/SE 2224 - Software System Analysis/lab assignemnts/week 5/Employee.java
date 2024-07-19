package week5;

public abstract class Employee extends Person{

    public int employeeID;
    public String position;


    public Employee(String firstName, String lastName, String birthMonth, int birthDay, int birthYear, int employeeID, String position) {
        super(firstName, lastName, birthMonth, birthDay, birthYear);
        this.employeeID = employeeID;
        this.position = position;
    }
}
