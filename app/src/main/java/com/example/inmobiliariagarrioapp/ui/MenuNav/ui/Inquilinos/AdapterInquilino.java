package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.Modelos.Inmueble;

import java.util.ArrayList;

public class AdapterInquilino extends RecyclerView.Adapter<AdapterInquilino.ViewHolder>{
    private Context context;
    private ArrayList<Inmueble> inmuebles;
    private LayoutInflater li;

    public AdapterInquilino(Context context, ArrayList<Inmueble> inmuebleArrayList, LayoutInflater li) {
        this.context = context;
        this.inmuebles = inmuebleArrayList;
        this.li = li;
        if(inmuebles.size()==0){
            Toast.makeText(context, "El propietario no posee propiedades en alquiler", Toast.LENGTH_SHORT).show();
        }
    }

    @NonNull
    @Override
    public AdapterInquilino.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_inmueble,parent,false);
        return new AdapterInquilino.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInquilino.ViewHolder holder, int position) {
        holder.info.setText(inmuebles.get(position).getDireccion()+"\n Ver");
        String imagen = inmuebles.get(position).getImagen().replace("\\","/");
        String url ="http://192.168.0.120:5000"+imagen;
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView info;
        private ImageView foto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.info = itemView.findViewById(R.id.etInfoInm);
            this.foto = itemView.findViewById(R.id.ivFotoInm);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("inmueble", inmuebles.get(position));
                        DetalleInquilinoFragment fragment = new DetalleInquilinoFragment();
                        NavController navController = Navigation.findNavController(v);
                        navController.navigate(R.id.action_nav_inquilinos_to_fragment_detalle_inquilino, bundle);
                    }
                }
            });


        }

    }
}
