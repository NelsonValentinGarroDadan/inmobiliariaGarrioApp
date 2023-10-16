package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Contratos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.modelo.Contrato;
import com.example.inmobiliariagarrioapp.modelo.Inmueble;
import com.example.inmobiliariagarrioapp.modelo.Inquilino;
import com.example.inmobiliariagarrioapp.modelo.Pago;
import com.example.inmobiliariagarrioapp.request.ApiClient;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos.AdapterInquilino;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos.DetalleInquilinoFragment;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Pagos.AdapterPagos;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Pagos.FragmentPagos;

import java.util.ArrayList;

public class DetalleContratoFragment extends Fragment {
    private TextView tvConCodigo,tvConFInicio ,tvConFFin ,tvConImporte, tvConInquilino ,tvConInmueble ,btnPagos;
    private ApiClient api;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_contrato, container, false);
        tvConCodigo = view.findViewById(R.id.tvConCodigo);
        tvConFInicio = view.findViewById(R.id.tvConFInicio);
        tvConFFin = view.findViewById(R.id.tvConFFin);
        tvConImporte = view.findViewById(R.id.tvConImporte);
        tvConInquilino = view.findViewById(R.id.tvConInquilino);
        tvConInmueble = view.findViewById(R.id.tvConInmueble);
        btnPagos = view.findViewById(R.id.btnPagos);
        api = ApiClient.getApi();
        Bundle bundle = getArguments();
        if (bundle != null) {
            Contrato contrato = (Contrato) bundle.getSerializable("contrato");
            if (contrato != null) {

                Inmueble inmueble = contrato.getInmueble();
                Inquilino inquilino = contrato.getInquilino();
                tvConCodigo.setText(contrato.getIdContrato()+"");
                tvConFInicio.setText(contrato.getFechaInicio());
                tvConFFin.setText(contrato.getFechaFin());
                tvConImporte.setText(contrato.getMontoAlquiler()+"");
                tvConInquilino.setText(inquilino.getNombre()+" "+inquilino.getApellido());
                tvConInmueble.setText("Inmueble en "+inmueble.getDireccion());
                btnPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contrato",contrato);
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_fragment_detalle_contrato_to_fragment_pagos, bundle);
                    }
                });
            }
        }

        return view;
    }
}
