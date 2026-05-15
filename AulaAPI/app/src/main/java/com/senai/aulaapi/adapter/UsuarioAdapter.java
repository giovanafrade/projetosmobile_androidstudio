package com.senai.aulaapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.aulaapi.R;
import com.senai.aulaapi.model.Usuario;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {
    private List<Usuario> lista;
    public UsuarioAdapter(List<Usuario> lista){
        this.lista = lista;
    }

    @NonNull
    @Override
    public UsuarioAdapter.UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);

       return UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdapter.UsuarioViewHolder holder, int position) {

        Usuario usuario = lista.get(position);
        holder.txt1.setText(usuario.getName());
        holder.txt2.setText(usuario.getEmail());

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder{
        TextView txt1, txt2;

        public UsuarioViewHolder(@NonNull View itemView){
            super(itemView);

            txt1 = itemView.findViewById(R.id.text1);
            txt2 = itemView.findViewById(R.id.text2);
        }
    }
}
