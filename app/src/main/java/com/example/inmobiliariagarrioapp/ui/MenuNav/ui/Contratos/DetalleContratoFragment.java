package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Contratos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.Modelos.Alquiler;
import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.example.inmobiliariagarrioapp.Modelos.Inquilino;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DetalleContratoFragment extends Fragment {
    private TextView tvConCodigo,tvConFInicio ,tvConFFin ,tvConImporte, tvConInquilino ,tvConInmueble ,btnPagos;

    private DetalleCFViewModel vm;
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
        vm = new ViewModelProvider(this).get(DetalleCFViewModel.class);
        vm.getMutable().observe(getViewLifecycleOwner(), new Observer<Alquiler>() {
            @Override
            public void onChanged(Alquiler alquiler) {
                LocalDate FechaInicio = LocalDate.parse(alquiler.getFecha_Inicio(),DateTimeFormatter.ISO_LOCAL_DATE_TIME );
                LocalDate FechaFin = LocalDate.parse(alquiler.getFecha_Fin(),DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                Inmueble inmueble = alquiler.getInmueble();
                Inquilino inquilino = alquiler.getInquilino();
                tvConCodigo.setText(alquiler.getId()+"");
                tvConFInicio.setText(FechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                tvConFFin.setText(FechaFin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                tvConImporte.setText(alquiler.getPrecio()+"");
                tvConInquilino.setText(inquilino.getPersona().getNombre()+" "+inquilino.getPersona().getApellido());
                tvConInmueble.setText("Inmueble en "+inmueble.getDireccion());
                btnPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("alquiler",alquiler);
                        NavController navController = Navigation.findNavController(view);
                        navController.navigate(R.id.action_fragment_detalle_contrato_to_fragment_pagos, bundle);
                    }
                });
            }
        });
        Bundle bundle = getArguments();
        if (bundle != null) {
            Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
            vm.obtnerAlquieler(inmueble);
        }

        return view;
    }
}
