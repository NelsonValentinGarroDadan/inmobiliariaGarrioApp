package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Contratos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariagarrioapp.modelo.Inmueble;
import com.example.inmobiliariagarrioapp.request.ApiClient;

import java.util.ArrayList;

public class ContratoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Inmueble>> mInmuebles;
    private ApiClient api;
    public ContratoViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        api = ApiClient.getApi();
    }
    public LiveData getMutable()
    {
        if(mInmuebles == null){
            mInmuebles = new MutableLiveData<>();
        }
        return mInmuebles;
    }
    public void cargarInmuebles(){
        mInmuebles.setValue(api.obtenerPropiedadesAlquiladas());
    }
}