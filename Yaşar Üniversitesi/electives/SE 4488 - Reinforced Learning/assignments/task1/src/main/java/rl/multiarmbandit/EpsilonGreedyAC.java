package rl.multiarmbandit;

import java.security.SecureRandom;
import java.util.Random;

/**
 * TODO-1 : Implement a bandit action chooser that selects arms in EpsilonGreedy fashion
 *          according to the given estimated values of each arm
 */
public class EpsilonGreedyAC implements ActionChooser {

    Random rng = new SecureRandom();// for random number generation if needed

    double epsilon;

    public EpsilonGreedyAC(double epsilon) {
        this.epsilon = epsilon;
    }

    /**
     * TODO-1.1 : Implement epsilon greedy selection in this function.
     *            Note that you can implement other helper functions to be used in this function
     * @param values
     * @return
     */
    @Override
    public int getAction(double[] values) {
        int action =0; // Assign this properly

        /* your code here*/
        double randomValue = rng.nextDouble();

        if(randomValue >= 0 && randomValue > epsilon){
            for (int i = 0; i < values.length; i++) {
                if(values[i] > values[action])
                    action = i;
            }

            return action;
        }
        else{
            return rng.nextInt(values.length);
        }

    }

    @Override
    public String getName() {
        return "Epsilon-Greedy";
    }


}
