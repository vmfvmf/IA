package QuadroHorario;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vmf
 */
public class Escola {
    ArrayList<Professor> professores;
    ArrayList<String> series;
    ArrayList<String> disciplinas;
    //int[][][][] quadrosHorario;// [SÉRIE][DIASEMANA][N_AULA][PROFESSOR]
    int aulasDia;
   
    public class Professor {
        public String nome;
        ArrayList<Integer> disciplinas;
        boolean disponiblidade[/* DiaSemana */][/* NAula */];
        public Professor(String n, ArrayList<Integer> disciplinas, boolean disp[][]){
            this.disciplinas= new ArrayList<>(disciplinas);
            this.nome=n;
            this.disponiblidade=disp;
        }

    }
    public void addProfessor(String n, ArrayList<Integer> disciplinas, boolean disp[][]){
        professores.add(new Professor(n,disciplinas,disp));
    }
    public Professor getProfessor(int i){
        return professores.get(i);
    }
    
    void setProfessor(int i, String nome, ArrayList<Integer> disciplinas, boolean[][] disp) {
        professores.get(i).nome = nome;
        professores.get(i).disciplinas = disciplinas;
        professores.get(i).disponiblidade = disp;
    }
    
    public Escola(int dias){
        professores=new ArrayList<>();
        series=new ArrayList<>();
        disciplinas=new ArrayList<>();
    }
    public String getDisciplina(int i){
        return disciplinas.get(i);
    }
    public String getSeries(int i){
        return series.get(i);
    }
    public void addDisciplina(String d){
        disciplinas.add(d);
    }
    public void addSeries(String s){
        series.add(s);
    }
    
    public static class Constantes{
        public static enum diasSemana {SEG,TER,QUA,QUI,SEX,SAB,DOM,DiasUteis};
        public static enum disciplinas{PORTUGUES,MATEMATICA,BIOLOGIA,HISTORIA,GEROGRAFIA,INGLES,ESPANHOL};
    }
}
