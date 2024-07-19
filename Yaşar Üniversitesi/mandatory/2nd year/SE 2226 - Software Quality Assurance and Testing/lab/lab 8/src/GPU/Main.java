package week8.GPU;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.vatanbilgisayar.com/";
        String exePath = "C:\\Users\\ardah\\OneDrive\\Masaüstü\\Selenium\\chromedriver_win32\\chromedriver.exe";
        /*
             Refactoring : To be able to access data with meaningful key values, the HashMap data structure is implemented.
             However, desired utility can be performed by using Enum values, which is essential integer values.
             Try to simply the data structure.
         */
        HashMap<String, String> keywords = new HashMap<>();
        keywords.put("category", "Bilgisayar Parçaları");
        keywords.put("product-category", "Ekran Kartları");
        keywords.put("filter-button", "1567-Button");
        keywords.put("model", "4080");
        keywords.put("stock", "address_id");
        keywords.put("sort", "sortOptionsDesktop");
        keywords.put("sorting-value", "UP"); //Price Ascending
        keywords.put("product-list", ".wrapper-product.wrapper-product--list-page.clearfix");
        keywords.put("gpu", ".product-list.product-list--list-page");
        keywords.put("brand","product-list__product-name");
        keywords.put("name","product-list__product-name");
        keywords.put("price","product-list__price");
        Bot bot = new Bot(exePath, url, keywords);
        bot.run();
    }
}
