package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.CrearInmueble;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearInmuebleVIewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inmueble> mInmueble;
    private ApiClientRetrofit.ApiInmobiliaria api;

    public CrearInmuebleVIewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        this.api = ApiClientRetrofit.getApiInmobiliaria();
    }
    public void crearInmueble(Inmueble inmueble, MultipartBody.Part imagen){
        if(inmueble.getLongitud()==null ||
                inmueble.getLatitud()==null ||
                inmueble.getTipo()==null ||
                inmueble.getUso()==null ||
                inmueble.getPrecio()==0 ||
                inmueble.getPropietario().getId()==0 ||
                imagen==null){
            Toast.makeText(context,"Todos los campos son obligatorios",Toast.LENGTH_LONG).show();
            return;
        }
        String token = "Bearer "+ApiClientRetrofit.leerToken(context);
        Call<Inmueble> llamada = api.crearInmueble(token,
                inmueble.getLongitud(),
                inmueble.getLatitud(),
                inmueble.getCAmbientes(),
                inmueble.getTipo(),
                inmueble.getUso(),
                inmueble.getPrecio(),
                inmueble.getPropietario().getId(),
                imagen
        );
        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    mInmueble.postValue(response.body());
                    Toast.makeText(context,"Se creo con exito el inmueble",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Toast.makeText(context,"Error: "+t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public LiveData getMutable(){
        if(mInmueble == null){
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }
}
