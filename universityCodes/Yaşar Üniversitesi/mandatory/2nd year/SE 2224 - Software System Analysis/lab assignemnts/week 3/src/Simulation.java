import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    public ArrayList<Item> items;

    public Simulation() {
        items = new ArrayList<Item>();
    }

    public void loadItems(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("=");

                String name = parts[0];
                int weight = Integer.parseInt(parts[1]);

                Item item = new Item(name, weight);
                items.add(item);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    public ArrayList<Rocket> loadU1(ArrayList<Item> items){


        U1 rocketU1 = new U1( new ArrayList<Item>() );

        ArrayList<Rocket> U1List = new ArrayList<>();
        U1List.add(rocketU1);
        int whichRocket = 0;

        for (int i = 0; i < items.size(); i++) {

            if(U1List.get(whichRocket).canCarry(items.get(i))){
                U1List.get(whichRocket).carry(items.get(i));
            }
            else{
                System.out.println("Rocket " + whichRocket + " is fully loaded\n");


                    System.out.println("Rocket " + whichRocket + ": ");
                    for (int k = 0; k < U1List.get(whichRocket).items.size(); k++) {
                        System.out.println(U1List.get(whichRocket).items.get(k).name + " - " + U1List.get(whichRocket).items.get(k).weight);
                    }
                    //System.out.println("\n");



                U1List.add( new U1( new ArrayList<Item>() ) );
                whichRocket++;
            }

            if(i == items.size() - 1){
                System.out.println("Rocket " + whichRocket + ": ");
                for (int k = 0; k < U1List.get(whichRocket).items.size(); k++) {
                    System.out.println(U1List.get(whichRocket).items.get(k).name + " - " + U1List.get(whichRocket).items.get(k).weight);
                }
                //System.out.println("\n");
            }
        }

        return U1List;
    }

    public ArrayList<Rocket> loadU2(ArrayList<Item> items) {
        U2 rocketU2 = new U2( new ArrayList<Item>() );

        ArrayList<Rocket> U2List = new ArrayList<>();
        U2List.add(rocketU2);
        int whichRocket = 0;

        for (int i = 0; i < items.size(); i++) {
            if(U2List.get(whichRocket).canCarry(items.get(i))){
                U2List.get(whichRocket).carry(items.get(i));

            }
            else{
                System.out.println("Rocket " + whichRocket + " is fully loaded");
                U2List.add( new U2( new ArrayList<Item>() ) );
                whichRocket++;
            }
        }

        return U2List;
    }

    public long runSimulation(ArrayList<Rocket> rockets){
        long budget = 0;
        System.out.println("Rocket cost: " +  rockets.get(0).rocketCost);

        for (int i = 0; i < rockets.size(); ) {
            budget += rockets.get(i).rocketCost;

            if(rockets.get(i).land() && rockets.get(i).launch()){
                System.out.println(rockets.get(i));
                System.out.println("Rocket " + i + ": " + "Success!");
                i++;
            }
            else{
                System.out.println(rockets.get(i));
                System.out.println("Rocket " + i + ": " + "Fail!");
            }
            System.out.println("\n" + "Total budget: " + budget);

        }

        return budget;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();

        System.out.println(" !!!  Phase 1  is  starting   !!!");
        simulation.loadItems("C:\\Users\\ardah\\IdeaProjects\\SE-2224_week 3\\src\\phase-1.txt");


        ArrayList<Rocket> U1RocketsList = simulation.loadU1(simulation.items);
        ArrayList<Rocket> U2RocketsList = simulation.loadU2(simulation.items);

        simulation.runSimulation(U1RocketsList);
        simulation.runSimulation(U2RocketsList);


        System.out.println("  !!!  Phase 2  is   starting   !!!");
        simulation.loadItems("C:\\Users\\ardah\\IdeaProjects\\SE-2224_week 3\\src\\phase-2.txt");


        ArrayList<Rocket> U1RocketsList2 = simulation.loadU1(simulation.items);
        ArrayList<Rocket> U2RocketsList2 = simulation.loadU2(simulation.items);

        simulation.runSimulation(U1RocketsList2);
        simulation.runSimulation(U2RocketsList2);


    }


}
