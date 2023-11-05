package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.example.inmobiliariagarrioapp.R;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InmuebleViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<List<Inmueble>> mInmuebles;
    private ApiClientRetrofit.ApiInmobiliaria api;
    public InmuebleViewModel(@NonNull Application application) {
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
        String token = "Bearer "+ ApiClientRetrofit.leerToken(context);
        Call<List<Inmueble>> llamada = api.obtenerPropiedades(token);
        llamada.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    List<Inmueble> lista = response.body();
                    Gson gson = new Gson();
                    String listaJson = gson.toJson(lista);
                    Log.d("Salida",listaJson);
                    mInmuebles.postValue(lista);
                }else{
                    Toast.makeText(context,"Ocurrio un error al traer los inmuebles",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("Salida","Erro: "+t.toString());
                Toast.makeText(context,"Ocurrio con el servidor",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void cargarInmubleCreado(Bundle bundle, View view){
        if(bundle == null) return;
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");
        if(inmueble != null) return;
        Log.d("Salida","Se creo con exito el inmueble");
        Toast.makeText(context,"Se creo con exito el inmueble",Toast.LENGTH_LONG).show();
        DetalleInmubleFragment fragment = new DetalleInmubleFragment();
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_nav_inmuebles_to_fragment_detalle_inmuble, bundle);

    }
}