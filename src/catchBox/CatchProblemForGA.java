package catchBox;

import ga.Problem;
import java.util.LinkedList;

public class CatchProblemForGA implements Problem<CatchIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes
    private LinkedList<Cell> cellsBoxes;
    private LinkedList<Pair> pairs;
    private Cell cellCatch;
    private Cell door;

    public CatchProblemForGA(LinkedList<Cell> cellsBoxes, LinkedList<Pair> pairs, Cell cellCatch, Cell door) {
        this.cellsBoxes = cellsBoxes;
        this.pairs = pairs;
        this.cellCatch = cellCatch;
        this.door = door;
    }

    @Override
    public CatchIndividual getNewIndividual() {
        //TODO
        //devolve um novo individuo Catch passando-lhe o numero de caixas para apanhar I guess
        return new CatchIndividual(this, this.cellsBoxes.size());
        //throw new UnsupportedOperationException("Not Implemented Yet");
    }

    @Override
    public String toString() {
        //TODO
        //throw new UnsupportedOperationException("Not Implemented Yet");
        StringBuilder sb = new StringBuilder();
        for (Pair pair : pairs) {
            sb.append(pair.toString());
        }
        return sb.toString();
    }

    public LinkedList<Cell> getCellsBoxes() {
        return cellsBoxes;
    }

    public LinkedList<Pair> getPairs() {
        return pairs;
    }

    public Cell getCellCatch() {
        return cellCatch;
    }

    public Cell getDoor() {
        return door;
    }
}
