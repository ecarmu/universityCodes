import java.util.ArrayList;
import java.util.Random;

public class U1 extends Rocket{



    public double chanceOfExplosion = 5 * currentWeight/cargoLimit;
    public double chanceOfCrash = currentWeight/cargoLimit;

    public U1(ArrayList<Item> items) {
        super(items, 20000, 36000, 100000000);
    }

    @Override
    public boolean launch() {
        Random rand = new Random();

        int rand_number = rand.nextInt(4);

        return !(rand_number >= chanceOfExplosion);
    }

    @Override
    public boolean land() {
        Random rand = new Random();

        int rand_number = rand.nextInt();

        return !(rand_number >= chanceOfCrash);
    }

    @Override
    public boolean canCarry(Item item) {
        return currentWeight + item.weight <= cargoLimit;
    }





    @Override
    public double carry(Item item) {
        items.add(item);
        currentWeight += item.weight;
        return currentWeight;
    }


}
