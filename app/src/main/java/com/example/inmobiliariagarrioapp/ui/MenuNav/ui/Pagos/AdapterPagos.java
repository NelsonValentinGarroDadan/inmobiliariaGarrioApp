package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Pagos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.Modelos.Pago;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        LocalDate Fecha = LocalDate.parse(pagos.get(position).getFecha(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        holder.tvPagCodigo.setText(pagos.get(position).getId()+"");
        holder.tvPagNroPago.setText(pagos.get(position).getNroPago()+"");
        holder.tvPagCodContrato.setText(pagos.get(position).getAlquiler().getId()+"");
        holder.tvPagImporte.setText("$"+pagos.get(position).getImporte());
        holder.tvPagFecha.setText(Fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
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
