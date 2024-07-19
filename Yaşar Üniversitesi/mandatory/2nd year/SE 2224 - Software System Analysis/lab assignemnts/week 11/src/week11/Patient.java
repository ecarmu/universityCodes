package week11;

import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Patient extends User{

    public Date date_of_birth;

    public Patient(int ID, String name, String surname, String email, char[] password, int mobile, String country, Date birthDate) {
        super(ID, name, surname, email, password, mobile, country);
        date_of_birth = birthDate;
    }

    public Patient() {
        super();
    }

    @Override
    public String toString() {
        return "Patient{" +
                "date_of_birth=" + date_of_birth +
                ", ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password=" + Arrays.toString(password) +
                ", mobile=" + mobile +
                ", country='" + country + '\'' +
                '}';
    }

    public static Patient createPatientFromLine(String line) throws ParseException {
        String[] data = line.split("\\|");
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        String surname = data[2];
        String email = data[3];
        char[] password = data[4].toCharArray();
        int mobile = Integer.parseInt(data[5]);
        String country = data[6];



        String[] words = data[7].split(" ");

        int year = Integer.parseInt(words[5]);
        String month = words[1];
        String day = words[2];

        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        int monthNumber = Arrays.asList(months).indexOf(month) + 1;

        String output = String.format("%d-%02d-%02d", year, monthNumber, Integer.parseInt(day));



        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(output);

        Patient patient = new Patient(id, name, surname, email, password, mobile, country, date);
        return patient;
    }


    @Override
    public void Login() {
        new CustomerScreen(this);
    }

    @Override
    public void Logout() {

    }

    @Override
    public void Register() {
        addPatient();
    }


    public void addPatient(){
        File file = new File("patient.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("patient.txt", true));
            writer.write(ID + "|" + name + "|" + surname + "|" + email + "|" + String.valueOf(password) + "|" + mobile + "|" + country+ "|" + date_of_birth + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editPatient(){

    }

    public void deletePatient(){

    }
}
