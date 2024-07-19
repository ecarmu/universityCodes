

public class Perceptron {
    
    private double[] weights, input;
    private double t, y, eta;

    public Perceptron(double[] weights, double[] input, double t, double eta) {
        this.weights = weights;
        this.input = input;
        this.t = t;
        this.eta = eta;
    }
    
    public void setWeights(double[] weights){
        this.weights = weights;
    }
    
    private double calculateSum(){
        double sum = 0.0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i]*input[i]; // Y
        }
        return sum;
    }
    
    public void calculateOutput(){
        y = f(calculateSum());
    } 
    
    private double f(double sum){
        return  1 / (1 + Math.exp(-sum));
    }
    
    private double df(double sum){
        return f(sum)*(1-f(sum));
    }
    
    public void updateWeights(byte select){
        switch (select){
            case 1: hebbianLearning(); break;
            case 2: perceptronLearning(); break;
            case 3: deltaLearning(); break;
        }
        
    }
    
    private void hebbianLearning(){
        // You will be coding
    }

    private void perceptronLearning() {
        // You will be coding
    }

    private void deltaLearning() {
        // You will be coding
    }
    
    public double[] getWeights(){
        return weights;
    }
    
}
