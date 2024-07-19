package week8.GPU;

public class GPU {

    private final String brand;
    private final String name;
    private final float price;


    public GPU(String brand, String name, float price) {
        this.brand = brand;
        this.name = name;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "GPU{" +
                "brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public static String processBrand(String rawBrand) {
        return rawBrand.split(" ", 2)[0];
    }

    public static float processPrice(String rawPrice) {
        return Float.parseFloat(rawPrice.replace(".", ""));
    }
}
