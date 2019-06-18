package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.Individual;
import ga.IntVectorIndividual;
import ga.Problem;

public class Mutation2<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation2(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //TODO
        int[] parent = new int[ind.getNumGenes()];
        int l = parent.length;

        for(int i = 0; i < l; i ++){
            parent[i] = ind.getGene(i);
        }

        int random1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int random2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());

        while(random1 == random2){
            random2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }

        int aux = parent[random1];

        parent[random1] = parent[random2];
        parent[random2] = aux;

        for(int i = 0; i < l; i ++){
            ind.setGene(i, parent[i]);
        }



        //throw new UnsupportedOperationException("Not Implemented Yet");
    }

    @Override
    public String toString() {
        //TODO
        return "Swap";
    }
}