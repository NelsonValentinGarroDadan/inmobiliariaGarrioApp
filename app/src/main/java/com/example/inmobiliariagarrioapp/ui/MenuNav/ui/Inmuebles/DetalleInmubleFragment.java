package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inmuebles;

import android.annotation.SuppressLint;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

        import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.Modelos.Inmueble;


public class DetalleInmubleFragment extends Fragment {

    private TextView tvInmCodigo, tvInmAmbientes, tvInmDireccion, tvInmPrecio, tvInmUso, tvInmTipo;
    private ImageView ivInmFoto;
    private CheckBox cbInmDisponible;
    private ViewModelDetalleInmueble vm;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_inmueble, container, false);
        vm=  new ViewModelProvider(this).get(ViewModelDetalleInmueble.class);
        tvInmCodigo = view.findViewById(R.id.tvInmCodigo);
        tvInmAmbientes = view.findViewById(R.id.tvInmAmbientes);
        tvInmDireccion = view.findViewById(R.id.tvInmDireccion);
        tvInmPrecio = view.findViewById(R.id.tvInmPrecio);
        tvInmUso = view.findViewById(R.id.tvInmUso);
        tvInmTipo = view.findViewById(R.id.tvInmTipo);
        ivInmFoto = view.findViewById(R.id.ivInmFoto);
        cbInmDisponible = view.findViewById(R.id.cbInmDisponible);
        Bundle bundle = getArguments();
        if (bundle != null) {
           Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
            if (inmueble != null) {
                tvInmCodigo.setText(inmueble.getId()+"");
                tvInmAmbientes.setText(inmueble.getCAmbientes()+"");
                tvInmDireccion.setText(inmueble.getLongitud()+inmueble.getLatitud());
                tvInmPrecio.setText(inmueble.getPrecio()+"");
                tvInmTipo.setText(inmueble.getTipo());
                tvInmUso.setText(inmueble.getUso());
                Glide.with(getContext())
                        .load("http://192.168.0.120:5000/api/Inmuebles/Imagenes/"+inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivInmFoto);
                cbInmDisponible.setChecked(inmueble.isDisponible());
                cbInmDisponible.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vm.cambiarDisponibilidad(cbInmDisponible.isChecked());
                    }
                });
            }
        }

        return view;
    }
}
