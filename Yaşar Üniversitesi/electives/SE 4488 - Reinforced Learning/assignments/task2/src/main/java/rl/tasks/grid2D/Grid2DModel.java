package rl.tasks.grid2D;

import rl.SimpleTransition;
import rl.base.EnvironmentModel;
import rl.base.RLAction;
import rl.base.RLState;
import rl.base.RLTransition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO-1 : Implement missing methods as documented
 *  Read the README document in this folder before
 *  Environment Model of Grid2DWorld
 */
public class Grid2DModel implements EnvironmentModel {

    int rowCount;   // # of Rows
    int colCount;   // # of Columns
    Grid2DState[][] states; // States

    public Grid2DModel(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;
        buildStates();
    }

    /**
     * Creates states as empty cells according to specified row and column count
     */
    private void buildStates() {
        states = new Grid2DState[rowCount][colCount];

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                states[r][c]= new Grid2DState(r,c);
            }
        }
    }

    /**
     * Makes a given state terminal state with a given reward
     * @param r
     * @param c
     * @param reward
     */
    public void setTerminal(int r, int c, double reward)
    {
        states[r][c].setTerminal(true,reward);
    }

    /**
     * Makes a given state obstacle (unreachable)
     * @param r
     * @param c
     */
    public void setObstacle(int r,int c)
    {
        states[r][c].setObstacle(true);
    }

    /**
     * Returns all states as a list
     * @return
     */
    @Override
    public List<RLState> getStates() {
        return Arrays.stream(states).flatMap(Arrays::stream).collect(Collectors.toList());
    }

    /**
     * Returns all possible transitions when the agent takes a given action in a given state
     * @param state
     * @param action
     * @return
     */
    @Override
    public List<RLTransition> getTransitions(RLState state, RLAction action) {
        GridDirection direction = (GridDirection) action;
        switch (direction)
        {
            case Up :  return upTransition(state);
            case Down: return downTransition(state);
            case Left: return leftTransition(state);
            case Right: return rightTransition(state);
        };
        return null;
    }

    /**
     * Returns all possible transitions when the agent takes right-action in a given state
     * @param state
     * @return
     */
    private List<RLTransition> rightTransition(RLState state) {
        Grid2DState grid2DState = (Grid2DState) state;
        int row =grid2DState.row;
        int col = grid2DState.col;
        List<RLTransition> transitions = new ArrayList<>();
        // right
        if (col>=colCount-1 || getState(row,col+1).isObstacle())
            transitions.add(new SimpleTransition(state, 0.8, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row,col+1), 0.8,getState(row,col+1).stateReward));

        // down
        if (row>=rowCount-1 || getState(row+1,col).isObstacle())
            transitions.add(new SimpleTransition(state, 0.1, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row+1,col), 0.1,getState(row+1,col).stateReward));

        // up
        if (row<=0 || getState(row-1,col).isObstacle())
            transitions.add(new SimpleTransition(state, 0.1, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row-1,col), 0.1,getState(row-1,col).stateReward));


        return transitions;
    }

    /**
     * TODO-1.1 : Implement this function. See the function rightTransition(..)
     * Returns all possible transitions when the agent takes left-action in a given state
     * @param state
     * @return
     */
    private List<RLTransition> leftTransition(RLState state) {

        /* todo: your code here */

        Grid2DState grid2DState = (Grid2DState) state;
        int row =grid2DState.row;
        int col = grid2DState.col;
        List<RLTransition> transitions = new ArrayList<>();
        if (col<= 0 || getState(row,col-1).isObstacle())
            transitions.add(new SimpleTransition(state, 0.8, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row,col-1), 0.8,getState(row,col-1).stateReward));

        if (row>=rowCount-1 || getState(row+1,col).isObstacle())
            transitions.add(new SimpleTransition(state, 0.1, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row+1,col), 0.1,getState(row+1,col).stateReward));

        if (row<=0 || getState(row-1,col).isObstacle())
            transitions.add(new SimpleTransition(state, 0.1, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row-1,col), 0.1,getState(row-1,col).stateReward));


        return transitions;
    }

    /**
     * TODO-1.2 : Implement this function. See the function rightTransition(..)
     * Returns all possible transitions when the agent takes down-action in a given state
     * @param state
     * @return
     */
    private List<RLTransition> downTransition(RLState state) {

        /* todo: your code here */

        Grid2DState grid2DState = (Grid2DState) state;
        int row =grid2DState.row;
        int col = grid2DState.col;
        List<RLTransition> transitions = new ArrayList<>();
        if (col<= 0 || getState(row,col-1).isObstacle())
            transitions.add(new SimpleTransition(state, 0.1, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row,col-1), 0.1,getState(row,col-1).stateReward));

        if (row>=rowCount-1 || getState(row+1,col).isObstacle())
            transitions.add(new SimpleTransition(state, 0.8, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row+1,col), 0.8,getState(row+1,col).stateReward));

        if (col>=colCount-1 || getState(row,col+1).isObstacle())
            transitions.add(new SimpleTransition(state, 0.1, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row,col+1), 0.1,getState(row,col+1).stateReward));


        return transitions;

    }

    /**
     * TODO-1.3 : Implement this function. See the function rightTransition(..)
     * Returns all possible transitions when the agent takes up-action in a given state
     * @param state
     * @return
     */
    private List<RLTransition> upTransition(RLState state) {
        /* todo: your code here */

        Grid2DState grid2DState = (Grid2DState) state;
        int row =grid2DState.row;
        int col = grid2DState.col;
        List<RLTransition> transitions = new ArrayList<>();
        if (col<= 0 || getState(row,col-1).isObstacle())
            transitions.add(new SimpleTransition(state, 0.1, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row,col-1), 0.1,getState(row,col-1).stateReward));

        if (row<=0 || getState(row-1,col).isObstacle())
            transitions.add(new SimpleTransition(state, 0.8, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row-1,col), 0.8,getState(row-1,col).stateReward));

        if (col>=colCount-1 || getState(row,col+1).isObstacle())
            transitions.add(new SimpleTransition(state, 0.1, grid2DState.stateReward));
        else transitions.add(new SimpleTransition(getState(row,col+1), 0.1,getState(row,col+1).stateReward));


        return transitions;


    }

    /**
     * TODO-1.4 : Implement This function
     * Returns all possible actions the agent can take in a given state
     * See the README file to understand what are the actions in a particular state
     * @param state
     * @return
     */
    @Override
    public List<RLAction> getActions(RLState state) {
        List<RLAction> actions = new ArrayList<>();
        Grid2DState grid2DState = (Grid2DState) state;

        int row =grid2DState.row;
        int col = grid2DState.col;

        if (!(col<= 0 || getState(row,col-1).isObstacle()))
            actions.add(GridDirection.Left);

        if (!(row<=0 || getState(row-1,col).isObstacle()))
            actions.add(GridDirection.Up);

        if (!(col>=colCount-1 || getState(row,col+1).isObstacle()))
            actions.add(GridDirection.Right);

        if (!(row>=rowCount-1 || getState(row+1,col).isObstacle()))
            actions.add(GridDirection.Down);


        return actions;
    }

    /**
     * Returns if the given state is terminal
     * @param state
     * @return
     */
    @Override
    public boolean isTerminal(RLState state) {
        Grid2DState grid2DState = (Grid2DState) state;
        return grid2DState.isTerminal();
    }



    /**
     * Returns the state for a given row and column of the grid
     * @param r
     * @param c
     * @return
     */
    public Grid2DState getState(int r, int c) {
        return states[r][c];
    }
}
