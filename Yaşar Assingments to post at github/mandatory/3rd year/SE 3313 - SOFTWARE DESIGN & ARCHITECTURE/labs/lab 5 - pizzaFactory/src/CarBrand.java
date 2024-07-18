public abstract class CarBrand {

    Mercedes mercedes;
    Renault renault;
    Toyota toyota;

    /*
    public CarBrand(Mercedes mercedes) {
        this.mercedes = mercedes;
    }

    public CarBrand(Renault renault) {
        this.renault = renault;
    }

    public CarBrand(Toyota toyota) {
        this.toyota = toyota;
    }

     */

    public Car orderCar(String type){
        Car car;

        car = createCar(type);
        System.out.println("Car: " + car.displayCarSpecs());

        return car;
    }

    public abstract Car createCar(String type);
}
