import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {
    ArrayList<Genotype> population = new ArrayList<>();
    double crossoverRatio = 7.0/10.0;
    double mutationRatio = 3.0/10.0;
    Genotype bestGeneOfPopulation;

    Random random = new Random();

    public void printPopulation(){
        //System.out.println("\nPopulation after adding offsprings before survival selection: ");
        for (Genotype g: population) {
            System.out.println(population.indexOf(g) + "th) " + g);
        }
    }

    public void setBestGeneOfPopulation(){
        Genotype bestGene = new Genotype(population.get(0).bitsArray);
        for (Genotype gene: population) {
            if(gene.fitnessValue > bestGene.fitnessValue)
                bestGene = new Genotype(gene.bitsArray);
        }

        bestGeneOfPopulation = new Genotype(bestGene.bitsArray);
    }

    public void runGeneration(){
        int initialSizeOfPopulation = population.size();

        for (int i = 0; i < initialSizeOfPopulation/2; i++) {
            Genotype selectedParent1 = new Genotype( Operations.selectionByTournament(population, initialSizeOfPopulation/3).bitsArray );
            Genotype selectedParent2 = new Genotype( Operations.selectionByTournament(population, initialSizeOfPopulation/3).bitsArray );

            ArrayList<Genotype> offsprings = new ArrayList<>();


            if (random.nextDouble(1) <= crossoverRatio) {
                offsprings = Operations.onePointCrossover(selectedParent1, selectedParent2);
            }
            else{
                /*System.out.println("p1: " + selectedParent1 + " p2: " + selectedParent2);
                System.out.println("failed parents -> " + "p1: " + selectedParent1 + " p2"+ selectedParent2);*/
                offsprings.add(selectedParent1);
                offsprings.add(selectedParent2);
            }

            if(random.nextDouble(1) <= mutationRatio){
                //System.out.println("\n\nOffsprings had mutated!!!\n");
                Operations.mutation(offsprings.get(0));
                Operations.mutation(offsprings.get(1));
            }

            population.add(offsprings.get(0));
            population.add(offsprings.get(1));
        }

    }

    public void runHomework(int numberOfGenerations){
        for (int i = 0; i < numberOfGenerations; i++) {
            System.out.println("\n\n#Iteration "  + i + ": \n");
            runGA();
        }
    }

    public void runGA(){
        runGeneration();


        printPopulation();

        Operations.updateSurvivalSelection(population);

        printPopulation();

        setBestGeneOfPopulation();
        System.out.println("Best gene of the population: " + bestGeneOfPopulation);
    }

    public static void main(String[] args) {
        GeneticAlgorithm GA = new GeneticAlgorithm();
        GA.population = Operations.constructInitialPopulation(1000, 100);

        GA.runHomework(10000);


    }

}
