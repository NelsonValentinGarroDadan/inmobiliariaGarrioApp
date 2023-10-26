package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelDetalleInmueble extends AndroidViewModel {
    private Context context;
    private ApiClientRetrofit.ApiInmobiliaria api;
    public ViewModelDetalleInmueble(@NonNull Application application) {
        super(application);
        this.context = application;
        this.api = ApiClientRetrofit.getApiInmobiliaria();
    }
    public void cambiarDisponibilidad(boolean estado){
        String token = "Bearer "+ ApiClientRetrofit.leerToken(context);
        Call<Inmueble> llamada = api.cambiarDisponibilidad(token,estado);
        llamada.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context,"Se alctualizo el inmueble con exito",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"Ocurrio un error al intentar actualizar el inmueble",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {
                Log.d("Salida",t.toString());
                Toast.makeText(context,"Error :"+t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
