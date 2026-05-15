package com.senai.aularecyclerview.model;

import android.media.Image;

public class Aluno {

    private String nome;
    private String curso;

    private int imagem;


    public Aluno(String nome, String curso, int imagem){
        this.nome = nome;
        this.curso = curso;
        this.imagem = imagem;
    }


    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public int getImagem(){
        return imagem;
    }
}
