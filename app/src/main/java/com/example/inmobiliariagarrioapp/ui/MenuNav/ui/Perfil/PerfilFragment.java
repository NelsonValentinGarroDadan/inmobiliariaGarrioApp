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

import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.databinding.FragmentPerfilBinding;
import com.example.inmobiliariagarrioapp.modelo.Propietario;


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
                String email = binding.etEmail.getText().toString();
                String clave = binding.etPsw.getText().toString();
                String telefono = binding.etTel.getText().toString();
                String nombreAvatar= getResources().getResourceEntryName(binding.ivAvatar.getId());
                int avatar = getResources().getIdentifier(nombreAvatar, "drawable", requireActivity().getPackageName());
                Propietario p = new Propietario(id,dni,nombre,apellido,email,clave,telefono,avatar);
                vm.EditarUsuario(p);
            }
        });
        vm.getMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                binding.etId.setText(p.getId()+"");
                binding.etDNI.setText(p.getDni()+"");
                binding.etNbre.setText(p.getNombre());
                binding.etApellido.setText(p.getApellido());
                binding.etEmail.setText(p.getEmail());
                binding.etPsw.setText(p.getContraseña());
                binding.ivAvatar.setImageResource(p.getAvatar());
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