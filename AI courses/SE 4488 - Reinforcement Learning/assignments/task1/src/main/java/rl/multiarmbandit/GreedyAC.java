package rl.multiarmbandit;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *  A bandit action chooser that selects arms in Greedy fashion
 *  according to the given estimated values of each arm
 */
public class GreedyAC implements ActionChooser {

    /**
     * Greedy action selection .

     * @param values
     * @return
     */
    @Override
    public int getAction(double[] values) {
        int action =0; // Assign this properly

        /*double max = Arrays.stream(values).max().getAsDouble();
        int[] maxVals= IntStream.range(0,values.length).filter(x->values[x]==max).toArray();
        action = maxVals[new SecureRandom().nextInt(maxVals.length)];
         */

        for (int a = 1; a < values.length; a++) {
            if(values[a] > values[action])
                action = a;
        }

        return action;
    }

    @Override
    public String getName() {
        return "Greedy";
    }
}
