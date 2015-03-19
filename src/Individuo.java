
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
        public static final int GENES = 5,  GENMIN = 1, GENMAX = 10;
        private final int[] genes;
        
        
        public Individuo(Individuo pai, Individuo mae, int corte) {
            this.genes = new int[GENES];
            System.arraycopy(pai.genes, 0, this.genes, 0, corte);
            System.arraycopy(mae.genes, corte, this.genes, corte, GENES - corte);
        }
        public Individuo(Individuo pai, Individuo mae, int corte, int mutacao, Random r) {
            this(pai,mae,corte);
            if(mutacao<=0){
                int val = this.genes[-1*mutacao%GENES];
                do{
                    this.genes[-1*mutacao%GENES] = r.nextInt(1+GENMAX-GENMIN) + GENMIN;
                }while(val==this.genes[-1*mutacao%GENES]);
            }
        }
        
        public Individuo(Random r) {
            this.genes = new int[GENES];
            for(int i=0;i<GENES;i++){
                this.genes[i] = r.nextInt(1+GENMAX-GENMIN) + GENMIN;
            }
        }
        @Override
        public String toString(){
            String r = "";
            for(int x=0;x<GENES;x++){
                r += this.genes[x]+" ";
            }
            return r;
        }
        public int[] getGenes() {
            return genes;
        }
        public int getFitness() {
            int r = 0;
            for(int i=0;i<GENES;i++){
                r += this.genes[i];
            }
            return r;
        }

        @Override
        public int compareTo(Individuo o) {
             int compareFitest = o.getFitness();
            return compareFitest-this.getFitness();
        }
}
    

