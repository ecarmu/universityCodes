package Gamermarkt;

public class Main {

    // This class is for testing coverage analysis


    public static void main(String[] args) {

        Selenium selenium = new Selenium();
        selenium.incorrectLogIn();
        selenium.correctLogIn();

        selenium.addToCart();

        selenium.addToFavorites();


        selenium.InitWebsite();


        selenium.Search("AMD", 4.0, 3.7);

        selenium.Search("AMD", 4.0, 53.7);
        selenium.Search("AMD", -1.0, 3.7);
        selenium.Search("ASUS", 4.0, 3.7);

        selenium.Search("AMD", 120.0, 3.7);


        selenium.BVAandEP("6477");
        selenium.BVAandEP("1000");



        selenium.incorrectLogIn();

        selenium.correctLogIn();


        selenium.addToCart();

        selenium.addToFavorites();



    }
}
