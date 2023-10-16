package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Perfil;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariagarrioapp.modelo.Propietario;
import com.example.inmobiliariagarrioapp.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {

    private  MutableLiveData<Propietario> mPerfil;
    private ApiClient api;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        api = ApiClient.getApi();
    }


    public LiveData getMutable() {
        if(mPerfil == null){
            mPerfil  = new MutableLiveData<>();
        }
        return mPerfil;
    }
    public void obtenerPerfil(){
        Propietario p = api.obtenerUsuarioActual();
        mPerfil.setValue(p);

    }
    public void EditarUsuario(Propietario prop){
        Propietario p = mPerfil.getValue();
        p.setDni(prop.getDni());
        p.setNombre(prop.getNombre());
        p.setApellido(prop.getApellido());
        p.setEmail(prop.getEmail());
        p.setTelefono(prop.getTelefono());
        mPerfil.setValue(p);
    }
}