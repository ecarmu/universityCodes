package week6;

public class Author {
    private String name;
    private String email;
    private String biography;

    public Author(String name, String email, String biography) {
        this.name = name;
        this.email = email;
        this.biography = biography;
    }

    public Author(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name + '|'
               + email + '|' +
               biography ;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}

