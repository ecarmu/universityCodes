public class VehicleClient {
    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        Vehicle car = carFactory.createVehicle();
        Engine carEngine = carFactory.createEngine();

        car.startEngine();
        carEngine.start();

        VehicleFactory motorcycleFactory = new MotorcycleFactory();
        Vehicle motorcycle = motorcycleFactory.createVehicle();
        Engine motorcycleEngine = motorcycleFactory.createEngine();

        motorcycle.startEngine();
        motorcycleEngine.start();
    }
}