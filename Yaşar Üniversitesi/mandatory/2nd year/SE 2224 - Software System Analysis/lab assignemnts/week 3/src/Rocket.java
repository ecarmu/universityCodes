import java.util.ArrayList;

public abstract class Rocket implements SpaceShip {

    public double currentWeight;
    public double cargoLimit;
    public double rocketCost;

    public Rocket(ArrayList<Item> items, double currentWeight, double cargoLimit, double rocketCost) {
        this.items = items;
        this.currentWeight = currentWeight;
        this.cargoLimit = cargoLimit;
        this.rocketCost = rocketCost;
    }



    ArrayList<Item> items = new ArrayList<>();

    public Rocket(ArrayList<Item> items) {
        this.items = items ;
    }

    @Override
    public abstract boolean launch();

    @Override
    public abstract boolean land();

    @Override
    public String toString() {
        int val = (int) rocketCost;
        return "Rocket" +
                "currentWeight=" + currentWeight +
                "\ncargoLimit=" + cargoLimit +
                "\nrocketCost=" + val;
    }

    @Override
    public abstract boolean canCarry(Item item);

    @Override
    public abstract double carry(Item item);
}
