package week5;

public class PieceWorker extends Employee{

    public int salary;

    public PieceWorker(String firstName, String lastName, String birthMonth, int birthDay, int birthYear, int employee_id, String position, int num_items, int rate) {
        super(firstName, lastName, birthMonth, birthDay, birthYear, employee_id, position);
        salary = num_items * rate;
    }


    public String trueToString() {
        return "PieceWorker{" +
                "employeeID=" + employeeID +
                ", position='" + position + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthMonth='" + birthMonth + '\'' +
                ", birthDay=" + birthDay +
                ", birthYear=" + birthYear +
                ", salary=" + salary +
                '}';
    }

    public String toString() {
        return firstName + " " + lastName + " " + salary + " " + employeeID + " " +
                position + " " + birthMonth + " " + birthDay + " " + birthYear;

    }
}
