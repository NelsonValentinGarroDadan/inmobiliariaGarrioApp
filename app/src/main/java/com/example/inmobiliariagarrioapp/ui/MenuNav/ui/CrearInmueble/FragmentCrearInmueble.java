package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.CrearInmueble;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.example.inmobiliariagarrioapp.Modelos.Propietario;
import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.databinding.FragmentCrearInmuebleBinding;
import com.example.inmobiliariagarrioapp.databinding.FragmentInquilinoBinding;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inmuebles.DetalleInmubleFragment;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos.AdapterInquilino;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos.InquilinoViewModel;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;

public class FragmentCrearInmueble extends Fragment {
    private FragmentCrearInmuebleBinding binding;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CrearInmuebleVIewModel vm =
                new ViewModelProvider(this).get(CrearInmuebleVIewModel.class);

        binding = FragmentCrearInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getMutable().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble", inmueble);
                DetalleInmubleFragment fragment = new DetalleInmubleFragment();
                NavController navController = Navigation.findNavController(root);
                navController.navigate(R.id.action_fragment_crear_inmueble_to_fragment_detalle_inmuble, bundle);
            }
        });
        binding.btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedImageUri != null) {
                    Inmueble inmueble = new Inmueble();
                    Propietario propietario = ApiClientRetrofit.obtenerPerfil(getContext());
                    inmueble.setPropietario(propietario);
                    inmueble.setLatitud(binding.etLatitud.getText().toString());
                    inmueble.setLongitud(binding.etLongitud.getText().toString());
                    inmueble.setTipo(binding.etTipo.getText().toString());
                    inmueble.setUso(binding.etUso.getText().toString());
                    inmueble.setPrecio(Double.parseDouble(binding.etPrecio.getText().toString()));
                    MultipartBody.Part imagePart = null;
                    try {
                        imagePart = ApiClientRetrofit.prepareImagePart(getContext(), selectedImageUri);
                    } catch (IOException e) {
                        Toast.makeText(getContext(), "Error al preparar la imagen", Toast.LENGTH_LONG).show();
                    }

                    vm.crearInmueble(inmueble, imagePart);
                } else {
                    Toast.makeText(getContext(), "Debe Seleccionar una imagen", Toast.LENGTH_LONG).show();
                }
            }
        });
        binding.btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });


        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleccionar imagen"), PICK_IMAGE_REQUEST);
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            binding.imageView.setImageURI(selectedImageUri);
        }
    }
}
