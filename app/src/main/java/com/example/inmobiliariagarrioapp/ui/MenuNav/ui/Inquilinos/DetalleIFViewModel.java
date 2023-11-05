package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariagarrioapp.Modelos.Alquiler;
import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.example.inmobiliariagarrioapp.Modelos.Inquilino;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleIFViewModel extends AndroidViewModel {
    private MutableLiveData<Alquiler> mAlquiler = new MutableLiveData<>();
    private Context context;

    public DetalleIFViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    public LiveData getInquilinoLiveData() {
        return mAlquiler;
    }

    public void obtenerInquilino(Bundle bundle){
        if (bundle == null || !bundle.containsKey("inmueble")) return;
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        if(inmueble == null) return;
        ApiClientRetrofit.ApiInmobiliaria api = ApiClientRetrofit.getApiInmobiliaria();
        String token = "Bearer " + ApiClientRetrofit.leerToken(context);
        Call<Alquiler> llamada = api.obtenerAlquilerXInmueble(token, inmueble);
        llamada.enqueue(new Callback<Alquiler>() {
            @Override
            public void onResponse(Call<Alquiler> call, Response<Alquiler> response) {
                if (response.isSuccessful()) {
                    mAlquiler.postValue(response.body());
                    Log.d("Salida", "Respuesta exitosa");
                } else {
                    Log.d("Salida", "Error: "+response.code());
                    Toast.makeText(context, "Error:" + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Alquiler> call, Throwable t) {
                Log.d("Salida", "Error:" + t.toString());
                Toast.makeText(context, "Error:" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
