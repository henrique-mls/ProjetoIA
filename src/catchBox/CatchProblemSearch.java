package catchBox;

import agentSearch.Action;
import agentSearch.Problem;

import java.util.ArrayList;
import java.util.List;

public class CatchProblemSearch<S extends CatchState> extends Problem<S> {
    //TODO this class might require the definition of additional methods and/or attributes
    ArrayList<Action> availableActions;
    private Cell goalCell;

    public CatchProblemSearch(S initialCatchState, Cell goalPosition) {
        super(initialCatchState);

        //TODO
        this.goalCell = goalPosition;
        this.availableActions = new ArrayList<>(4);
        availableActions.add(new ActionUp());
        availableActions.add(new ActionRight());
        availableActions.add(new ActionDown());
        availableActions.add(new ActionLeft());


    }

    @Override
    public List<S> executeActions(S state) {
        //TODO
        //declarar uma lista de estados vazia
        ArrayList<S> successors = new ArrayList<>(4);
        //para cada accao disponivel
        for (Action availableAction : this.availableActions) {
            if (availableAction.isValid(state)) {
                //se o estado sucessor resultante da acao for valido
                S successor = (S) state.clone();
                //obter o estado sucessor
                availableAction.execute(successor);
                //acrescentar o estado sucessor a lista
                successors.add(successor);
            }
        }

        //devolver lista de estados sucessores
        return successors;
    }

    public boolean isGoal(S state) {
        //TODO
        return goalCell.getLine() == state.getCatchLine() && goalCell.getColumn() == state.getCatchColumn();
    }
}
