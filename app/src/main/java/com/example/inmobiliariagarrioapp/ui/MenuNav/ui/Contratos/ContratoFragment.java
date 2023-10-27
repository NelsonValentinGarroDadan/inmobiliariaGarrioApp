package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Contratos;

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

import com.example.inmobiliariagarrioapp.databinding.FragmentContratoBinding;
import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inmuebles.InmuebleViewModel;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos.AdapterInquilino;

import java.util.ArrayList;

public class ContratoFragment extends Fragment {

    private FragmentContratoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContratoViewModel vm =
                new ViewModelProvider(this).get(ContratoViewModel.class);

        binding = FragmentContratoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> lista) {
                RecyclerView r = binding.rContratos;
                GridLayoutManager glm = new GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false);
                r.setLayoutManager(glm);
                AdapterContrato adapter = new AdapterContrato(requireContext(), lista, getLayoutInflater());
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