package Factory;


import static Factory.Factory.A;
import static Factory.Factory.B;
import static Factory.Factory.C;
import static Factory.Factory.MATERIAPRIMADISPONIVEL;
import static Factory.Factory.PRODUCAO;
import java.util.Arrays;
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
        private Factory[] genes;
        private final boolean 
                SHOWALL = false, 
                SHOWFITNESS = false;
        private int fitness;
        public static final int GENES = 3;
        
        public Individuo(Individuo pai, Individuo mae, int corte) {
            Factory a = Factory.newFactory('a',pai.genes[Factory.A].getProducao());
            Factory b = corte == 0 ?  Factory.newFactory('b',pai.genes[Factory.B].getProducao()) :
                    Factory.newFactory('b',mae.genes[Factory.B].getProducao());
            Factory c = Factory.newFactory('c',mae.genes[Factory.C].getProducao());
            this.genes = new Factory[]{a,b,c};
            calcFitnessTotal();
        }
        public String showAll(){
         return  String.format("Total - %d | %s | %s | %s", fitness,
                    this.genes[Factory.A].toString(), 
                    this.genes[Factory.B].toString(), 
                    this.genes[Factory.C].toString());
        }
        
        private void calcFitnessTotal(){
            int f = Math.round(genes[A].getFitness() +genes[B].getFitness() + genes[C].getFitness());
            // checa penalidades
            for(int i=0;i<PRODUCAO;i++){
                if(genes[A].getProducao()[i] + 
                        genes[B].getProducao()[i]+
                        genes[C].getProducao()[i] > MATERIAPRIMADISPONIVEL[i])
                    f+= -500;
            }
            fitness = f;
        }
        
        public Individuo(Individuo pai, Individuo mae, int corte, int mutacao, Random r) {
            this(pai,mae,corte);
            if(mutacao<=0){
                do{
                    int n = r.nextInt(genes.length), 
                        prodA = r.nextInt(Factory.PRODUCAO),
                        prodB = r.nextInt(Factory.PRODUCAO);
                    while(this.genes[n].getProducao()[prodA] <= 3 || 
                            prodA == prodB){
                        prodA = r.nextInt(Factory.PRODUCAO);
                        prodB = r.nextInt(Factory.PRODUCAO);
                    }
                    this.genes[n].getProducao()[prodA] = this.genes[n].getProducao()[prodA]-1;
                    this.genes[n].getProducao()[prodB] = this.genes[n].getProducao()[prodB]+1;
                }while((r.nextInt(3)+1)%3==0);
            }
            calcFitnessTotal();
        }
        
        
        public Individuo() {
            genes = new Factory[]{Factory.newFactory('a'), Factory.newFactory('b'),Factory.newFactory('c')};
            calcFitnessTotal();
        }
        
        @Override
        public String toString(){
            if(SHOWALL)
                return  String.format("Total - %d | %s | %s | %s", getFitness(),
                    this.genes[Factory.A].toString(), this.genes[Factory.B].toString(), this.genes[Factory.C].toString());
            else if(SHOWFITNESS)
                return String.format("%d", getFitness());
            else return "A:"+Arrays.toString(genes[Factory.A].getProducao())+"| B:"+
                    Arrays.toString(genes[Factory.B].getProducao())+"| C"+
                    Arrays.toString(genes[Factory.C].getProducao());
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
    

