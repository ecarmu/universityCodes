// https://www.selenium.dev/documentation/webdriver/

package week7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {

    public static void main(String[] args) {
        //File->Project Structure->Libraries ,  Set the path for selenium java server org.seleniumhq.selenium:selenium-java:4.8.1
        //change EXE_PATH with your pc's download path, Example : C:\\Users\\Student\\Downloads\\chromedriver.exe
        //chromedriver.exe port 9515
        //selenium server port 4444
        String EXE_PATH = "C:\\Users\\ardah\\OneDrive\\Masaüstü\\Selenium\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", EXE_PATH);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://google.com.tr");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
