package week9;



public class Main {
    public static void main(String[] args) {
        Selenium2 selenium = new Selenium2();
        selenium.connect();
        selenium.storeData();
        selenium.quit();
        System.out.println(selenium.getMovieNames());
    }

}
