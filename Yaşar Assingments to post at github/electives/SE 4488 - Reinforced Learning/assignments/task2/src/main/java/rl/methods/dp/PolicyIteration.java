package rl.methods.dp;


import rl.base.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * TODO-2 : Implement Policy Iteration
 *
 * The Policy Iteration algorithm covered in PART-IV
 *
 * See  PolicyIteration.png for the pseudocode
 */
public class PolicyIteration implements DynamicProgramming {

    final double GAMMA = 0.99;      // GAMMA of the algorithm. See the pseudocode
    final double EPSILON = 1e-7;    // EPSILON of the algorithm. See the pseudocode
    final double INITIAL_V = 0.0;   // Initial Values for the V(s) for all states

    HashMap<RLState,Double> v;      // V-Table
    HashMap<RLState,Double> vNext;  // V-Table to be calculated in the next iteration

    DeterministicPolicy pi;         // The Policy to be established according to V-Table

    // Renderers for visualizing  the outputs of the algorithm
    private RLValueRenderer valueRenderer;
    private RLPolicyRenderer policyRenderer;

    public PolicyIteration(DeterministicPolicy pi) {
        this.pi = pi;
    }

    public void setValueRenderer(RLValueRenderer valueRenderer) {
        this.valueRenderer = valueRenderer;
    }

    public void setPolicyRenderer(RLPolicyRenderer policyRenderer) {
        this.policyRenderer = policyRenderer;
    }

    /**
     * Initializes the algorithm variables (V-Tables)
     * @param environment
     */
    private void init(EnvironmentModel environment) {
        vNext = new HashMap<>();
        v = new HashMap<>();

        for (RLState state:environment.getStates())
            vNext.put(state,INITIAL_V);

        for (RLState state:environment.getStates())
            pi.setAction(state,environment.getActions(state).get(0));
    }

    /**
     * TODO-2.1 : Implement Policy Iteration algorithm. See pesudocode in PolicyIteration.png
     *       HINT: See the {@link ValueIteration} implementation to have an idea
     *
     * @param environment
     * @param iteration
     */
    public void perform(EnvironmentModel environment, int iteration) {
        init(environment);

        /*todo: your code here */
        boolean isStable;
        int i = 0;

        do {
            isStable = true;
            policyEvaluation(environment, iteration);
            isStable = policyImprovement(environment, isStable);
            i++;
        }while (!isStable && i < iteration);

        System.out.println(i);

        printResults(environment);
    }

    private void policyEvaluation(EnvironmentModel environment, int iteration) {

        double delta = 0;

        for (int n = 0; n < iteration; n++) {
            v.putAll(vNext);
            delta = 0;

            for (RLState state : environment.getStates()) {

                double newValue = 0;

                if(!environment.isTerminal(state)){
                    RLAction action = pi.getAction(state);
                    double val = 0;

                    for (RLTransition transition :
                            environment.getTransitions(state, action)) {
                        val += transition.prob() * (transition.reward() + GAMMA * v.get(transition.to()));
                    }

                    newValue = val;
                    pi.setAction(state, action);

                }
                vNext.put(state, newValue);
                double err = Math.abs(vNext.get(state)-v.get(state));
                if (err>delta)
                    delta=err;
            }

            if(delta < EPSILON)
                break;
        }
    }

    private boolean policyImprovement(EnvironmentModel environment, boolean isStable) {

        for (RLState state: environment.getStates()) {

            RLAction oldAction =  pi.getAction(state);

            List<Double> actionValues=new ArrayList<>();

            if(!environment.isTerminal(state)){
                for (RLAction action: environment.getActions(state)) {
                    double val =0;
                    for(RLTransition transition: environment.getTransitions(state,action))
                    {
                        val += transition.prob()*transition.reward()+ GAMMA*transition.prob()*v.get(transition.to());
                    }
                    actionValues.add(val);
                }

                int maxIndex= 0;

                for (int i = 1; i <actionValues.size() ; i++) {
                    if (actionValues.get(i)>actionValues.get(maxIndex))
                    {
                        maxIndex=i;
                    }
                }

                pi.setAction(state,environment.getActions(state).get(maxIndex));
                RLAction newAction = pi.getAction(state);

                if(!oldAction.equals(newAction)){
                    isStable = false;
                }

            }

        }

        return isStable;


    }



    private void printResults(EnvironmentModel model) {
        if (valueRenderer != null)
            valueRenderer.renderValues(model,v);

        if (policyRenderer != null)
            policyRenderer.renderPolicy(model,pi);
    }


}
