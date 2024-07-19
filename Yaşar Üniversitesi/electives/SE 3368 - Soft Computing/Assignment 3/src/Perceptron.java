import java.util.Arrays;

public class Perceptron {


    // eta -> yunan alfabesinde "n" gibi olan -> learning rate (0.5)
    // t -> target
    // y ?= f(x) (activation function outputu)

    private double[][] input;                // current tabloda bulunduğumuz row
    private double[] t, weights;
    private double y, eta;

    // constructor'da, "weights_table" ve "input_table" vercem ya input olarak
    // ya "input_table"ın ilk row'u {0,0,0,0,0} gibi olcak ve 1. index'ten başlıcak
    // ya da weights_table'in size'ı, input_table'dan 1 fazla olacak

    //                                        Hebian için:
    //               {0,0,0,0,0}          {}, {10,2,5,1,4,6}, ...    {, 0.9,...}          0.5
    public Perceptron(double[] weights,     double[][] input,      double[] t,        double eta) {
        this.weights = weights;
        this.input = input;
        this.t = t;
        this.eta = eta;
    }

    public void setWeights(double[] weights){
        this.weights = weights;
    }



    // weighted sum
    private double calculateSum(int rowNo){
        double sum = 0.0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i]*input[rowNo][i];
        }
        return sum;
    }

    // activation function's (sigmoid) output
    public void calculateOutput(int rowNo){
        y = f(calculateSum(rowNo));
    }

    // sigmoid function
    private double f(double sum){
        return  1 / (1 + Math.exp(-sum));
    }

    // derivative of sigmoid function ( sigmoid(x) * (1 - sigmoid(x) )
    private double df(double sum){
        return f(sum)*(1-f(sum));
    }


    public void updateWeights(int select){
        switch (select){
            case 1: hebbianLearning(); break;
            case 2: perceptronLearning(); break;
            case 3: deltaLearning(); break;
        }

    }

    private void hebbianLearning(){
        double weightAdjustment[] = new double[input[0].length];

        for (int i = 0; i < input.length; i++) {

            for (int j = 0; j < weightAdjustment.length; j++) {
                weightAdjustment[j] = input[i][j] * t[i];
            }

            for (int k = 0; k < weights.length; k++) {
                weights[k] = weights[k] + weightAdjustment[k];
            }

        }
    }

    private void perceptronLearning() {
        double weightAdjustment[] = new double[input[0].length];

        for (int i = 0; i < input.length; i++) {

            if(i==1)
                i=1;

            calculateOutput(i);

            for (int j = 0; j < weightAdjustment.length; j++) {
                weightAdjustment[j] = eta * (t[i] - y) * input[i][j];
            }

            for (int k = 0; k < weights.length; k++) {
                weights[k] = weights[k] + weightAdjustment[k];
            }

        }
    }

    private void deltaLearning() {
        double weightAdjustment[] = new double[input[0].length];

        for (int i = 0; i < input.length; i++) {

            calculateOutput(i);

            for (int j = 0; j < weightAdjustment.length; j++) {
                weightAdjustment[j] = eta * (t[i] - y) * df(y) * input[i][j];
            }

            for (int k = 0; k < weights.length; k++) {
                weights[k] = weights[k] + weightAdjustment[k];
            }

        }
    }


    public double[] getWeights(){
        return weights;
    }



    public static void main(String[] args) {

        // Values at the graph

        double[][] input_array = {
                {10, 2, 5, 1, 4, 6},
                {1, 0, 9, 4, 10, 8},
                {8, 5, 2, 6, 0, 4},
                {7, 6, 3, 2, 1, 10},
                {3, 4, 7, 0, 5, 3},
                {4, 1, 6, 8, 6, 0},
                {2, 3, 8, 5, 8, 9},
                {1, 3, 3, 9, 0, 5},
                {10, 7, 5, 3, 1, 6},
                {9, 8, 1, 10, 2, 0}
        };


        double[] weights = {0.2, 0.2, 0.2, 0.2, 0.2, 0.2};


        double[] t_array = {0.9, 1.0, 0.6, 1.0, 1.0, 0.7, 1.0, 0.6, 0.9, 0.1};

        final double eta = 0.5;


        System.out.println("\n\n\n                         final adjusted weights from :                   " + "\n\n\n");


        // Hebbian Learning
        Perceptron perceptron1 = new Perceptron(weights, input_array, t_array, eta);
        perceptron1.updateWeights(1);
        System.out.println(" --  HEBIAN LEARNING  -- " + "\n" + Arrays.toString(perceptron1.getWeights()) + "\n\n");


        // Hebbian Learning
        Perceptron perceptron2 = new Perceptron(weights, input_array, t_array, eta);
        perceptron2.updateWeights(2);
        System.out.println(" --  PERCEPTRON LEARNING  -- " + "\n" + Arrays.toString(perceptron2.getWeights()) + "\n\n");


        // Delta Learning
        Perceptron perceptron3 = new Perceptron(weights, input_array, t_array, eta);
        perceptron3.updateWeights(3);
        System.out.println(" --  DELTA LEARNING  -- " + "\n" + Arrays.toString(perceptron3.getWeights()) + "\n\n");



    }


}

