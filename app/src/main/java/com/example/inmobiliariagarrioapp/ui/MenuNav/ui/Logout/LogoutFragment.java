package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Logout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliariagarrioapp.databinding.FragmentLogoutBinding;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inmuebles.InmuebleViewModel;

public class LogoutFragment extends Fragment {

    private FragmentLogoutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Dialogo d = new Dialogo(this);
        d.show(getParentFragmentManager(),"Dialogo");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}