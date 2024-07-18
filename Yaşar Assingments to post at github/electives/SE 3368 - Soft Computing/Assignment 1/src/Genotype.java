import java.util.ArrayList;

public class Genotype {
    public int fitnessValue;
    public ArrayList<Integer> bitsArray;

    public Genotype(){
        this.bitsArray = new ArrayList<>();
    }

    public Genotype(ArrayList<Integer> bitsArray) {
        this.bitsArray = (ArrayList<Integer>) bitsArray.clone();

        fitnessValue = fitnessValueCalculator();
    }


    public Genotype(int fitnessValue, ArrayList<Integer> bitsArray) {
        this.fitnessValue = fitnessValue;
        this.bitsArray = (ArrayList<Integer>) bitsArray.clone();
    }

    @Override
    public String toString() {
        return bitsArray + " -> " + "Fitness Value: " + fitnessValue;
    }

    public int fitnessValueCalculator(){
        fitnessValue = 0;
        int max = fitnessValue;

        for (int i = 0; i < bitsArray.size(); i++) {
            if(bitsArray.get(i) == 1){
                fitnessValue++;
            }
            if(bitsArray.get(i) == 0 || i== bitsArray.size() -1){
                if(fitnessValue > max)
                    max = fitnessValue;

                    fitnessValue = 0;
            }
        }

        return max;
    }
}
