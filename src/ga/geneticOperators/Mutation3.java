package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

public class Mutation3<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation3(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //TODO doenst work
        //throw new UnsupportedOperationException("Not Implemented Yet");
        int[] parent = new int[ind.getNumGenes()];
        int l = parent.length;

        //repete o processo 5 vezes
        for (int i = 0; i < 5; i++) {

            //get 2 random integers between 0 and size of array
            int random1 = GeneticAlgorithm.random.nextInt(l);
            int random2 = GeneticAlgorithm.random.nextInt(l);


            //to make sure the r1 < r2
            while(random1 >= random2){
                random1 = GeneticAlgorithm.random.nextInt(l);
                random2 = GeneticAlgorithm.random.nextInt(l);
            }


            //this code inverts (i.e. reverses) elements between r1..r2 inclusive
            int mid = random1 + ((random2 + 1) - random1) / 2;
            int endCount = random2;

            for (int j = random1; j < mid; j++) {
                int aux = parent[j];
                parent[j] = parent[endCount];
                parent[endCount] = aux;
                endCount--;
            }
        }

        for(int i = 0; i < l; i ++){
            ind.setGene(i, parent[i]);
        }
    }

    @Override
    public String toString() {
        //TODO
        return "Inversion";
    }
}