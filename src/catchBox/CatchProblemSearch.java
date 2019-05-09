package catchBox;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.ArrayList;
import java.util.List;

public class CatchProblemSearch<S extends CatchState> extends Problem<S> {
    //TODO this class might require the definition of additional methods and/or attributes
    ArrayList<Action> availableActions;

    public CatchProblemSearch(S initialCatchState, Cell goalPosition) {
        super(initialCatchState);

        //TODO
        this.availableActions = new ArrayList<>(4);

        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    @Override
    public List<S> executeActions(S state) {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public boolean isGoal(S state) {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }
}
