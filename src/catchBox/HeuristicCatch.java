package catchBox;

import agentSearch.Heuristic;

public class HeuristicCatch extends Heuristic<CatchProblemSearch, CatchState> {

    @Override
    public double compute(CatchState state) {
        return state.computeDistance(state.getCatchLine(), state.getCatchColumn(), state.getGoalCell());
    }

    @Override
    public String toString() {
        return "Compute Distance";
    }
}