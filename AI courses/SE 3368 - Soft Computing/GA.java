package tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GA {

    private int popSize, nbOfIter;  // popSize is N
    private double cr, mr;
    private ArrayList<Genome> population;
    private Genome best;

    Random rnd;

    public GA(int popSize, int nbOfIter, double cr, double mr) {
        this.popSize = popSize;
        this.nbOfIter = nbOfIter;
        this.cr = cr;
        this.mr = mr;
        population = new ArrayList<>();
    }

    public void run() {
        rnd = new Random();
        // initialize the population
        for (int i = 0; i < popSize; i++) {
            population.add(new Genome());
        }

        // Update the best solution
        best = new Genome(population.get(0));
        for (Genome g : population) {
            if (g.getFitness() < best.getFitness()) {
                best = new Genome(g);
            }
        }
        System.out.print("0; ");
        best.printFitness();
        // Iteration
        for (int i = 0; i < nbOfIter; i++) {
            Operators.constructRouletteWheel(population);
            ArrayList<Genome> offspring = new ArrayList<>();

            // Generating Offspring
            for (int j = 0; j < popSize / 2; j++) {
                // Mating Selection
                Genome p1 = population.get(Operators.selectByRW());
                Genome p2 = population.get(Operators.selectByRW());
                ArrayList<Genome> child;

                // Crossover probability
                double probability = rnd.nextDouble();
                if (probability <= cr) {
                    child = Operators.PMX(p1, p2); // 2 children as a list.
                } else {
                    child = new ArrayList<>();
                    child.add(p1);
                    child.add(p2);
                }

                // Mutation probability
                probability = rnd.nextDouble();
                if (probability <= mr) {
                    Operators.insertMutation(child.get(0));
                    Operators.insertMutation(child.get(1));
                }

                // Update the best & Extend the Offspring
                for (int k = 0; k < child.size(); k++) {
                    offspring.add(child.get(k));
                    if (child.get(k).getFitness() < best.getFitness()) {
                        best = new Genome(child.get(k));

                    }
                }

            }

            System.out.print((i + 1) + "; ");
            best.printFitness();

            // Combine parents and offspring
            population.addAll(offspring);
            // Survival selection
            population = survivalSelection(popSize / 10);
        }
    }

    private ArrayList<Genome> survivalSelection(int k) {
        ArrayList<Genome> newPop = new ArrayList<>();

        // Elitism
        Collections.sort(population, new FitnessComperator());
        for (int i = 0; i < k; i++) {
            newPop.add(population.get(0));
            population.remove(0);
        }
        for (int i = 0; i < popSize - k; i++) {
            newPop.add(population.get(Operators.selectByTournament(population, 2)));
        }
        return newPop;
    }

    public Genome getBest() {
        return best;
    }
}
