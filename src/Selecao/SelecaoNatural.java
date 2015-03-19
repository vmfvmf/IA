package Selecao;

import java.util.ArrayList;
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
 * @param <Item>
 */

public class SelecaoNatural{
    static final int POPULACAO = 20, GERACOES = 40, MUTACAOP100 = 100, PRESSAO_ROLETA = 2;
    private Individuo[] individuosSobreviventes, filhos;
    Random r;
    
    public SelecaoNatural(){
        this.individuosSobreviventes = new Individuo[POPULACAO];
        this.filhos = new Individuo[POPULACAO];
        r = new Random();
    }
    
    public void inicializaPopulacao(){
        for(int i = 0; i < POPULACAO; i++){
            individuosSobreviventes[i] = new Individuo(r);
            System.out.print(individuosSobreviventes[i].toString()+" ");
            System.out.println();
        }
    }
    
    public Individuo roleta1(int pressao){
        int at = 0, n;
        for(Individuo indi: individuosSobreviventes){
            at += indi.getFitness();
        }
        n = r.nextInt(at+1)/pressao;
        for(Individuo indi: individuosSobreviventes){
            if(indi.getFitness() >= n) return indi;
        }
        return null;
    }
    public Individuo roleta2(){
        int at = 0, n,soma=0;
        for(Individuo indi: individuosSobreviventes){
            at += indi.getFitness();
        }
        n = r.nextInt(at+1);
        for(Individuo indi: individuosSobreviventes){
            soma += indi.getFitness();
            if(soma >= n) return indi;
        }
        return null;
    }
    public void geraFilhosRoleta1(){
        System.out.println("GERANDO FILHOS - ROLETA 1");
        for(int i = 0; i < POPULACAO; i+=2){
            Individuo pai = roleta1(PRESSAO_ROLETA),
                    mae = roleta1(PRESSAO_ROLETA);
            while(pai == null)pai = roleta1(PRESSAO_ROLETA);
            while(mae == null)mae = roleta1(PRESSAO_ROLETA);
            int corte = r.nextInt(Individuo.GENES-1)+1;
            
            String rr = "Pai:"+pai.toString()+
                    "Mae:"+mae.toString()+
                    "Corte:"+corte;
            filhos[i] = new Individuo(pai,mae,corte);
            filhos[i+1] = new Individuo(mae,pai,corte);
            String r2 = "Pai:"+mae.toString()+pai.toString()+"Corte:"+corte;
            
            System.out.println(rr+"Filho:"+filhos[i].toString()+" ");
            System.out.println(r2+"Filho:"+filhos[i+1].toString()+" ");
        }
    }
    public void geraFilhosRoleta2(){
        System.out.println("GERANDO FILHOS - ROLETA 2");
        for(int i = 0; i < POPULACAO; i+=2){
            Individuo pai = roleta1(PRESSAO_ROLETA),
                    mae = roleta1(PRESSAO_ROLETA);
            while(pai == null)pai = roleta1(PRESSAO_ROLETA);
            while(mae == null)mae = roleta1(PRESSAO_ROLETA);
            int corte = r.nextInt(Individuo.GENES-1)+1;
            
            String rr = "Pai:"+pai.toString()+
                    "Mae:"+mae.toString()+
                    "Corte:"+corte;
            filhos[i] = new Individuo(pai,mae,corte);
            filhos[i+1] = new Individuo(mae,pai,corte);
            String r2 = "Pai:"+mae.toString()+pai.toString()+"Corte:"+corte;
            
            System.out.println(rr+"Filho:"+filhos[i].toString()+" ");
            System.out.println(r2+"Filho:"+filhos[i+1].toString()+" ");
        }
    }
    public void geraFilhos(){
        System.out.println("GERANDO FILHOS");
        for(int i = 0; i < POPULACAO; i+=2){
            int corte = r.nextInt(Individuo.GENES-1)+1;
            int pai = r.nextInt(POPULACAO);
            int mae = r.nextInt(POPULACAO);
            int mutacaof1 = r.nextInt(101);
            int mutacaof2 = r.nextInt(101);            
            String rr = "Pai:"+individuosSobreviventes[pai].toString()+
                    "Mae:"+individuosSobreviventes[mae].toString()+
                    "Corte:"+corte;
            filhos[i] = new Individuo(individuosSobreviventes[pai],
                individuosSobreviventes[mae],corte,mutacaof1-MUTACAOP100,r);
            String r2 = "Pai:"+individuosSobreviventes[mae].toString()+
                    "Mae:"+individuosSobreviventes[pai].toString()+
                    "Corte:"+corte;
            filhos[i+1] = new Individuo(individuosSobreviventes[mae],
                individuosSobreviventes[pai],corte,mutacaof2-MUTACAOP100,r);
            
            System.out.println(rr+"Filho:"+filhos[i].toString()+" ");
            System.out.println(r2+"Filho:"+filhos[i+1].toString()+" ");
        }
    }    
    void evolucao() {
        ArrayList<Individuo> arr = new ArrayList<>();
        arr.addAll(Arrays.asList(individuosSobreviventes));
        arr.addAll(Arrays.asList(filhos));
        Individuo[] indi = new Individuo[arr.size()];
        indi = arr.toArray(indi);
        Arrays.sort(indi);
        arr = new ArrayList<>(Arrays.asList(indi));
        individuosSobreviventes = arr.subList(0, POPULACAO)
                .toArray(individuosSobreviventes);
        System.out.println("TUDO");
        for (Individuo arr1 : arr) {
            System.out.println(arr1.toString() + "Fitnes:" + arr1.getFitness());
        }
        System.out.println("SOBREVIVENTES");
        for (Individuo individuosSobrevivente : individuosSobreviventes) {
            System.out.println(individuosSobrevivente.toString() + "Fitnes:" + individuosSobrevivente.getFitness());
        }
    }
}

