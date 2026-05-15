package com.senai.aulalistafotos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senai.aulalistafotos.R;
import com.senai.aulalistafotos.model.FotoItem;

import java.util.List;

public class FotoAdapter extends RecyclerView.Adapter<FotoAdapter.FotoViewHolder> {

    public interface OnDeleteClickListener{
        void OnDeleteClick(int position);

    }
    private List<FotoItem> lista;

    private OnDeleteClickListener onDeleteClickListener;

    @NonNull
    @Override
    public FotoAdapter.FotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_foto, parent, false);

        return new FotoViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull FotoAdapter.FotoViewHolder holder, int position) {

        FotoItem item = lista.get(position);

        if (item.getUri() != null){
            holder.imgItem.setImageURI(item.getUri());
        } else {
            holder.imgItem.setImageBitmap(item.getBitmap());
        }

        holder.btnDelete.setOnClickListener(v -> {
            onDeleteClickListener.OnDeleteClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class FotoViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItem, btnDelete;

        public FotoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgItem = itemView.findViewById(R.id.imgItem);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }

    }

}
