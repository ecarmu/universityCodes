package week8.GPU;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bot {
    private WebDriver driver;
    private String url;
    private HashMap<String, String> keywords;
    private final int TIMEOUT = 5;
    private Actions actionProvider;
    private ArrayList<GPU> gpuList;
    private ArrayList<GPU> ppList;

    //Lazy initialization. Don't spend time to initialize driver, if connection url is not valid.
    public Bot(String exePath, String url, HashMap<String, String> keywords) {
        if (validateUrl(url)) {
            this.url = url;
            this.keywords = keywords;
            gpuList = new ArrayList<>();
            ppList = new ArrayList<>();
            initializeDriver(exePath);
            initializeActionProvider();
        }
    }

    private boolean validateUrl(String url) {
        return true;
    }

    private void initializeDriver(String exePath) {
        System.setProperty("webdriver.chrome.driver", exePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    private void initializeActionProvider() {
        actionProvider = new Actions(driver);
    }
    /*
        Refactoring : The code in below includes repetitive code with a similar pattern.
        With a structure like in week4.ATM Rule class, it can be implemented more efficiently.
        Try to simplify the data structure.
     */

    private void navigate() {
        // Find category title by text and hover over it
        WebElement category = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver -> driver.findElement(By.linkText(keywords.get("category"))));
        actionProvider.moveToElement(category).build().perform();
        // From the sub menu, locate and click on product category
        WebElement productCategory = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver -> driver.findElement(By.linkText(keywords.get("product-category"))));
        actionProvider.doubleClick(productCategory).build().perform();
        // Find filter dropdown and expand the sub list
        WebElement filter = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver -> driver.findElement(By.id(keywords.get("filter-button"))));
        actionProvider.click(filter).build().perform();
        // Filter the result by selected model
        WebElement model = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver -> driver.findElement(By.partialLinkText(keywords.get("model"))));
        actionProvider.click(model).build().perform();
        // Check available models
//        WebElement stock = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
//                .until(driver -> driver.findElement(By.name(keywords.get("stock"))));
//        actionProvider.click(stock).build().perform();
        // Sort by selection
        WebElement sort = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver -> driver.findElement(By.id(keywords.get("sort"))));
        Select sortingList = new Select(sort);
        sortingList.selectByValue(keywords.get("sorting-value"));
    }

    private List<WebElement> getListOfProducts() {
        WebElement productList = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver -> driver.findElement(By.cssSelector(keywords.get("product-list"))));
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(driver -> productList.findElements(By.cssSelector(keywords.get("gpu"))));
    }

    private void processGPUs(List<WebElement> gpus) {
        for (WebElement gpu : gpus) {
            String rawBrand = gpu.findElement(By.className(keywords.get("brand"))).getText();
            String rawPrice = gpu.findElement(By.className(keywords.get("price"))).getText();
            String name = gpu.findElement(By.className(keywords.get("name"))).getText();
            gpuList.add(new GPU(GPU.processBrand(rawBrand), name, GPU.processPrice(rawPrice)));
        }
    }

    //Price-Performance
    private void isPP() {
        String brand;
        float price;
        for (GPU gpu : gpuList) {
            brand = gpu.getBrand();
            price = gpu.getPrice();
            // Change prices and brands to see some results.
            if ((brand.equals("ASUS") || brand.equals("MSI")) && (price < 40000)) {//Triple Fan
                ppList.add(gpu);
            }
        }
    }

    private void showResults() {
        if (!ppList.isEmpty()) {
            JOptionPane.showInputDialog(null, "Choose", "GPUs", JOptionPane.PLAIN_MESSAGE, null, ppList.toArray(), "------");
        } else {
            JOptionPane.showMessageDialog(null, "404 Not Found");
        }
    }

    private void connect() {
        driver.get(url);
    }

    private void quit() {
        actionProvider.pause(Duration.ofSeconds(TIMEOUT)).build().perform();
        driver.quit();
    }

    public void run() {
        connect();
        navigate();
        List<WebElement> gpus = getListOfProducts();
        processGPUs(gpus);
        isPP();
        quit();
        showResults();
    }
}
