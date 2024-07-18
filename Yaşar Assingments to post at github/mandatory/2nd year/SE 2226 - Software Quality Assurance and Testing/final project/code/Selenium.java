package Gamermarkt;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Selenium {


    private final static String GururExePath = "C:\\Java\\Java\\src\\drivers\\chromedriver_win32\\chromedriver.exe";
    private final static String EXE_PATH_ARDA = "C:\\Users\\ardah\\OneDrive\\Masaüstü\\Selenium\\chromedriver_win32\\chromedriver.exe";

    ArrayList<Processor> processorList = new ArrayList<>();

    WebDriver driver;

    public boolean BVAandEP(String price){

        String priceText2 = "";

        try{
            JavascriptExecutor js = ((JavascriptExecutor) driver);
            js.executeScript("window.scrollTo(0, 1000)");


            Thread.sleep(400);

            WebElement minPrice = driver.findElement(By.xpath("//*[@id=\"min-price\"]"));
            minPrice.click();
            minPrice.sendKeys(price);
            Thread.sleep(400);

            WebElement maxPrice = driver.findElement(By.xpath("//*[@id=\"max-price\"]"));
            maxPrice.click();
            maxPrice.sendKeys(price);
            Thread.sleep(900);

            WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"priceContent\"]/div/div/div[3]/a"));
            searchButton.click();

            WebElement altPrice = driver.findElement(By.cssSelector("#productsLoad > div > div.product-list__content > div.product-list__cost > span.product-list__price"));

            String priceText = altPrice.getText();

            priceText = priceText.replace("." , "");

            priceText2 = priceText;
        }
        catch (Exception ex){

            WebElement noProcessorFind = driver.findElement(By.xpath("//*[@id=\"product-list-container\"]/div/div/div[3]/div/h3"));
            String warning = noProcessorFind.getText();
            if(warning.equals("ARADIĞINIZ KRİTERLERE\n" + "UYGUN ÜRÜN BULUNAMADI.")){
                return true;
            }
        }

        return  price.contains(priceText2);


    }

    public boolean Search(String processorBrand, double starRate, double processorSpeed)  {

        try{
            MarkCheckbox(processorBrand,  starRate,  processorSpeed);

            if ((starRate > 5))
                throw new Exception();

            getProcessor();
            return Validate(processorList,processorBrand,  starRate,  processorSpeed);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

        return false;
    }


    public void getProcessor(){
        processorList.clear();

        List<WebElement> brandList = driver.findElements(By.className("product-list__link"));
        List<WebElement> ratingList = driver.findElements(By.className("wrapper-star"));

        for (int i = 0; i < brandList.size(); i++) {
            WebElement brand = brandList.get(i);
            WebElement rating = ratingList.get(i);

            String brandText = brand.getText();
            WebElement ratingAlt = rating.findElement(By.className("score"));

            String ratingText = ratingAlt.getAttribute("style");
            ratingText = ratingText.replaceAll("[^0-9]", "");
            int ratingInt = Integer.parseInt(ratingText) / 20;
            ratingText = "" + ratingInt;

            String processorSpeedText = "";

            String[] tempText = brandText.split(" ");

            for (int j = 0; j < tempText.length; j++) {
                if (tempText[j].contains("GHz") || tempText[j].contains("ghz") || tempText[j].contains("GHZ") || tempText[j].contains("Ghz")) {
                    if(tempText[j].matches(".*\\d+.*")){
                        processorSpeedText = tempText[j];

                    }
                    else
                        processorSpeedText = tempText[j-1];
                }
            }

            processorSpeedText =  processorSpeedText.replace("GHZ", "");
            processorSpeedText =  processorSpeedText.replace("GHz", "");
            processorSpeedText =  processorSpeedText.replace("Ghz", "");
            processorSpeedText =  processorSpeedText.replace("ghz", "");

            brandText = brandText.split(" ")[0];
            brandText = brandText.split("\n")[1];
            brandText=  brandText.toLowerCase();

            Processor p = new Processor(brandText,ratingText,processorSpeedText);
            processorList.add(p);
        }
    }

    public boolean Validate(List<Processor> list,String processorBrand, double starRate, double processorSpeed){

        int starRateInt = (int) starRate;


        String starRateText = Integer.toString(starRateInt);
        String processorSpeedText = Double.toString(processorSpeed);

        processorBrand = processorBrand.toLowerCase();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).brand.equals(processorBrand)){
                if(list.get(i).rating.equals(starRateText)){
                    System.out.println("processor speed: " +  processorSpeed + " processorSpeedText:" + processorSpeedText);
                    if(list.get(i).processorSpeed.contains(processorSpeedText)){

                    }
                    else {
                        Exception processorSpeedException = new Exception("Processor speed values of website (" +list.get(i).processorSpeed + ") and parameters ("+ processorSpeedText+") do not match.");

                        processorSpeedException.printStackTrace();
                        return false;
                    }
                }
                else {
                    Exception starRateException = new Exception("Star rate values of website (" +list.get(i).rating + ") and parameters ("+ starRateText+") do not match.");

                    starRateException.printStackTrace();
                    return  false;
                }
            }
            else{
                Exception brandException = new Exception("Brand values values of website (" +list.get(i).brand + ") and parameters ("+ processorBrand+") do not match.");

                brandException.printStackTrace();
                return false;
            }
        }
        return true;


    }



    public void InitWebsite(){
        System.setProperty("webdriver.chrome.driver",EXE_PATH_ARDA);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--whitelisted-ips=''");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.vatanbilgisayar.com/islemciler/");

        WebElement notifications = driver.findElement(By.xpath("/html/body/div[8]/div/div[2]"));
        notifications.click();
    }

    public void CheckBoxMarker(WebElement webElement){


        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", webElement);


    }


    public void MarkCheckbox(String processorBrand, double starRate, double processorSpeed) throws InterruptedException {

        try{
            Thread.sleep(1000);
            if(!driver.findElements(By.className("main-filter-list__button")).isEmpty()){
                WebElement clearButton = driver.findElement(By.className("main-filter-list__button"));
                clearButton.click();
            }

            if(starRate > 5)
                throw new Exception();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }


        JavascriptExecutor js = ((JavascriptExecutor) driver);

        WebElement AmdOrIntel = driver.findElement(By.cssSelector("#\\31 -Content > div.panel-body.wrapper-filter-list > ul"));
        List<WebElement> elementList = AmdOrIntel.findElements(By.className("filter-list__item"));


        elementList.removeIf(element -> !element.findElement(By.className("filter-list__text")).getAttribute("innerHTML").contains(processorBrand));

        if(processorBrand.equals("AMD") || processorBrand.equals("INTEL")){
            for (int i = 0; i < elementList.size(); i++) {
                Thread.sleep(1000);
                CheckBoxMarker(elementList.get(i).findElement(By.className("filter-list__text")));

            }
        }

        WebElement fourOrFiveStar = driver.findElement(By.cssSelector( "#starsContent > div > ul"));
        List<WebElement> elementList2 = fourOrFiveStar.findElements(By.className("filter-list__item"));

        elementList2.removeIf(element2 -> !element2.findElement(By.cssSelector(".filter-list__text.filter-list__star-text")).getAttribute("innerHTML").contains(String.valueOf(starRate)));

        if (starRate == 4.0 ||starRate == 5.0){
            for (int i = 0; i < elementList2.size(); i++) {
                Thread.sleep(1000);

                CheckBoxMarker(elementList2.get(i).findElement(By.className("filter-list__text")));
            }
        }

        WebElement GHzSpeed = driver.findElement(By.cssSelector( "#\\32 0-Content > div > ul"));
        List<WebElement> elementList3 = GHzSpeed.findElements(By.className("filter-list__item"));


        elementList3.removeIf(element3 -> !element3.findElement(By.className("filter-list__text")).getAttribute("innerHTML").contains(String.valueOf(processorSpeed)));

        if ( !(starRate > 5) ){
            if(processorSpeed == 3.6 || processorSpeed == 3.7){
                for (int i = 0; i < elementList3.size(); i++) {
                    Thread.sleep(2000);
                    CheckBoxMarker(elementList3.get(i).findElement(By.className("filter-list__text")));
                }
            }
        }
        try{
            Thread.sleep(2000);

            if(starRate > 5)
                throw new Exception();
        }
        catch (Exception excd){
            excd.printStackTrace();
        }

    }


    public void correctLogIn(){

        try{


            driver.get("https://www.vatanbilgisayar.com/login/?ReturnUrl=%2FuyeBilgi%2FuyeBilgi%2F");
            WebElement email = driver.findElement(By.cssSelector("#email"));

            email.click();
            email.sendKeys("se2226.proje@gmail.com");
            Thread.sleep(1500);

            WebElement password = driver.findElement(By.cssSelector("#pass"));

            password.click();
            password.sendKeys("se2226_proje_dummy");
            Thread.sleep(500);

            WebElement acceptButton = driver.findElement(By.cssSelector("#login-button"));
            acceptButton.click();


        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }


    public void incorrectLogIn(){

        try{
            driver.get("https://www.vatanbilgisayar.com/login/?ReturnUrl=%2FuyeBilgi%2FuyeBilgi%2F");
            WebElement email = driver.findElement(By.cssSelector("#email"));
            email.click();
            email.sendKeys("se2226.proje@gmail.com");
            Thread.sleep(1500);

            WebElement password = driver.findElement(By.cssSelector("#pass"));
            password.click();
            password.sendKeys("se2226_proje_clever");
            Thread.sleep(500);

            WebElement acceptButton = driver.findElement(By.cssSelector("#login-button"));
            acceptButton.click();


        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

    public void addToCart() {

        try {
            driver.get("https://www.vatanbilgisayar.com");
            WebElement search = driver.findElement(By.cssSelector("#navbar-search-input"));
            Thread.sleep(1500);

            search.click();
            search.sendKeys("işlemciler");
            Thread.sleep(1500);

            WebElement item = driver.findElement(By.cssSelector("#search-product-image"));

            item.click();

            Thread.sleep(1500);

            WebElement addToCart = driver.findElement(By.cssSelector("#add-to-cart-button > span:nth-child(2)"));
            addToCart.click();



        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public void addToFavorites() {


        try {
            driver.get("https://www.vatanbilgisayar.com");
            WebElement search = driver.findElement(By.cssSelector("#navbar-search-input"));
            Thread.sleep(1500);

            search.click();
            search.sendKeys("işlemciler");
            Thread.sleep(1500);

            WebElement item = driver.findElement(By.cssSelector("#search-product-image"));

            item.click();

            Thread.sleep(1000);

            WebElement addToFavorites = driver.findElement(By.cssSelector("#fav_Icon > span"));
            addToFavorites.click();



        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }




}