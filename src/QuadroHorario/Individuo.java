package QuadroHorario;

import java.util.Random;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vi
 */
public class Individuo implements Comparable<Individuo>{
        private int[][][][] genes;// [SÉRIE][DIASEMANA][N_AULA][PROFESSOR]
        private final boolean 
                SHOWALL = false, 
                SHOWFITNESS = false;
        private int fitness;
        public static int GENES; // [SÉRIE]*[DIASEMANA]*[N_AULA]*[PROFESSOR]
        
        public Individuo(Individuo pai, Individuo mae, int corte) {
            
            calcFitnessTotal();
        }
        public String showAll(){
         return  "";
        }
        
        private void calcFitnessTotal(){
            int f = 0;
            fitness = f;
        }
        
        public Individuo(Individuo pai, Individuo mae, int corte, int mutacao, Random r) {
            this(pai,mae,corte);
            if(mutacao<=0){
                do{
                    int n = r.nextInt(genes.length);
                       // prodA = r.nextInt(Factory.PRODUCAO),
                       // prodB = r.nextInt(Factory.PRODUCAO);
                    //while(this.genes[n].getProducao()[prodA] <= 3 || 
                    //        prodA == prodB){
                    //    prodA = r.nextInt(Factory.PRODUCAO);
                    //    prodB = r.nextInt(Factory.PRODUCAO);
                    //}
                    //this.genes[n].getProducao()[prodA] = this.genes[n].getProducao()[prodA]-1;
                    //this.genes[n].getProducao()[prodB] = this.genes[n].getProducao()[prodB]+1;
                }while((r.nextInt(3)+1)%3==0);
            }
            calcFitnessTotal();
        }
        
        
        public Individuo() {
            //genes = new Factory[]{Factory.newFactory('a'), Factory.newFactory('b'),Factory.newFactory('c')};
            calcFitnessTotal();
        }
        
        @Override
        public String toString(){
            return "";
        }

        public int getFitness(){
            return fitness;
        }

    @Override
    public int compareTo(Individuo o) {
        int compareFitest = o.getFitness();
            return compareFitest-this.getFitness();
    }
}
    

