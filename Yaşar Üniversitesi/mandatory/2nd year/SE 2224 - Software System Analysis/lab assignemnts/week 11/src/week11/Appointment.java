package week11;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.time.DayOfWeek;

public class Appointment {
    public int ID;
    public int doctor_ID;
    public int patient_ID;
    public DayOfWeek day; //Date de olabilir
    public Time start_time;
    public Time end_time;

    public Appointment(int ID, int doctor_ID, int patient_ID, DayOfWeek day, Time start_time, Time end_time) {
        this.ID = ID;
        this.doctor_ID = doctor_ID;
        this.patient_ID = patient_ID;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public void bookAppointment(){
        File file = new File("appointment.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("appointment.txt", true));
            writer.write(ID + "|" + doctor_ID + "|" + patient_ID + "|" + day + "|" + start_time + "|" + end_time + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void cancelAppointment(){

    }
    public void modifyAppointment(){

    }

}
