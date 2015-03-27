package QuadroHorario;

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
public class SelecaoNatural {

    public static final int 
            POPULACAO   = 100,
            GERACOES    = 100000,
            MUTACAOP100 = 100,
            
            ESCOLHA_DEFAULT             = 0,
            ESCOLHA_ROLETA1             = 1, PRESSAO_ROLETA = 2,
            ESCOLHA_ROLETA2             = 2,
            ESCOLHA_TORNEIO_ESTOCASTICO = 3, K_TORNEIO = 2,
            TIPO_ESCOLHA_PAIS = ESCOLHA_TORNEIO_ESTOCASTICO;

    private Individuo[] pais, filhos;
    private static boolean 
            ELITISMO            = false,
            LOGSOBREVIVENTES    = false,
            LOGFILHOS           = false,
            LOGPAIS             = false,
            LOGSTATISTIC        = true,
            LOGVETORES          = false,
            STEADYSTATE         = false;
    Random r;
    private static int GERATUAL = 1;

    public static void main(String[] args) {
        SelecaoNatural s = new SelecaoNatural();
        LOGSOBREVIVENTES = LOGPAIS = false;
        LOGFILHOS = false;
        s.inicializaPopulacao();
        for (; GERATUAL <= SelecaoNatural.GERACOES; GERATUAL++) {
            s.evolucao();
        }

        System.out.println("MELHOR SOLUÇÃO:" + s.pais[0].showAll());

    }

    public SelecaoNatural() {
        this.pais = new Individuo[POPULACAO];
        this.filhos = new Individuo[POPULACAO];
        r = new Random();
    }

    public void inicializaPopulacao() {
        for (int i = 0; i < POPULACAO; i++) {
            pais[i] = new Individuo();
            if (LOGPAIS) {
                System.out.println(pais[i].toString() + " ");
            }
        }
    }

    public Individuo roleta1() {
        int at = 0, n;
        for (Individuo indi : pais) {
            at += indi.getFitness();
        }
        n = r.nextInt(at + 1) / PRESSAO_ROLETA;
        for (Individuo indi : pais) {
            if (indi.getFitness() >= n) {
                return indi;
            }
        }
        return null;
    }

    public Individuo roleta2() {
        int at = 0, n, soma = 0;
        for (Individuo indi : pais) {
            at += indi.getFitness();
        }
        n = r.nextInt(at + 1);
        for (Individuo indi : pais) {
            soma += indi.getFitness();
            if (soma >= n) {
                return indi;
            }
        }
        return null;
    }

    public Individuo torneioEstocastico() {
        Individuo campeao = null, indi[] = new Individuo[K_TORNEIO];
        for (int i = 0; i < K_TORNEIO; i++) {
            indi[i] = pais[r.nextInt(POPULACAO)];
        }
        for (Individuo x : indi) {
            if (campeao == null || campeao.getFitness() < x.getFitness()) {
                campeao = x;
            }
        }
        return campeao;
    }

    public void geraFilhos() {
        if (LOGFILHOS) {
            System.out.println("GERANDO FILHOS");
        }
        for (int i = 0; i < POPULACAO; i += 2) {
            int corte = r.nextInt(Individuo.GENES - 1);
            Individuo pai = null, mae = null;
            switch (TIPO_ESCOLHA_PAIS) {
                case ESCOLHA_ROLETA1:
                    while (pai == null) {
                        pai = roleta1();
                    }
                    while (mae == null) {
                        mae = roleta1();
                    }
                    break;
                case ESCOLHA_ROLETA2:
                    while (pai == null) {
                        pai = roleta2();
                    }
                    while (mae == null) {
                        mae = roleta2();
                    }
                    break;
                case ESCOLHA_TORNEIO_ESTOCASTICO:
                    while (pai == null) {
                        pai = torneioEstocastico();
                    }
                    while (mae == null) {
                        mae = torneioEstocastico();
                    }
                    break;
                default:
                    pai = pais[r.nextInt(POPULACAO)];
                    mae = pais[r.nextInt(POPULACAO)];
                    break;
            }

            int mutacaof1 = r.nextInt(101)-MUTACAOP100;
            int mutacaof2 = r.nextInt(101)-MUTACAOP100;

            filhos[i] = new Individuo(pai, mae, corte, mutacaof1, r);
            filhos[i + 1] = new Individuo(mae, pai, corte, mutacaof2, r);
            if (LOGFILHOS) {
                String rr = "Pai:" + pai.toString()
                        + "Mae:" + mae.toString()
                        + "Corte:" + corte;
                String r2 = "Pai:" + mae.toString()
                        + "Mae:" + pai.toString()
                        + "Corte:" + corte;
                System.out.println(rr + "Filho:" + filhos[i].toString() + " ");
                System.out.println(r2 + "Filho:" + filhos[i + 1].toString() + " ");
            }
        }
    }

    void evolucao() {
        geraFilhos();
        Individuo paisEFilhos[] = new Individuo[POPULACAO * 2], elite;

        for (int i = 0; i < POPULACAO * 2; i++) {
            paisEFilhos[i] = (i < POPULACAO) ? pais[i] : filhos[i - POPULACAO];
        }

        Arrays.sort(paisEFilhos);
        if (LOGVETORES) {
            System.out.println("PAIS:" + Arrays.toString(pais));
            System.out.println("FILHOS:" + Arrays.toString(filhos));
            System.out.println("TODOS:" + Arrays.toString(paisEFilhos));
        }
        if (ELITISMO) {
            elite = pais[0];
        }
        System.arraycopy(paisEFilhos, 0, pais, 0, pais.length);
        if (STEADYSTATE) {
            for (int i = pais.length / 4; i >= 0; i--) {
                pais[pais.length - i - 1] = new Individuo();
            }
        }
        if (LOGVETORES) {
            System.out.println("NOVOS:" + Arrays.toString(pais));
        }
        if (LOGSOBREVIVENTES) {
            System.out.println("TUDO");
            for (Individuo arr1 : paisEFilhos) {
                System.out.println(arr1.toString());
            }
            System.out.println("SOBREVIVENTES");
            for (Individuo individuosSobrevivente : pais) {
                System.out.println(individuosSobrevivente.toString());
            }
        }
        if (LOGSTATISTIC) {
            System.out.println(GERATUAL + "," + pais[0].getFitness() + ","
                    + ((pais[0].getFitness() + pais[POPULACAO - 1].getFitness()) / 2)
                    + "," + pais[POPULACAO - 1].getFitness());
        }
    }
}
