package com.example.inmobiliariagarrioapp.ui.Main;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliariagarrioapp.modelo.Propietario;
import com.example.inmobiliariagarrioapp.request.ApiClient;
import com.example.inmobiliariagarrioapp.ui.MenuNav.MenuActivity;


public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient api;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private  SensorEventListener sensorListener;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        this.api = ApiClient.getApi();

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
        if(mail.length()<1 || password.length()<1){
            Toast.makeText(context, "Ambos campos son obligatorios",Toast.LENGTH_LONG).show();

        }else{
            Propietario user= api.login(mail,password);
            if(user == null){
                Toast.makeText(context, "Mail o Contraseña incorrecta",Toast.LENGTH_LONG).show();
            }else{
                Intent intent = new Intent(context, MenuActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
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
