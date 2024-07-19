package week8.movie;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Selenium {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String EXE_PATH = "C:\\Users\\ardah\\OneDrive\\Masaüstü\\Selenium\\chromedriver_win32\\chromedriver.exe";
    private final String WEBSITE = "https://www.imdb.com/search/title/?groups=top_250&sort=user_rating";
    private final String MOVIE_CLASS = ".lister-item.mode-advanced";
    private final String LIST_CLASS = "lister-list";
    private final String NAME_CLASS = "lister-item-header";
    private ArrayList<String> movieNames;
    public Selenium() {
        movieNames = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", EXE_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void storeData() {
        WebElement movieList = driver.findElement(By.className(LIST_CLASS));
        List<WebElement> movies = movieList.findElements(By.cssSelector(MOVIE_CLASS));
        for (WebElement movie : movies) {
            WebElement header = movie.findElement(By.className(NAME_CLASS));
            String name = header.findElement(By.tagName("a")).getText();
            movieNames.add(name);
            //String year = header.findElements(By.tagName("span")).get(1).getText();
        }
    }

    public void locateData() {
        for (int i = 0; i < 4; i++) {
            storeData();
            driver.findElement(By.linkText("Next »")).click();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(MOVIE_CLASS)));
        }
        storeData();
    }


    public void connect() {
        driver.get(WEBSITE);
    }

    public void quit() {
        driver.quit();
    }

    public ArrayList<String> getMovieNames() {
        return movieNames;
    }
}
