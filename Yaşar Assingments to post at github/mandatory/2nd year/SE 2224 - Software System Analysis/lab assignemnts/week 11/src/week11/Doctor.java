package week11;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Doctor extends User{

    public String qualifications;

    @Override
    public String toString() {
        return "Doctor{" +
                "qualifications='" + qualifications + '\'' +
                ", department='" + department + '\'' +
                ", ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password=" + Arrays.toString(password) +
                ", mobile=" + mobile +
                ", country='" + country + '\'' +
                '}';
    }

    public String department;

    public Doctor(int ID, String name, String surname, String email, char[] password, int mobile, String country, String qual, String dept) {
        super(ID, name, surname, email, password, mobile, country);

        qualifications = qual;
        department = dept;
    }

    public Doctor() {
        super();
    }

    @Override
    public void Login() {

    }

    @Override
    public void Logout() {

    }

    @Override
    public void Register() {

    }

    public void addDoctor(){
        File file = new File("doctor.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("doctor.txt", true));
            writer.write(ID + "|" + name + "|" + surname + "|" + email + "|" + String.valueOf(password) + "|" + mobile + "|" + country+ "|" + qualifications + "|" + department + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Doctor createDoctorFromLine(String line) throws ParseException {
        String[] data = line.split("\\|");
        int id = Integer.parseInt(data[0]);
        String name = data[1];
        String surname = data[2];
        String email = data[3];
        char[] password = data[4].toCharArray();
        int mobile = Integer.parseInt(data[5]);
        String country = data[6];
        String qual = data[7];
        String dept = data[8];


        Doctor doctor = new Doctor(id, name, surname, email, password, mobile, country, qual, dept);
        return doctor;
    }

    public void editDoctorInfo(){

    }

    public void deleteDoctor(){

    }

    public void searchDoctor(){
        // bu Doctor dönebilir
    }
}
