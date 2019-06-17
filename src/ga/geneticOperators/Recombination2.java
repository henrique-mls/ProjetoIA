package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    public Recombination2(double probability) {
        super(probability);
    }

    private int[] child1;
    private int[] child2;
    private int[] offspring1;
    private int[] offspring2;

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        this.child1 = new int[ind1.getNumGenes()];
        this.child2 = new int[ind2.getNumGenes()];

        for(int index = 0; index < child1.length; index ++){
            this.child1[index] = ind1.getGene(index);
            this.child1[index] = ind2.getGene(index);
        }

        this.offspring1 = new int[child1.length];
        this.offspring2 = new int[child2.length];

        for(int index = 0; index < offspring1.length; index++){
            offspring1[index] = -1;
            offspring2[index] = -1;
        }

        crossOver(offspring1, child1, child2);
        crossOver(offspring2, child2, child1);

        /*for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, offspring1[i]);
            ind2.setGene(i, offspring2[i]);
        }*/

        //throw new UnsupportedOperationException("Not Implemented Yet");
    }

    public void crossOver(int[] offspring, int[] childX, int[] childY){
        int index = 0;
        while(!element_already_inOffspring(offspring, childY[index])){
            offspring[index] = childX[index];
            int position = getPosition_ofSecondParentElement_infirstParent
                    (childX, childY[index]);
            offspring[position] = childY[index];
            index = position;
        }

        for(int i = 0; i < offspring.length; i++){
            if(offspring[i] == -1){
                offspring[i] = childY[i];
            }
        }
    }

    private boolean element_already_inOffspring(int[] offspring, int element){
        for(int index = 0; index < offspring.length; index++){
            if(offspring[index] == element){
                return true;
            }
        }
        return false;
    }

    private int getPosition_ofSecondParentElement_infirstParent(int [] firstChild, int elementToSearch){
        int pos = 0;
        for(int i = 0; i < child1.length; i++){
            if(firstChild[i] == elementToSearch){
                pos = i;
                break;
            }
        }
        return pos;
    }

    @Override
    public String toString(){
        //TODO
        //throw new UnsupportedOperationException("Not Implemented Yet");
        return "CX";
    }    
}