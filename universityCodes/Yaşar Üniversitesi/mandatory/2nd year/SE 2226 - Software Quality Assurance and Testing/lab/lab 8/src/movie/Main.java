package week8.movie;

public class Main {
    public static void main(String[] args) {
        Selenium selenium = new Selenium();
        selenium.connect();
        selenium.locateData();
        selenium.quit();
        System.out.println(selenium.getMovieNames());
    }
}
