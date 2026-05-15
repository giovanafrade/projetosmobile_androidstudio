package com.senai.aulacrud.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.senai.aulacrud.model.Aluno;

import java.util.List;

@Dao
public interface AlunoDAO {

    @Insert
    void inserir(Aluno aluno);
    @Update
    void atualizar(Aluno aluno);
    @Delete
    void excluir(Aluno aluno);

    @Query("SELECT * FROM alunos ORDER BY nome ASC")
    List<Aluno> listarTodos();

}
