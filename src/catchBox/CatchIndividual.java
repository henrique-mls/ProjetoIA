package catchBox;

import ga.IntVectorIndividual;

import java.util.LinkedList;


public class CatchIndividual extends IntVectorIndividual<CatchProblemForGA, CatchIndividual> {

    public CatchIndividual(CatchProblemForGA problem, int size) {
        super(problem, size);
    }

    public CatchIndividual(CatchIndividual original) {
        super(original);
    }

    @Override
    public double computeFitness() {
        //TODO
        //throw new UnsupportedOperationException("Not Implemented Yet");
        Cell cellCatch = problem.getCellCatch();
        Cell door = problem.getDoor();
        LinkedList<Pair> pairs = problem.getPairs();
        LinkedList<Cell> cellsBoxes = problem.getCellsBoxes();
        this.fitness = 0;
        double custo = 0;

        for (int i = 0; i < genome.length; i++) {
            Cell cell = cellsBoxes.get(genome[i]);
            for (Pair pair : pairs) {
                if(pair.getCell1() == cellCatch && pair.getCell2() == cell){
                    custo += pair.getValue();
                    cellCatch = cell;
                }
                //caso o par esteja ao contrário
                if(pair.getCell1() == cell && pair.getCell2() == cellCatch){
                    custo += pair.getValue();
                    cellCatch = cell;
                }
            }
        }
        //par da porta
        for (Pair pair : pairs) {
            if(pair.getCell1() == cellCatch && pair.getCell2() == door){
                custo += pair.getValue();
                cellCatch = door;
            }
        }

        fitness = custo;
        return fitness;
    }

    public int[] getGenome() {
        return genome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        sb.append("\npath: ");
        for (int i = 0; i <genome.length ; i++) {
            sb.append(genome[i]).append(" ");
        }
        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(CatchIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public CatchIndividual clone() {
        return new CatchIndividual(this);

    }
}
