package catchBox;

import agentSearch.Action;
import agentSearch.State;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CatchState extends State implements Cloneable {
    //TODO this class might require the definition of additional methods and/or attributes

    protected int[][] matrix;

    public CatchState(int[][] matrix) {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public void executeAction(Action action) {
        action.execute(this);
        // TODO
        fireUpdatedEnvironment();

        throw new UnsupportedOperationException("Not Implemented Yet"); // delete after implementing
    }

    public boolean canMoveUp() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public boolean canMoveRight() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public boolean canMoveDown() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public boolean canMoveLeft() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public void moveUp() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public void moveRight() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public void moveDown() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public void moveLeft() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public int getNumBox() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public void setCellCatch(int line, int column) {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setGoal(int line, int column) {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public int getSteps() {
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
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
        //TODO
        throw new UnsupportedOperationException("Not Implemented Yet");
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

}
