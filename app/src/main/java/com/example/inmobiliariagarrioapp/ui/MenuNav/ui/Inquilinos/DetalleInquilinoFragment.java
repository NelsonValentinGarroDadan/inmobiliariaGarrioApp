package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobiliariagarrioapp.Modelos.Alquiler;
import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.Modelos.Inquilino;

public class DetalleInquilinoFragment  extends Fragment {
    private TextView tvInqTelGarante,tvInqNbreGarante,tvInqTelefono,tvInqMail,tvInqDni,tvInqNombre,tvInqApellido,tvInqCodigo;
    private DetalleIFViewModel vm;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_inquilino, container, false);
        tvInqCodigo = view.findViewById(R.id.tvInqCodigo);
        tvInqNombre = view.findViewById(R.id.tvInqNombre);
        tvInqApellido = view.findViewById(R.id.tvInqApellido);
        tvInqDni = view.findViewById(R.id.tvInqDni);
        tvInqTelefono = view.findViewById(R.id.tvInqTelefono);
        vm =new  ViewModelProvider(this).get(DetalleIFViewModel.class);
        vm.getInquilinoLiveData().observe(getViewLifecycleOwner(), new Observer<Alquiler>() {
            @Override
            public void onChanged(Alquiler alquiler) {
                Inquilino inquilino = alquiler.getInquilino();
                tvInqCodigo.setText(inquilino.getId()+"");
                tvInqNombre.setText(inquilino.getPersona().getNombre());
                tvInqApellido.setText(inquilino.getPersona().getApellido());
                tvInqDni.setText(inquilino.getPersona().getDNI()+"");
                tvInqTelefono.setText(inquilino.getPersona().getTelefono()+"");
            }
        });
        vm.obtenerInquilino(getArguments());


        return view;
    }
}

