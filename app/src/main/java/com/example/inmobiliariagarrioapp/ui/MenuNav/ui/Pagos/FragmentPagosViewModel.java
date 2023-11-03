package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Pagos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariagarrioapp.Modelos.Alquiler;
import com.example.inmobiliariagarrioapp.Modelos.Pago;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPagosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Pago>> mLista;
    public FragmentPagosViewModel(@NonNull Application application) {
        super(application);
        this.context =application;
    }
    public LiveData getMutable(){
        if(mLista == null){
            mLista = new MutableLiveData<>();
        }
        return mLista;
    }
    public void obtenerPagos(Alquiler alquiler){
        String token = "Bearer "+ ApiClientRetrofit.leerToken(context);
        ApiClientRetrofit.ApiInmobiliaria api = ApiClientRetrofit.getApiInmobiliaria();
        Call<List<Pago>> llamada = api.obtenerPagosXInmueble(token,alquiler);
        llamada.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    mLista.postValue(response.body());
                }else{
                    Log.d("Salida",response.errorBody().toString());
                    Toast.makeText(context,"Error al traer los pagos",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Toast.makeText(context,"Error :"+t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
