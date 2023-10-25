package com.example.inmobiliariagarrioapp.ui.MenuNav;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariagarrioapp.Modelos.Propietario;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;

public class ViewModelMenu extends AndroidViewModel {
    private Context context ;
    private MutableLiveData<Propietario> mPActual;
    public ViewModelMenu(@NonNull Application application) {
        super(application);
        this.context = application;
        mPActual= new MutableLiveData<>();
    }
    public LiveData getMutable(){
        return mPActual;
    }
    public void obtenerPActual(){
        Propietario p = ApiClientRetrofit.obtenerPerfil(context);
        mPActual.setValue(p);
    }
}
