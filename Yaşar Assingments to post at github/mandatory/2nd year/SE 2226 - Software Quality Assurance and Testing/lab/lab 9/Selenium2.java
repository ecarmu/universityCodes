package week9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Selenium2 {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String EXE_PATH = "C:\\Users\\ardah\\OneDrive\\Masaüstü\\Selenium\\chromedriver_win32\\chromedriver.exe";
    private final String WEBSITE = "https://www.worldometers.info/coronavirus/";

    private final String MOVIE_CLASS = "even";
    private final String MOVIE_CLASS2 = "odd";

    private final String LIST_CSS_SELECTOR = ".table.table-bordered.table-hover.main_table_countries.dataTable.no-footer";
    private final String NAME_CLASS = "mt_a";
    private ArrayList<String> movieNames;
    public Selenium2() {
        movieNames = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", EXE_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void storeData() {
        WebElement movieList = driver.findElement(By.cssSelector(LIST_CSS_SELECTOR));
        List<WebElement> movies = movieList.findElements(By.className(MOVIE_CLASS));
        List<WebElement> movies2 = movieList.findElements(By.className(MOVIE_CLASS2));

        // List <WebElement> moviesWhole = concatMovies(movies, movies2);

        List <WebElement> moviesWhole = makeList(movieList);

        for (WebElement movie : moviesWhole) {
            List<WebElement> td = movie.findElements(By.tagName("td"));
            String name = td.get(1).getText();
            System.out.println(name);
            movieNames.add(name);
            //String year = header.findElements(By.tagName("span")).get(1).getText();
        }
    }

    /*private List<WebElement> concatMovies(List<WebElement> movies, List<WebElement> movies2) {
        List<WebElement> newList = new ArrayList<>();
        int j = 0, k = 0;

        int moviesWholeSize = movies.size() + movies2.size();

        System.out.println("movies (even) size: " + movies.size());
        System.out.println("movies2 (odd) size: " + movies2.size());
        for(int i = 0; i<moviesWholeSize; i++){

            if(i == 0|| i == 1){
                newList.add(movies2.get(k));
                k++;
            }

            if(i % 2 == 0){
                newList.add(movies.get(j));
                j++;
                System.out.println("j: " + j);
            }


            if(i %2 != 0){
                newList.add(movies2.get(k));
                k++;
                System.out.println("k: " + k);
            }
        }

        return newList;
    }*/

    public void connect() {
        driver.get(WEBSITE);
    }

    public void quit() {
        driver.quit();
    }

    public ArrayList<String> getMovieNames() {
        return movieNames;
    }

    public List<WebElement> makeList(WebElement movieList){
        List<WebElement> fakeList = movieList.findElements(By.className(MOVIE_CLASS));
        List<WebElement> fakeList2 = movieList.findElements(By.className(MOVIE_CLASS2));

        int size = fakeList.size() + fakeList2.size();

/*
        for (int i = 0; i < size; i++) {
            if(movieList.getClass(). == MOVIE_CLASS);
        }*/

        // hata vermesin diye öylesine return yazdım
        return fakeList;
    }
}

