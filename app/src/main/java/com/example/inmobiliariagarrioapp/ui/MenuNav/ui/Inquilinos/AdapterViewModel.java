package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos;

import android.app.Application;
import android.content.Context;
import android.media.MediaCodec;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariagarrioapp.Modelos.Inquilino;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> inquilinoLiveData = new MutableLiveData<>();
    private Context context;

    public AdapterViewModel(@NonNull Application application) {
        super(application);
        context = application;
    }

    public LiveData<Inquilino> getInquilinoLiveData() {
        return inquilinoLiveData;
    }

    public void obtenerInquilino(int IdInmueble) {
        ApiClientRetrofit.ApiInmobiliaria api = ApiClientRetrofit.getApiInmobiliaria();
        String token = "Bearer " + ApiClientRetrofit.leerToken(context);
        Call<Inquilino> llamada = api.obtenerXInmueble(token, IdInmueble);
        llamada.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if (response.isSuccessful()) {
                    inquilinoLiveData.postValue(response.body());
                    Gson gson = new Gson();
                    String inquiline = gson.toJson(response.body());
                    Log.d("Salida", "Respuesta exitosa: "+inquiline);
                } else {
                    Log.d("Salida", "Error: "+response.code());
                    Toast.makeText(context, "Error:" + response.errorBody().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Log.d("Salida", "Error:" + t.toString());
                Toast.makeText(context, "Error:" + t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
