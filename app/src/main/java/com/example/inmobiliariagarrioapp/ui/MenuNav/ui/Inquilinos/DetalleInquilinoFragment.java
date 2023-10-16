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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.modelo.Inmueble;
import com.example.inmobiliariagarrioapp.modelo.Inquilino;

public class DetalleInquilinoFragment  extends Fragment {
    private TextView tvInqTelGarante,tvInqNbreGarante,tvInqTelefono,tvInqMail,tvInqDni,tvInqNombre,tvInqApellido,tvInqCodigo;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_inquilino, container, false);
        tvInqCodigo = view.findViewById(R.id.tvInqCodigo);
        tvInqNombre = view.findViewById(R.id.tvInqNombre);
        tvInqApellido = view.findViewById(R.id.tvInqApellido);
        tvInqDni = view.findViewById(R.id.tvInqDni);
        tvInqMail = view.findViewById(R.id.tvInqMail);
        tvInqTelefono = view.findViewById(R.id.tvInqTelefono);
        tvInqNbreGarante = view.findViewById(R.id.tvInqNbreGarante);
        tvInqTelGarante = view.findViewById(R.id.tvInqTelGarante);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Inquilino inquilino = (Inquilino) bundle.getSerializable("inquilino");
            if (inquilino != null) {
                tvInqCodigo.setText(inquilino.getIdInquilino()+"");
                tvInqNombre.setText(inquilino.getNombre());
                tvInqApellido.setText(inquilino.getApellido());
                tvInqDni.setText(inquilino.getDNI()+"");
                tvInqMail.setText(inquilino.getEmail());
                tvInqTelefono.setText(inquilino.getTelefono());
                tvInqNbreGarante.setText(inquilino.getNombreGarante());
                tvInqTelGarante.setText(inquilino.getTelefonoGarante());
            }
        }

        return view;
    }
}

