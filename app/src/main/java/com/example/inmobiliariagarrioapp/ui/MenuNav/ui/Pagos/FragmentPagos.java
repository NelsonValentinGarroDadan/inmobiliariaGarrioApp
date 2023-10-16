package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Pagos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliariagarrioapp.databinding.FragmentContratoBinding;
import com.example.inmobiliariagarrioapp.databinding.FragmentPagosBinding;
import com.example.inmobiliariagarrioapp.modelo.Contrato;
import com.example.inmobiliariagarrioapp.modelo.Inmueble;
import com.example.inmobiliariagarrioapp.modelo.Pago;
import com.example.inmobiliariagarrioapp.request.ApiClient;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Contratos.ContratoViewModel;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos.AdapterInquilino;

import java.util.ArrayList;

public class FragmentPagos  extends Fragment {

    private FragmentPagosBinding binding;
    private ApiClient api;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPagosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        api = ApiClient.getApi();

        Bundle bundle = getArguments();
        if (bundle != null) {
            Contrato contrato = (Contrato) bundle.getSerializable("contrato");
            if (contrato != null) {
                ArrayList<Pago> lista = api.obtenerPagos(contrato);
                RecyclerView r = binding.rPagos;
                GridLayoutManager glm = new GridLayoutManager(requireContext(), 1, GridLayoutManager.VERTICAL, false);
                r.setLayoutManager(glm);
                AdapterPagos adapter = new AdapterPagos(requireContext(), lista, getLayoutInflater());
                r.setAdapter(adapter);
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