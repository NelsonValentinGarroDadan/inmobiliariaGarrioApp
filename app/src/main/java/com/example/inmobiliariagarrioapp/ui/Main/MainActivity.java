package com.example.inmobiliariagarrioapp.ui.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inmobiliariagarrioapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel mv;
    private static final int CODIGO_DE_SOLICITUD_DE_PERMISOS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mv.login(binding.etMail.getText().toString(),binding.etPassword.getText().toString());
            }
        });
        //Descomentar para localizacion
        if (!verificarPermisos()) {
            solicitarPermisos();
        }
        mv.startShakeDetection();
    }
    public void solicitarPermisos(){
        String[] permisos = new String[]{
                //Manifest.permission.ACCESS_COARSE_LOCATION,
                //Manifest.permission.ACCESS_FINE_LOCATION,
               // Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                Manifest.permission.INTERNET,
                Manifest.permission.WAKE_LOCK,
                Manifest.permission.CALL_PHONE
        };

        ActivityCompat.requestPermissions(this, permisos, CODIGO_DE_SOLICITUD_DE_PERMISOS);

    }
    private boolean verificarPermisos() {
       int permisoInternet = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        int permisoAcelerometro = ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK);
        int permisoCall = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        /* Permiso de localizacion
          int permisoCoarseLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int permisoFineLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permisoBackgroundLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION);
       return permisoCoarseLocation == PackageManager.PERMISSION_GRANTED &&
                permisoFineLocation == PackageManager.PERMISSION_GRANTED &&
                permisoBackgroundLocation == PackageManager.PERMISSION_GRANTED &&
                permisoInternet == PackageManager.PERMISSION_GRANTED &&
                permisoAcelerometro == PackageManager.PERMISSION_GRANTED &&
                permisoCall == PackageManager.PERMISSION_GRANTED;
         */
        return
                permisoInternet == PackageManager.PERMISSION_GRANTED &&
                permisoAcelerometro == PackageManager.PERMISSION_GRANTED &&
                permisoCall == PackageManager.PERMISSION_GRANTED;


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mv.stopShakeDetection();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CODIGO_DE_SOLICITUD_DE_PERMISOS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(this, "La aplicación requiere permisos para funcionar. La aplicación se cerrará.", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}