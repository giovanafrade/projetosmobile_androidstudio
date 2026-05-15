package com.senai.aulaapi.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.senai.aulaapi.model.Usuario;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //Evita duplicação
    void inserirTodos(List<Usuario> lista);

    @Query("SELECT * FROM usuarios ORDER BY name ASC")
    List<Usuario> listarTodos();

    @Query("DELETE FROM usuarios")
    void limparTabela();

}
