package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.databinding.FragmentInmuebleBinding;
import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class InmuebleFragment extends Fragment {

    private FragmentInmuebleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InmuebleViewModel vm =
                new ViewModelProvider(this).get(InmuebleViewModel.class);

        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.fab.setOnClickListener(new View.OnClickListener() {
              @Override
             public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_nav_inmuebles_to_fragment_crear_inmueble);
             }
        });
        vm.getMutable().observe(getViewLifecycleOwner(), new Observer<List<Inmueble>>() {
            @Override
            public void onChanged(List<Inmueble> lista) {
                RecyclerView r = binding.rInmuebles;
                GridLayoutManager glm = new GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false);
                r.setLayoutManager(glm);
                AdapterInmueble adapter = new AdapterInmueble(requireContext(), lista, getLayoutInflater());
                r.setAdapter(adapter);
            }
        });
        //vm.cargarInmubleCreado(getArguments(),root);
        vm.cargarInmuebles();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}