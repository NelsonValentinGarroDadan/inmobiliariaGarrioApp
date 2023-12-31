package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Contratos;

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
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleCFViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Alquiler> mAlquiler;
    public DetalleCFViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }
    public LiveData getMutable() {
        if(mAlquiler == null){
            mAlquiler = new MutableLiveData<>();
        }
        return mAlquiler;
    }
    public void obtnerAlquiler(Bundle bundle){
        if (bundle == null || !bundle.containsKey("inmueble")) return;
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        if(inmueble == null) return;
        ApiClientRetrofit.ApiInmobiliaria api = ApiClientRetrofit.getApiInmobiliaria();
        String token ="Bearer "+ ApiClientRetrofit.leerToken(context);
        Call<Alquiler> llamada = api.obtenerAlquilerXInmueble(token,inmueble);
        llamada.enqueue(new Callback<Alquiler>() {
            @Override
            public void onResponse(Call<Alquiler> call, Response<Alquiler> response) {
                if(response.isSuccessful()){
                    mAlquiler.postValue(response.body());
                }else{
                    Log.d("Salida",response.errorBody().toString());
                    Toast.makeText(context,"Error al traer el alquiler",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Alquiler> call, Throwable t) {
                Toast.makeText(context,"Error:"+t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
