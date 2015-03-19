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
    int[][][][][][] quadrosHorario;// [DIASEMANA][N_AULA][PROFESSOR][DISPONIBILIDADE_PROFESSOR][DISCIPLINA][SÉRIE]
    int aulasDia;
    
    public class Professor {
        public String nome;
        ArrayList<Integer> disciplinas;

    }
    public Escola(int dias){
        professores=new ArrayList<>();
        series=new ArrayList<>();
        disciplinas=new ArrayList<>();
        quadrosHorario= new int[dias][][][][][];
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
        public static enum diasSemana {Seg,Ter,Qua,Qui,Sex,Sab,Dom,DiasUteis};
        public static enum disciplinas{PORTUGUES,MATEMATICA,BIOLOGIA,HISTORIA,GEROGRAFIA,INGLES,ESPANHOL};
        
        public static String getDisciplina(int disciplina){
            switch(disciplina){
                case 0:return "Português";
                case 1:return "Matemática";
                case 2:return "Biologia";
                case 3:return "História";
                case 4:return "Geografia";
                case 5:return "Inglês";
                case 6:return "Espanhol";
                default: return "Inválida";
            }
        }
        
        public static String getDiaSemana(int diaSemana){
            switch(diaSemana){
                case 0:return "Segunda";
                case 1:return "Terça";
                case 2:return "Quarta";
                case 3:return "Quinta";
                case 4:return "Sexta";
                case 5:return "Sabádo";
                case 6:return "Domingo";
                case 7:return "Dias úteis";
                default: return "Inválida";
            }
        }
    }
}
