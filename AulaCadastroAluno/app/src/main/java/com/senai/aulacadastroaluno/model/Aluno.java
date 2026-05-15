package com.senai.aulacadastroaluno.model;

import java.io.Serializable;

public class Aluno  implements Serializable {

    private String nome;
    private String curso;
    private int Imagem;

    public Aluno(String nome, String curso, int imagem) {
        this.nome = nome;
        this.curso = curso;
        Imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public int getImagem() {
        return Imagem;
    }
}
