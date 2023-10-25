package com.example.inmobiliariagarrioapp.ui.Main;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariagarrioapp.Modelos.Propietario;
import com.example.inmobiliariagarrioapp.request.ApiClient;
import com.example.inmobiliariagarrioapp.request.ApiClientRetrofit;
import com.example.inmobiliariagarrioapp.ui.MenuNav.MenuActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private ApiClientRetrofit.ApiInmobiliaria api;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private  SensorEventListener sensorListener;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        this.api = ApiClientRetrofit.getApiInmobiliaria();

        sensorManager = (SensorManager) application.getSystemService(Application.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                if (x > 12 || y > 12 || z > 12) {
                    makePhoneCall();
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };
    }
    public void login(String mail,String password){

        Call<String> llamada = api.login(mail,password);
        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if(ApiClientRetrofit.guardarToken(context,response.body())){
                        obtenerPerfil();
                        Intent intent = new Intent(context, MenuActivity.class);
                        intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }else{
                        Toast.makeText(context, "Mail o Contraseña incorrecta",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context, "Mail o Contraseña incorrecta",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void obtenerPerfil(){
        String token ="Bearer "+ ApiClientRetrofit.leerToken(context);
        Call<com.example.inmobiliariagarrioapp.Modelos.Propietario> llamada = api.obtenerPerfil(token);
        llamada.enqueue(new Callback<com.example.inmobiliariagarrioapp.Modelos.Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    Propietario p = response.body();
                    ApiClientRetrofit.guardarPerfil(context,p);

                }else{
                    Toast.makeText(context, "El token expiro",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


    }
    public void startShakeDetection() {
        sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopShakeDetection() {
        sensorManager.unregisterListener(sensorListener);
    }

    private void makePhoneCall() {
        Intent llamadaIntent = new Intent(Intent.ACTION_DIAL);
        llamadaIntent.setData(Uri.parse("tel:2664910392"));
        llamadaIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(llamadaIntent);
    }
}
