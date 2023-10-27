package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Pagos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariagarrioapp.Modelos.Alquiler;
import com.example.inmobiliariagarrioapp.databinding.FragmentContratoBinding;
import com.example.inmobiliariagarrioapp.databinding.FragmentPagosBinding;
import com.example.inmobiliariagarrioapp.modelo.Contrato;
import com.example.inmobiliariagarrioapp.modelo.Inmueble;
import com.example.inmobiliariagarrioapp.Modelos.Pago;
import com.example.inmobiliariagarrioapp.request.ApiClient;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Contratos.ContratoViewModel;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos.AdapterInquilino;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPagos  extends Fragment {

    private FragmentPagosBinding binding;
    private FragmentPagosViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPagosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm = new ViewModelProvider(this).get(FragmentPagosViewModel.class);
        vm.getMutable().observe(getViewLifecycleOwner(), new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> lista) {
                RecyclerView r = binding.rPagos;
                GridLayoutManager glm = new GridLayoutManager(requireContext(), 1, GridLayoutManager.VERTICAL, false);
                r.setLayoutManager(glm);
                AdapterPagos adapter = new AdapterPagos(requireContext(), lista, getLayoutInflater());
                r.setAdapter(adapter);
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            Alquiler alquiler = (Alquiler) bundle.getSerializable("alquiler");
            if (alquiler != null) {
                vm.obtenerPagos(alquiler.getId());
            }
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}