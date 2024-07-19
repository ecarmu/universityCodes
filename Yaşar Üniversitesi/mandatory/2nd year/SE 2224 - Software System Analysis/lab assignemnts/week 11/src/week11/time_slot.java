package week11;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;

public class time_slot {
    public int ID;
    public int Doctor_ID;
    public Date day; //bunu Date değil de Day'de yapmaya bak
    public Time start_time;
    public Time end_time;


    public time_slot(int ID, int doctor_ID, Date day, Time start_time, Time end_time) {
        this.ID = ID;
        Doctor_ID = doctor_ID;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
    }


    public void addAvailableTime(){
        File file = new File("time_slot.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("time_slot.txt", true));
            writer.write("\n" + ID + "|" + Doctor_ID + "|" + day + "|" + start_time + "|" + end_time );
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
