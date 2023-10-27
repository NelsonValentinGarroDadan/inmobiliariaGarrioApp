package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Pagos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.modelo.Inmueble;
import com.example.inmobiliariagarrioapp.Modelos.Pago;
import com.example.inmobiliariagarrioapp.request.ApiClient;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Contratos.AdapterContrato;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos.DetalleInquilinoFragment;

import java.util.ArrayList;
import java.util.List;

public class AdapterPagos extends RecyclerView.Adapter<AdapterPagos.ViewHolder>{
    private Context context;
    private List<Pago> pagos;
    private LayoutInflater li;

    public AdapterPagos(Context context, List<Pago> pagosArrayList, LayoutInflater li) {
        this.context = context;
        this.pagos = pagosArrayList;
        this.li = li;
    }

    @NonNull
    @Override
    public AdapterPagos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item_pago,parent,false);
        return new AdapterPagos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPagos.ViewHolder holder, int position) {
        holder.tvPagCodigo.setText(pagos.get(position).getId()+"");
        holder.tvPagNroPago.setText(pagos.get(position).getNroPago()+"");
        holder.tvPagCodContrato.setText(pagos.get(position).getAlquiler().getId()+"");
        holder.tvPagImporte.setText("$"+pagos.get(position).getImporte());
        holder.tvPagFecha.setText(pagos.get(position).getFecha().toString());
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPagCodigo,tvPagNroPago ,tvPagCodContrato ,tvPagImporte ,tvPagFecha;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvPagCodigo = itemView.findViewById(R.id.tvPagCodigo);
            this.tvPagNroPago  = itemView.findViewById(R.id.tvPagNroPago );
            this.tvPagCodContrato = itemView.findViewById(R.id.tvPagCodContrato);
            this.tvPagImporte = itemView.findViewById(R.id.tvPagImporte);
            this.tvPagFecha = itemView.findViewById(R.id.tvPagFecha);
        }

    }
}
