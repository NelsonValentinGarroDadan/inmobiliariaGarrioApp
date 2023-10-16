package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariagarrioapp.databinding.FragmentInquilinoBinding;
import com.example.inmobiliariagarrioapp.modelo.Inmueble;
import com.example.inmobiliariagarrioapp.ui.Main.MainActivity;
import com.example.inmobiliariagarrioapp.ui.MenuNav.MenuActivity;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inmuebles.AdapterInmueble;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inmuebles.InmuebleViewModel;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Logout.Dialogo;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Logout.LogoutFragment;

import java.util.ArrayList;

public class InquilinoFragment extends Fragment {

    private FragmentInquilinoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        InquilinoViewModel vm =
                new ViewModelProvider(this).get(InquilinoViewModel.class);

        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        vm.getMutable().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> lista) {
                RecyclerView r = binding.rInquilinos;
                GridLayoutManager glm = new GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false);
                r.setLayoutManager(glm);
                AdapterInquilino adapter = new AdapterInquilino(requireContext(), lista, getLayoutInflater());
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