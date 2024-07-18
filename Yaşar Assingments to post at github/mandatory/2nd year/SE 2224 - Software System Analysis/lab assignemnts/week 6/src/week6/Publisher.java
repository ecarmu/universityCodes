package week6;

public class Publisher {
    private String name;
    private String address;
    private String email;

    public Publisher(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Publisher(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name + '|' +
                address + '|' +
                email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

