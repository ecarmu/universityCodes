package week11;

import javax.swing.*;

public abstract class User {
    public int ID;
    public String name;
    public String surname;
    public String email;
    public char[] password;
    public int mobile;
    public String country;

    public User(int ID, String name, String surname, String email, char[] password, int mobile, String country) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.country = country;
    }

    public User() {

    }

    public abstract void Login();

    public abstract void Logout();

    public abstract void Register();


}
