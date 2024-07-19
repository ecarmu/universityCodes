package week5;

public class HourlyEmployee extends Employee{
    public int salary;

    public String trueToString() {
        return "HourlyEmployee{" +
                "employeeID=" + employeeID +
                ", position='" + position + '\'' +
                ", salary=" + salary +
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

    public HourlyEmployee(String firstName, String lastName, String birthMonth, int birthDay, int birthYear, int employee_id, String position, int totalWorkedHour, int rate) {
        super(firstName, lastName, birthMonth, birthDay, birthYear, employee_id, position);
        salary = totalWorkedHour * rate;
    }
}
