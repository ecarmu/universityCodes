public class CarRentalSystem {
    String cityName;
    String date;
    int dayAmount;

    public CarRentalSystem(String cityName, String date, int dayAmount) {
        this.cityName = cityName;
        this.date = date;
        this.dayAmount = dayAmount;
    }

    public void rentCar(){
        System.out.println("Car rented in " + cityName + " from " + date + " for " + dayAmount + "  days");
    }
}
