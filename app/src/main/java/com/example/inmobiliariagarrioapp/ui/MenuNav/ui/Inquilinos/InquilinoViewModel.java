package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliariagarrioapp.Modelos.Alquiler;
import com.example.inmobiliariagarrioapp.request.ApiClient;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;

import java.util.List;

import retrofit2.Call;

public class InquilinoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Alquiler>> mAlquileres;
    private ApiClientRetrofit.ApiInmobiliaria api;
    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        api = ApiClientRetrofit.getApiInmobiliaria();
    }
    public LiveData getMutable()
    {
        if(mAlquileres == null){
            mAlquileres = new MutableLiveData<>();
        }
        return mAlquileres;
    }
    public void cargarInmuebles(){
        String token = ApiClientRetrofit.leerToken(context);
        Call<List<Alquiler>> llamada = api.obtenerPropiedadesAlquiladas(token);

    }
}