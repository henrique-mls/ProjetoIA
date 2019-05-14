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


        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                /*if (this.matrix[i][j] == Properties.CATCH) {
                    this.catchLine = i;
                    this.catchColumn = j;
                }*/
                if (this.matrix[i][j] == Properties.BOX) {
                    this.numBox++;
                }

            }
        }
    }

    public CatchState(int[][] matrix, int catchLine, int catchColumn, Cell goalCell, int numBox, int steps) {
        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        this.catchLine = catchLine;
        this.catchColumn = catchColumn;
        this.goalCell = goalCell;
        this.numBox = numBox;
        this.steps = steps;
    }

    public void executeAction(Action action) {
        action.execute(this);
        // TODO
        fireUpdatedEnvironment();

        //throw new UnsupportedOperationException("Not Implemented Yet"); // delete after implementing
    }

    public boolean canMoveUp() {

        if (catchLine == 0 || this.matrix[catchLine - 1][catchColumn] == Properties.WALL) {
            return false;
        }

        return true;
    }

    public boolean canMoveRight() {

        if (catchColumn == getSize()-1 || this.matrix[catchLine][catchColumn + 1] == Properties.WALL) {
            return false;
        }

        return true;
    }

    public boolean canMoveDown() {

        if (catchLine == getSize()-1 || this.matrix[catchLine + 1][catchColumn] == Properties.WALL) {
            return false;
        }

        return true;
    }

    public boolean canMoveLeft() {

        if (catchColumn == 0 || this.matrix[catchLine][catchColumn - 1] == Properties.WALL) {
            return false;
        }

        return true;
    }

    public void moveUp() {
        matrix[this.catchLine][this.catchColumn] = Properties.EMPTY;
        matrix[this.catchLine -1][this.catchColumn] = Properties.CATCH;
        setCellCatch(this.catchLine-1, this.catchColumn);
        steps++;
    }

    public void moveRight() {
        matrix[this.catchLine][this.catchColumn] = Properties.EMPTY;
        matrix[this.catchLine][this.catchColumn+1] = Properties.CATCH;
        setCellCatch(this.catchLine, this.catchColumn+1);
        steps++;
    }

    public void moveDown() {
        matrix[this.catchLine][this.catchColumn] = Properties.EMPTY;
        matrix[this.catchLine+1][this.catchColumn] = Properties.CATCH;
        setCellCatch(this.catchLine+1, this.catchColumn);
        steps++;
    }

    public void moveLeft() {
        //TODO decrementar numbox
        matrix[this.catchLine][this.catchColumn] = Properties.EMPTY;
        matrix[this.catchLine][this.catchColumn-1] = Properties.CATCH;
        setCellCatch(this.catchLine, this.catchColumn-1);
        steps++;
    }

    public int getNumBox() {
        return this.numBox;
    }

    public void setCellCatch(int line, int column) {
        this.catchLine = line;
        this.catchColumn = column;
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

        return new CatchState(matrix, catchLine, catchColumn, goalCell, numBox, steps);
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

    public int computeDistance(int line, int column, Cell goalCell) {
        int dx = Math.abs(line - goalCell.getLine());
        int dy = Math.abs(column - goalCell.getColumn());

        return 1 * (dx + dy);
    }

    public int getCatchLine() {
        return catchLine;
    }

    public int getCatchColumn() {
        return catchColumn;
    }
}
