/*
 * Em função de caracteristicas especificas do processo produtivo não é economicamente
 * inviavel a produção de quantidades menores que 2 un de um determinado produto em qualquer uma das fábricas.
 * Tambem, em função de compromissos já assumidos com clientes é necessário produzir pelo menos 3 unidades
 * de cada um dos produtos.
 */
package Factory;

import java.util.Random;


/**
 *
 * @author vi
 */
public class Factory {
    public final static int A=0,B=1,C=2;
    public final static int PRODUCAO=8;
    public final static int 
        FIGO=0,PESSEGO=1,ABACAXI=2, AMEIXA=3, GOIABA=4,BANANA=5,MACA=6,PERA=7;
    public final static float[] CUSTOMATERIAPRIMA =
       {3.8f ,    2.7f,     3.1f,     3.1f,      3.7f,    4f,    4.1f,  4f};
    public final static int[] MATERIAPRIMADISPONIVEL =
       {20,        53,       52,       33,        25,     86,     63,    18};
    public final static float[] LUCROPRODUTO =
       {20.75f , 25.25f,    22.5f,     21f,       25f,   21.75f,  22f,   24f};
    public float[] CUSTO = null;
    public static final int penalidade = 1000;
    private int[] producao;
    private int maxProducao;
    private char tipo;
    public Factory() {
        
    }
    public Factory(int maxProducao) {
        this.maxProducao = maxProducao;
    }
    public Factory(int maxProducao, float[] CUSTO, char tipo) {
        this(maxProducao);
        this.producao =  new int[8];
        this.CUSTO = CUSTO;
        this.tipo = tipo;
        Random r = new Random();
        for(int i=0;i<maxProducao;i++){
            producao[r.nextInt(8)] +=1;
        }
    }
    public Factory(int maxProducao, float[] CUSTO, char tipo, int[] prod) {
        this(maxProducao);
        this.CUSTO = CUSTO;
        this.tipo = tipo;
        Random r = new Random();
        this.producao =  new int[]{prod[0], prod[1], prod[2], prod[3], prod[4], prod[5], prod[6], prod[7]};
    }
    public static Factory newFactory(char tipo) {
        switch(tipo){
            case 'a':
                return new Factory(80, new float[]{3.5f , 3.4f, 3.9f, 4, 3.5f, 4.1f, 3.9f, 3.0f},'a');
            case 'b':
                return new Factory(70,new float[]{3.5f , 2.8f, 3.8f, 2.5f, 3.7f, 3.9f, 4f, 3.5f},'b');
            case 'c':
                return new Factory(50, new float[]{2.5f , 2.7f, 3.2f, 2.3f, 2.7f, 2.65f, 2.7f, 2.45f},'c');
        }
        return null;
    }
    public static Factory newFactory(char tipo,int[] prod) {
        switch(tipo){
            case 'a':
                return new Factory(80, new float[]{3.5f , 3.4f, 3.9f, 4, 3.5f, 4.1f, 3.9f, 3.0f},'a',prod);
            case 'b':
                return new Factory(70,new float[]{3.5f , 2.8f, 3.8f, 2.5f, 3.7f, 3.9f, 4f, 3.5f},'b',prod);
            case 'c':
                return new Factory(50, new float[]{2.5f , 2.7f, 3.2f, 2.3f, 2.7f, 2.65f, 2.7f, 2.45f},'c',prod);
        }
        return null;
    }
    
    public int getFitness(){
        float custo,venda;
        int somaProducao = 0, total = 0;
        for(int i=0;i<PRODUCAO;i++){
            somaProducao+=producao[i];
            custo = producao[i]*(CUSTOMATERIAPRIMA[i]+CUSTO[i]);
            venda = producao[i]*LUCROPRODUTO[i];
            total += Math.round(venda-custo);
            if(producao[i]>MATERIAPRIMADISPONIVEL[i] || producao[i]<3) total += -500;
        }
        if(somaProducao>maxProducao) total += -500;
        return total;
    };
    
    @Override
    public String toString(){
        return tipo+" - Figo:"+producao[FIGO]+",Pessego:"+producao[PESSEGO]+",Abacaxi:"+producao[ABACAXI]+
                ",Ameixa:"+producao[AMEIXA]+",Goiaba:"+producao[GOIABA]+",Banana:"+producao[BANANA]+",Maçã:"+
                producao[MACA]+",Pera:"+producao[PERA]+",Fitnes:"+getFitness();
    }
    
    public int[] getProducao(){
        return producao;
    }
    
}


