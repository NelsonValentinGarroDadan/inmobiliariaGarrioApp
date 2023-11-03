package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inquilinos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Inmueble>> mInmuebles;
    private ApiClientRetrofit.ApiInmobiliaria api;
    public InquilinoViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        api = ApiClientRetrofit.getApiInmobiliaria();
    }
    public LiveData getMutable()
    {
        if(mInmuebles == null){
            mInmuebles = new MutableLiveData<>();
        }
        return mInmuebles;
    }
    public void cargarInmuebles(){
        String token ="Bearer "+ ApiClientRetrofit.leerToken(context);
        Call<List<Inmueble>> llamada = api.obtenerPropiedadesAlquiladas(token);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    mInmuebles.postValue(response.body());
                }else{
                    Log.d("Salida",response.errorBody().toString());
                    Toast.makeText(context,"Error al trae los inmuebles",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context,"Error :"+t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}