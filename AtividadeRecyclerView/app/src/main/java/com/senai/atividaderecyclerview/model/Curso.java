package com.senai.atividaderecyclerview.model;

public class Curso {

    private String curso;

    private String cargaHoraria;
    private String nomeProf;

    public Curso(String curso, String cargaHoraria, String nomeProf){
        this.curso = curso;
        this.cargaHoraria = cargaHoraria;
        this.nomeProf = nomeProf;
    }

    public String getCurso(){
        return curso;
    }

    public String getCargaHoraria(){
            return cargaHoraria;
    }

    public String getNomeProf(){
            return nomeProf;
    }

}
