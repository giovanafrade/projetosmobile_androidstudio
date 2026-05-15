package com.senai.aulaapi.service;

import com.senai.aulaapi.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    Call<List<Usuario>> listarUsuarios();

}
