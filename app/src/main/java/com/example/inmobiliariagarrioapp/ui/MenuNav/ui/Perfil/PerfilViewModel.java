package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliariagarrioapp.Modelos.Propietario;
import com.example.inmobiliariagarrioapp.request.ApiClient;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;
import com.example.inmobiliariagarrioapp.ui.MenuNav.MenuActivity;
import com.example.inmobiliariagarrioapp.ui.MenuNav.ViewModelMenu;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private  MutableLiveData<Propietario> mPerfil;
    private ApiClientRetrofit.ApiInmobiliaria  api;
    private Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        this.api = ApiClientRetrofit.getApiInmobiliaria();
    }


    public LiveData getMutable() {
        if(mPerfil == null){
            mPerfil  = new MutableLiveData<>();
        }
        return mPerfil;
    }
    public void obtenerPerfil(){
        Propietario p = ApiClientRetrofit.obtenerPerfil(context);
        mPerfil.setValue(p);
    }
    public void EditarUsuario(Propietario prop){
        Log.d("Salida","lo que se envia");
        Propietario p = ApiClientRetrofit.obtenerPerfil(context);
        String token = "Bearer "+ApiClientRetrofit.leerToken(context);
        p.getPersona().setDNI(prop.getPersona().getDNI());
        p.getPersona().setNombre(prop.getPersona().getNombre());
        p.getPersona().setApellido(prop.getPersona().getApellido());
        p.getPersona().setTelefono(prop.getPersona().getTelefono());
       Call<Propietario> llamada = api.modificarPerfil(token,
               p.getPersona().getNombre(),
               p.getPersona().getApellido(),
               p.getPersona().getDNI(),
               p.getPersona().getTelefono());
       llamada.enqueue(new Callback<Propietario>() {
           @Override
           public void onResponse(Call<Propietario> call, Response<Propietario> response) {
               if(response.isSuccessful()){
                   ApiClientRetrofit.guardarPerfil(context,response.body());
                   obtenerPerfil();
                   Toast.makeText(context, "Se guardo con exito el perfil", Toast.LENGTH_LONG).show();
               }else {
                   try {
                       String errorBody = response.errorBody().string();
                       Log.d("Error Body", errorBody);
                       // Aquí puedes analizar y mostrar el cuerpo de error en el registro o en un Toast
                       Toast.makeText(context, "Error: " + errorBody, Toast.LENGTH_LONG).show();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }

           @Override
           public void onFailure(Call<Propietario> call, Throwable t) {
               Toast.makeText(context, "Ocurrio un error con el servidorl",Toast.LENGTH_LONG).show();
           }
       });

    }
}