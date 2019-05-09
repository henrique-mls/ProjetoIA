package catchBox;

import agentSearch.Action;
import agentSearch.State;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CatchState extends State implements Cloneable {
    //TODO this class might require the definition of additional methods and/or attributes

    protected int[][] matrix;
    private int catchLine;
    private int catchColumn;
    private Cell goalCell;
    private int numBox;
    private int steps;


    public CatchState(int[][] matrix) {
        this.goalCell = null;
        this.catchLine = 0;
        this.catchColumn = 0;
        this.numBox = 0;
        this.steps = 0;

        this.matrix = matrix;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if (this.matrix[i][j] == Properties.CATCH) {
                    this.catchLine = i;
                    this.catchColumn = j;
                }
                if(this.matrix[i][j] == Properties.BOX){
                    this.numBox++;
                }

            }
        }
    }



    public void executeAction(Action action) {
        action.execute(this);
        // TODO
        fireUpdatedEnvironment();

        throw new UnsupportedOperationException("Not Implemented Yet"); // delete after implementing
    }

    public boolean canMoveUp() {
        if(catchLine == 0){
            return false;
        }

        if(this.matrix[catchLine - 1][catchColumn] == Properties.EMPTY || this.matrix[catchLine - 1][catchColumn] == Properties.BOX){
            return true;
        }

        return false;
    }

    public boolean canMoveRight() {
        if(catchColumn == getSize()){
            return false;
        }

        if(this.matrix[catchLine][catchColumn+1] == Properties.EMPTY || this.matrix[catchLine][catchColumn+1] == Properties.BOX){
            return true;
        }

        return false;
    }

    public boolean canMoveDown() {
        if(catchLine == getSize()){
            return false;
        }

        if(this.matrix[catchLine +1][catchColumn] == Properties.EMPTY || this.matrix[catchLine +1][catchColumn] == Properties.BOX){
            return true;
        }

        return false;
    }

    public boolean canMoveLeft() {
        if(catchColumn == 0){
            return false;
        }

        if(this.matrix[catchLine][catchColumn-1] == Properties.EMPTY || this.matrix[catchLine][catchColumn-1] == Properties.BOX){
            return true;
        }

        return false;
    }

    public void moveUp() {
        if(canMoveUp()){
            setCellCatch(catchLine+1, catchColumn);
        }

    }

    public void moveRight() {
        if(canMoveRight()){
            setCellCatch(catchLine, catchColumn+1);
        }

    }

    public void moveDown() {
        if(canMoveDown()){
            setCellCatch(catchLine+1, catchColumn);
        }

    }

    public void moveLeft() {
        if(canMoveLeft()){
            setCellCatch(catchLine, catchColumn-1);
        }

    }

    public int getNumBox() {
        return this.numBox;
    }

    public void setCellCatch(int line, int column) {
        matrix[this.catchLine][this.catchColumn] = Properties.EMPTY;
        matrix[line][column] = Properties.CATCH;
        this.steps++;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setGoal(int line, int column) {
        this.goalCell = new Cell(line, column);
    }

    public int getSteps() {
        return steps;
    }

    public int getSize() {
        return matrix.length;
    }

    public Color getCellColor(int line, int column) {
        switch (matrix[line][column]) {
            case Properties.BOX:
                return Properties.COLORBOX;
            case Properties.CATCH:
                return Properties.COLORCATCH;
            case Properties.DOOR:
                return Properties.COLORDOOR;
            case Properties.WALL:
                return Properties.COLORWALL;
            default:
                return Properties.COLOREMPTY;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CatchState)) {
            return false;
        }

        CatchState o = (CatchState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public CatchState clone() {
        return new CatchState(matrix);
    }

    //Listeners
    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }

    public Cell getGoalCell() {
        return goalCell;
    }

}
