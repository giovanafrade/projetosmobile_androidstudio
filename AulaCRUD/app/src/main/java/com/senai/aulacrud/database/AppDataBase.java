package com.senai.aulacrud.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.senai.aulacrud.DAO.AlunoDAO;
import com.senai.aulacrud.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)

public abstract class AppDataBase extends RoomDatabase {

    public abstract AlunoDAO alunoDAO();
    private static AppDataBase INSTANCE;
    public static AppDataBase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class,"banco_alunos").allowMainThreadQueries().build();

            return INSTANCE;

        }
    };

}
