package rl.multiarmbandit;


import java.util.Arrays;

/**
 * TODO-3 : Implement Sample Average with UCB for value estimation
 *          See the UCB description in your textbook.
 *          Hint: It will be similar to SampleAverageVE here
 *               the UCB term for each actions should be added
 */
public class UpperConfidenceBoundVE implements ValueEstimator {

    double initialEstimation;   // Initial estimations of values for each action
    double[] estimations;       // Current estimation of values for each action

    int[] updateTimes;

    long timesteps;             // Total number of action-reward feeds.
                                // It is used in confidence bound calculations

    double c;   // The C constant of UCB equation.

    double[] confidenceBounds;

    public double getConfidenceBound(int act) {
        return c*Math.sqrt(Math.log(timesteps)/updateTimes[act]);
    }

    public void updateConfidenceBounds(){
        for (int act = 0; act < estimations.length; act++) {

            confidenceBounds[act] = estimations[act] + getConfidenceBound(act);
        }
    }

    public UpperConfidenceBoundVE(double initialEstimation, double c) {
        this.initialEstimation = initialEstimation;
        this.c = c;
    }

    @Override
    public double[] getValues() {

        return confidenceBounds;
    }

    /**
     * TODO-3.1 : A new reward (reward) is observed for the given arm (a).
     *            Update the current estimation accordingly.
     *            Note that the updateTime of the given arm should be incremented
     * @param a
     * @param reward
     */
    @Override
    public void feed(int a, double reward) {
        /* your code here*/

        // estimations[a] += (1.0/updateTimes[a]) c*Math.sqrt(Math.log(timesteps)/updateTimes[a]);

        estimations[a] += (1.0/updateTimes[a])*(reward - estimations[a]);
        updateTimes[a]++;

        timesteps++;

        updateConfidenceBounds();
    }

    /**
     * TODO-3.2 Initializes the object properly.
     *  Hint: See SampleAverageVE::init()
     *
     * @param armCount
     */
    @Override
    public void init(int armCount) {
        /* your code here*/
        estimations = new double[armCount];
        updateTimes = new int[armCount];
        confidenceBounds = new double[armCount];

        Arrays.fill(estimations, initialEstimation);
        Arrays.fill(updateTimes,1);

        updateConfidenceBounds();
    }

    @Override
    public String getName() {
        return "UCB-"+c;
    }
}
