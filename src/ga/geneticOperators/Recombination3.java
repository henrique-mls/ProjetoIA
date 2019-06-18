package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.ArrayList;

public class Recombination3<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    public Recombination3(double probability) {
        super(probability);
    }

    private int[] parent1;
    private int[] parent2;
    private int[] offspring1;
    private int[] offspring2;
    private int cut1;
    private int cut2;

    private ArrayList<Integer> outerSegmentBuildArray;

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        outerSegmentBuildArray = new ArrayList<Integer>();
        this.parent1 = new int[ind1.getNumGenes()];
        this.parent2 = new int[ind2.getNumGenes()];

        for(int index = 0; index < parent1.length; index ++){
            this.parent1[index] = ind1.getGene(index);
            this.parent2[index] = ind2.getGene(index);
        }

        offspring1 = new int[ind1.getNumGenes()];
        offspring2 = new int[ind2.getNumGenes()];
        cut1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        cut2 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        //se o 2 for maior que o 1 para não ser igual
        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        crossOver(offspring1, parent1, parent2);
        crossOver(offspring2, parent2, parent1);
    }

    private void crossOver(int[] offspring, int[] parentX, int[] parentY) {
        int tempIndex = 0;
        int index = cut2 + 1;
        // if index - cutPoint2 + 1  == parentX.length
        // add all parentX elements directly to  outerSegmentBuildArray ArrayList.
        if(index == parentX.length) { // e.g. (1 2 3 | 4 5 6 7 8| )
            for(int x = 0; x < parentX.length; x++){
                outerSegmentBuildArray.add(parentX[x]);
            }
        }

        // Else block here concatenates segments in the following order 3rd then (1 and 2)
        // outerSegmentBuildArray
        else {
            for(index = cut2 + 1; index < parentX.length; index++){
                outerSegmentBuildArray.add(tempIndex, parentX[index]);
                tempIndex++;
            }
            for(index = 0; index <= cut2; index++){
                outerSegmentBuildArray.add(tempIndex, parentX[index]);
                tempIndex++;
            }

        }


        for(int indexInSegment = cut1; indexInSegment <=cut2; indexInSegment++){
            // for ArrayList temp remove elements that appear in parentY mid segments
            removeSpecifiedElement(parentY[indexInSegment]);
        }

        for(int x = cut1; x <= cut2; x++){
            // copy mid segment from parent designated as Y,
            // into offspring to be created.
            offspring[x] = parentY[x];
        }


        // Belows section copies remaining elements in temp into offspring
        // starting from 3rd segment of offspring.
        tempIndex = 0;
        for(int y = cut2 + 1; y < offspring.length; y++){
            if(y == offspring.length){ break; }
            offspring[y] = outerSegmentBuildArray.get(tempIndex);
            tempIndex++;
        }

        // after end of offspring reach, copy elements from temp haven't been copied
        // into offspring from 1st segment.
        for(int z = 0; z < cut1; z++){
            if(z == offspring.length){ break; }
            offspring[z] = outerSegmentBuildArray.get(tempIndex);
            tempIndex++;
        }
    }

    private void removeSpecifiedElement(int elementToRemove){
        for(int index = 0; index< outerSegmentBuildArray.size(); index++){
            if(outerSegmentBuildArray.get(index) == elementToRemove){
                outerSegmentBuildArray.remove(index);
                break;
            }
        }
    }

    @Override
    public String toString(){
        //TODO
        return "OX";
    }    
}