package week5;

public class ComissionEmployee extends Employee{
    public double salary;


    public String trueToString() {
        return "ComissionEmployee{" +
                "salary=" + salary +
                ", employeeID=" + employeeID +
                ", position='" + position + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthMonth='" + birthMonth + '\'' +
                ", birthDay=" + birthDay +
                ", birthYear=" + birthYear +
                '}';
    }

    public String toString() {
        return firstName + " " + lastName + " " + salary + " " + employeeID + " " +
                position + " " + birthMonth + " " + birthDay + " " + birthYear;

    }


    public ComissionEmployee(String firstName, String lastName, String birthMonth, int birthDay, int birthYear, int employee_id, String position, int baseSalary, int num_items, int item_price, int comission_rate) {
        super(firstName, lastName, birthMonth, birthDay, birthYear, employee_id, position);

        salary = baseSalary + (num_items * ( item_price * ( comission_rate * .01 ) ) );
    }
}
