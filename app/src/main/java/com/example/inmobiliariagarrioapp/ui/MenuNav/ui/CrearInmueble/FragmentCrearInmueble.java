package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.CrearInmueble;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
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
    private ActivityResultLauncher<Intent> arl;
    private CrearInmuebleVIewModel vm;
    private Intent intentImg;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm =
                new ViewModelProvider(this).get(CrearInmuebleVIewModel.class);

        binding = FragmentCrearInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //observers
        vm.getMutable().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                Toast.makeText(getContext(), "Se creo con exito el inmueble en: "+inmueble.getDireccion(), Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmueble",inmueble);

                NavController navController = Navigation.findNavController(root);
                navController.navigate(R.id.action_fragment_crear_inmueble_to_nav_inmuebles,bundle);

            }
        });
        vm.getMutableUri().observe(getViewLifecycleOwner(), new Observer<Uri>() {
            @Override
            public void onChanged(Uri uri) {
                selectedImageUri = uri;
                binding.imageView.setImageURI(selectedImageUri);
            }
        });

        binding.btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Inmueble inmueble = new Inmueble();
                    Propietario propietario = ApiClientRetrofit.obtenerPerfil(getContext());
                    inmueble.setPropietario(propietario);
                    inmueble.setDireccion(binding.etDireccion.getText().toString());
                    inmueble.setCAmbientes(Integer.parseInt(binding.etCA.getText().toString()));
                    inmueble.setTipo(binding.etTipo.getText().toString());
                    inmueble.setUso(binding.etUso.getText().toString());
                    inmueble.setPrecio(Double.parseDouble(binding.etPrecio.getText().toString()));
                    vm.crearInmueble(inmueble, selectedImageUri);
            }
        });
        binding.btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                arl.launch(intentImg);
            }
        });
        abrirGaleria();

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void abrirGaleria() {
        intentImg = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        arl = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                vm.verificarUri(result);
            }
        });
    }

}
