import java.util.ArrayList;
import java.util.Random;

public class Operations {
    public static Random random = new Random();

    public static ArrayList<Genotype> constructInitialPopulation(int populationSize, int genotypeSize){
        ArrayList<Genotype> initialPopulation = new ArrayList<>();

        int counter = 0;
        System.out.println("--- Initial Population ---");
        while(counter < populationSize){
            ArrayList<Integer> bitsArrayOfGenotype = new ArrayList<>();
            System.out.print("[ ");
            for (int i = 0; i < genotypeSize; i++) {
                int gene = random.nextInt() % 2;
                if(gene < 0)
                    gene *= -1;

                bitsArrayOfGenotype.add(gene);

                if(i==0)
                    System.out.print(gene);
                else
                     System.out.print(", " + gene);
            }
            System.out.print(" ] ");
            Genotype genotype = new Genotype(bitsArrayOfGenotype);
            System.out.println("-> Fitness Value: " + genotype.fitnessValue);
            initialPopulation.add(genotype);
            counter++;
        }
        return initialPopulation;
    }

    public static Genotype selectionByTournament(ArrayList<Genotype> population, int k){
        ArrayList<Genotype> contestants = new ArrayList<>();

        // System.out.println("\n\nSelected contestants :");
        for(int i = 0; i<k; i++){
            int rand = random.nextInt(population.size());
            // System.out.println(i + ") " + population.get(rand) + " (from " + rand + "th index)");
            contestants.add(population.get(rand));
        }

        Genotype bestGenotypeContestant = contestants.get(0);
        int indexOfBest = 0;
        for (int i = 1; i < contestants.size(); i++) {
            if(contestants.get(i).fitnessValue > bestGenotypeContestant.fitnessValue){
                bestGenotypeContestant = contestants.get(i);
                indexOfBest = i;
            }

        }

        // System.out.println("\nWinner contestant: " + bestGenotypeContestant.toString() + " -> " + indexOfBest + "th from contestant list");

        return bestGenotypeContestant;
    }

    //bunu for loop içinde, tekrar tekrar çağırıp çocuk yaptırr
    public static ArrayList<Genotype> onePointCrossover(Genotype parent1, Genotype parent2){
        int bitsArraySize = parent1.bitsArray.size();
        int pointOfCrossover = random.nextInt(bitsArraySize - 2) + 1;
        // System.out.println("\n\nPoint of crossover -> " + pointOfCrossover);

        Genotype offspring1 = new Genotype();
        for (int i = 0; i < bitsArraySize; i++) {
            if(i < pointOfCrossover)
                offspring1.bitsArray.add(parent1.bitsArray.get(i));
            else
                offspring1.bitsArray.add(parent2.bitsArray.get(i));
        }
        offspring1.fitnessValue = offspring1.fitnessValueCalculator();

        // System.out.println("1st offspring: " + offspring1);

        Genotype offspring2 = new Genotype();
        for (int i = 0; i < bitsArraySize; i++) {
            if(i < pointOfCrossover)
                offspring2.bitsArray.add(parent2.bitsArray.get(i));
            else
                offspring2.bitsArray.add(parent1.bitsArray.get(i));
        }

        offspring2.fitnessValue = offspring2.fitnessValueCalculator();

        // System.out.println("2nd offspring: " + offspring2);

        ArrayList<Genotype> offspringList = new ArrayList<>();
        offspringList.add(offspring1);
        offspringList.add(offspring2);

        return offspringList;
    }

    public static void mutation(Genotype offspring){
        int bitsArraySize = offspring.bitsArray.size();

        // System.out.println("Offspring\nbefore mutation: " + offspring);

        for (int i = 0; i < bitsArraySize/3; i++) {
            int index = random.nextInt(bitsArraySize);
            offspring.bitsArray.set(index, (offspring.bitsArray.get(index) + 1) % 2);
        }
        offspring.fitnessValue = offspring.fitnessValueCalculator();

        // System.out.println("After  mutation: " + offspring);
    }

    public static void updateSurvivalSelection(ArrayList<Genotype> population){
        ArrayList<Genotype> new_population = new ArrayList<>();

        new_population = (ArrayList<Genotype>) elitisim(population).clone();
        // System.out.println("new pop after elit: " + new_population);

        // System.out.println("pop size before tournament: " + population.size()*9/19);


        GeneticAlgorithm GA = new GeneticAlgorithm();
        GA.population = population;


        int populationInitialSize = population.size()*9/19;
        for (int i = 0; i < populationInitialSize; i++) {
            new_population.add(selectionByTournament(population, population.size()/3));
            population.remove(new_population.get(new_population.size()-1));
            GA = new GeneticAlgorithm();
            GA.population = population;

        }
        // System.out.println("new pop after tournamen: " + new_population.toString());

        population.clear();
        population.addAll(new_population);

        // System.out.println("new of the original pop afcl" + population);

    }

    private static ArrayList<Genotype> elitisim(ArrayList<Genotype> population) {
        ArrayList<Genotype> first_part_of_new_population = new ArrayList<>();

        // System.out.println("pop size: " + population.size()/20);

        for (int i = 0; i < population.size()/20; i++) {
            int maxFitness = population.get(i).fitnessValue;
            int maxFitnessIndex = i;

            for (int j = i+1; j < population.size(); j++) {
                if(population.get(j).fitnessValue > maxFitness){
                    maxFitness = population.get(j).fitnessValue;
                    maxFitnessIndex = j;
                }
            }

            first_part_of_new_population.add(population.get(maxFitnessIndex));
            // System.out.println("first part of new: " + first_part_of_new_population);


            //burası %99 hatalı -> arraylist'te objeleri swap edicem. Sonra düzeltirim hataları
            // System.out.println("Selected best genotype " + i + ": " + population.get(maxFitnessIndex) + " (indexOfMax : " + maxFitnessIndex + " )");
            Genotype tmp = population.get(i);
            population.set(i, population.get(maxFitnessIndex));
            population.set(maxFitnessIndex, tmp);

            // System.out.println("0th of the pop new swapped: " + population.get(0));

        }

        for (int i = 0; i < population.size()/20; i++) {
            population.remove(i);
        }

        GeneticAlgorithm GA = new GeneticAlgorithm();
        GA.population = population;



        return first_part_of_new_population;
    }
}
