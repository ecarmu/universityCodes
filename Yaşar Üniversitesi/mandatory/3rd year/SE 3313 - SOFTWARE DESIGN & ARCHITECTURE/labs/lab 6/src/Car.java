public class Car implements Vehicle{
    @Override
    public void startEngine() {
        System.out.println("Car motor başladııııı");
    }

    @Override
    public void stopEngine() {
        System.out.println("Car motor stop edildi");
    }
}
