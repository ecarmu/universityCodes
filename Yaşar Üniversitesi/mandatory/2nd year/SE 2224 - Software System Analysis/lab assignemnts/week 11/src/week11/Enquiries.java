package week11;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Enquiries {

    public String title;
    public String name;
    public String email;
    public int mobile;
    public String message;


    public Enquiries(String title, String name, String email, int mobile, String message) {
        this.title = title;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.message = message;
    }


    public void makeEnquiries() {
        File file = new File("enquiry.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("enquiry.txt", true));
            writer.write(title + "|" + name + "|" + email + "|" + mobile + "|" + message + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void replyEnquiries(){

    }
    public void deleteEnquiries(){

    }



}
