import java.util.ArrayList;
import java.util.Random;

public class U2 extends Rocket{


    public final double chanceOfExplosion = 4 * currentWeight/cargoLimit;
    public final double chanceOfCrash = 8 * currentWeight/cargoLimit;



    public U2(ArrayList<Item> items) {
        super(items, 36000, 58000, 120000000);
    }

    @Override
    public boolean launch() {
        Random rand = new Random();

        int rand_number = rand.nextInt(3);

        return !(rand_number >= chanceOfExplosion);
    }

    @Override
    public boolean land() {
        Random rand = new Random();

        int rand_number = rand.nextInt(6);

        return !(rand_number >= chanceOfCrash);
    }

    @Override
    public boolean canCarry(Item item) {
        return currentWeight + item.weight <= cargoLimit;
    }

    @Override
    public double carry(Item item) {
        currentWeight += item.weight;
        return currentWeight;
    }
}
