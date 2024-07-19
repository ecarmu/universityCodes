public abstract class Car {



    String modelName;
    double weight;
    double accelaration;
    double topSpeed;
    double price;

    int deliveryTime;
    String originOfCountry;


    public String displayCarSpecs() {
        return "Car{" +
                "modelName='" + modelName + '\'' +
                ", weight=" + weight + "kg " +
                ", accelaration=" + accelaration +
                ", topSpeed=" + topSpeed +
                ", price=" + price +
                '}' + "deliveryTime= " + deliveryTime + " weeks "
                + "origin of country= " + originOfCountry;
    }


}
