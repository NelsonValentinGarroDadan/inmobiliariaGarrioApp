package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliariagarrioapp.Modelos.Persona;
import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.databinding.FragmentPerfilBinding;
import com.example.inmobiliariagarrioapp.Modelos.Propietario;
import com.example.inmobiliariagarrioapp.ui.MenuNav.MenuActivity;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ViewModelMenu;


public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PerfilViewModel vm =
                new ViewModelProvider(this).get(PerfilViewModel.class);
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(binding.etId.getText().toString());
                Long dni = Long.parseLong(binding.etDNI.getText().toString());
                String nombre = binding.etNbre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                Long telefono = Long.parseLong(binding.etTel.getText().toString());
                Persona persona = new Persona(id,dni,nombre,apellido,telefono);
                Propietario p = new Propietario(id,persona);
                vm.EditarUsuario(p);}
        });
        vm.getMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                binding.etId.setText(p.getId()+"");
                binding.etId.setEnabled(false);
                binding.etDNI.setText(p.getPersona().getDNI()+"");
                binding.etNbre.setText(p.getPersona().getNombre());
                binding.etApellido.setText(p.getPersona().getApellido());
                binding.etTel.setText(p.getPersona().getTelefono()+"");


                MenuActivity menuActivity = (MenuActivity) requireActivity();
                menuActivity.actualizarDatosMenu(p.getPersona().getNombre() + " " + p.getPersona().getApellido(), p.getMail());

            }
        });
        vm.obtenerPerfil();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}