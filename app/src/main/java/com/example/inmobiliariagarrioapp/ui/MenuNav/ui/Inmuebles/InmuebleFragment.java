package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inmuebles;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariagarrioapp.databinding.FragmentInmuebleBinding;
import com.example.inmobiliariagarrioapp.Modelos.Inmueble;

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
        vm.cargarInmuebles();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}