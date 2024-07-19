import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rl.multiarmbandit.*;

import java.io.IOException;

/**
 * TODO-4 : Implement the following test methods as documented
 */
public class TestBanditExperiment {

    /**
     * Creates an experiment with 3 different Action selection method.
     * Rolls the bandit 1000 times in each experiment
     * Runs the experiment 2000 times for each algorithm. Creates the comparison plot
     * See the chart file : "SampleAverage_AC_Comparison.jpeg" after running this test
     * @throws IOException
     */
    @Test
    void testSampleAvg_AC_Comparison() throws IOException {
        MultiArmBandit bandit =new GaussianMAB(10);

        ComparisonExperiment experiment = new ComparisonExperiment(bandit,2000,"SampleAverage_AC_Comparison.jpeg");
        experiment.addPlayer(new RLBanditPlayer("P1",new SampleAverageVE(0),new EpsilonGreedyAC(0.9)));
        experiment.addPlayer(new RLBanditPlayer("P2",new SampleAverageVE(0),new GreedyAC()));
        experiment.addPlayer(new RLBanditPlayer("P3",new SampleAverageVE(0),new RandomAC()));

        experiment.performExperiment(1000);
    }

    /**
     * TODO-4.1 : Implement the test function according to the documentation below
     *            Hint: check the previous test function, this will be very similar
     *  Creates an experiment with 3 different bandit player.
     *  1 with constant step-size value estimator with alpha=0.9 with EpsilonGreedyAC(epsilon=0.9)
     *  2 with constant step-size value estimator with alpha=0.5 with EpsilonGreedyAC(epsilon=0.9)
     *  3 with sample average value estimator with alpha=0.9 with EpsilonGreedyAC(epsilon=0.9) -----> ??
     *  Rolls the bandit 1000 times in each experiment
     *  Runs the experiment 2000 times for each algorithm. Creates the comparison plot
     *  See the chart file : "ConstantStepSize_SampleAvg_Comparison.jpeg" after running this test
     * @throws IOException
     */
    @Test
    void testConstantStepSize_SampleAvg_Comparison() throws IOException {
        /*your code here*/
        MultiArmBandit bandit =new GaussianMAB(10);

        ComparisonExperiment experiment = new ComparisonExperiment(bandit,2000,"ConstantStepSize_SampleAvg_Comparison.jpeg");
        experiment.addPlayer(new RLBanditPlayer("P1",new ConstantStepSizeVE(0.0, 0.9),new EpsilonGreedyAC(0.9)));
        experiment.addPlayer(new RLBanditPlayer("P2",new ConstantStepSizeVE(0.0, 0.5),new EpsilonGreedyAC(0.9)));
        experiment.addPlayer(new RLBanditPlayer("P3",new SampleAverageVE(0.0),new EpsilonGreedyAC(0.9)));

        experiment.performExperiment(1000);


        //Assertions.fail("Function is not implemented.."); // remove this line after implementing the function
    }

    /**
     * TODO-4.2 : Implement the test function according to the documentation below
     *            Hint: check the previous test function, this will be very similar
     *  Creates an experiment with 3 different bandit player.
     *  1 with constant step-size value estimator with alpha=0.9 with GreedyAC with Optimistic Initial Values:2.0
     *  2 with constant step-size value estimator with alpha=0.9 with GreedyAC with Optimistic Initial Values:200.0
     *  2 with constant step-size value estimator with alpha=0.9 with GreedyAC with Normal Initial Values:0.0
     *  Rolls the bandit 1000 times in each experiment
     *  Runs the experiment 2000 times for each algorithm. Creates the comparison plot
     *  See the chart file : "OptimisticInitialValues_Comparison.jpeg" after running this test
     * @throws IOException
     */
    @Test
    void testOptimisticInitialValues_Comparison() throws IOException {
        /*your code here*/
        MultiArmBandit bandit =new GaussianMAB(10);

        ComparisonExperiment experiment = new ComparisonExperiment(bandit,2000,"OptimisticInitialValues_Comparison.jpeg");
        experiment.addPlayer(new RLBanditPlayer("P1",new ConstantStepSizeVE(2.0, 0.9),new GreedyAC()));
        experiment.addPlayer(new RLBanditPlayer("P2",new ConstantStepSizeVE(200.0, 0.9),new GreedyAC()));
        experiment.addPlayer(new RLBanditPlayer("P3",new ConstantStepSizeVE(0.0, 0.9),new GreedyAC()));

        experiment.performExperiment(1000);
        //Assertions.fail("Function is not implemented.."); // remove this line after implementing the function
    }

    /**
     * TODO-4.3 : Implement the test function according to the documentation below
     *            Hint: check the previous test function, this will be very similar
     *  Creates an experiment with 2 different bandit player.
     *  1 with constant step-size value estimator with alpha=0.9 with GreedyAC with Normal Initial Values:0.0
     *  2 with upper confidence bounds value estimater(c=5) with GreedyAC with Normal Initial Values:0.0
     *  Rolls the bandit 10000 times in each experiment
     *  Runs the experiment 2000 times for each algorithm. Creates the comparison plot
     *  See the chart file : "UpperConfidenceBounds_Comparison.jpeg" after running this test
     * @throws IOException
     */
    @Test
    void testUpperConfidenceBoundsComparison() throws IOException {
        /*your code here*/
        MultiArmBandit bandit =new GaussianMAB(10);

        ComparisonExperiment experiment = new ComparisonExperiment(bandit,2000,"UpperConfidenceBounds_Comparison.jpeg");
        experiment.addPlayer(new RLBanditPlayer("P1",new ConstantStepSizeVE(0.0, 0.9),new GreedyAC()));
        //aşağıdakinde hataaa
        experiment.addPlayer(new RLBanditPlayer("P2",new UpperConfidenceBoundVE(0.0, 5),new GreedyAC()));

        experiment.performExperiment(10000);

        // Assertions.fail("Function is not implemented.."); // remove this line after implementing the function
    }

    /**
     * TODO-4.4 : Implement the test function according to the documentation below
     *            Hint: check the previous test function, this will be very similar
     *  Creates an experiment with 3 different bandit player.
     *  1 with constant step-size value estimator with alpha=0.9 with GreedyAC with Normal Initial Values:0.0
     *  2 with sample average value estimator with GreedyAC with Normal Initial Values:0.0
     *  3 with upper confidence bound value estimator(c=5.0) with GreedyAC with Normal Initial Values:0.0
     *  The bandit machines changes after each 100 rolls
     *  Rolls the bandit 5000 times in each experiment
     *  Runs the experiment 2000 times for each algorithm. Creates the comparison plot
     *  See the chart file : "NonStationary_Comparison.jpeg" after running this test
     * @throws IOException
     */
    @Test
    void testNonStationary_Comparison() throws IOException {
        /*your code here*/
        MultiArmBandit bandit =new GaussianMAB(10);

        ComparisonExperiment experiment = new ComparisonExperiment(bandit,2000,"NonStationary_Comparison.jpeg");
        experiment.addPlayer(new RLBanditPlayer("P1",new ConstantStepSizeVE(0.0, 0.9),new GreedyAC()));
        experiment.addPlayer(new RLBanditPlayer("P2",new SampleAverageVE(0.0),new GreedyAC()));
        experiment.addPlayer(new RLBanditPlayer("P3",new UpperConfidenceBoundVE(0.0, 5.0),new GreedyAC()));

        experiment.setChangeInterval(100);
        experiment.performExperiment(5000);




        //Assertions.fail("Function is not implemented.."); // remove this line after implementing the function
    }

}
