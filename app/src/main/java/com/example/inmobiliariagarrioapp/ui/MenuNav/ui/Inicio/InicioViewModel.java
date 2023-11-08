package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inicio;
import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class InicioViewModel extends AndroidViewModel {

    private Context context;
    private FusedLocationProviderClient fused;
    private MutableLiveData<MapaActual> miMapaM;
    private LatLng Inmobiliaria = new LatLng(-33.16017,-66.32583);

    public InicioViewModel(@NonNull Application application) {
        super(application);
        context = application;
        fused = LocationServices.getFusedLocationProviderClient(context);
    }
    public LiveData getMutableMapa() {
        if (miMapaM == null) {
            miMapaM = new MutableLiveData<>();
        }
        return miMapaM;
    }
    public void obtenerMapa(){
        MapaActual mp = new MapaActual();
        miMapaM.setValue(mp);
    }
    public void obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> tarea = fused.getLastLocation();
        tarea.addOnSuccessListener(context.getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    obtenerMapa();

                }
            }
        });
    }

    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions()
                    .position(Inmobiliaria)
                    .title("Inmubles La Punta")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            );
            CameraPosition camera = new CameraPosition.Builder()
                    .target(Inmobiliaria)
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(camera);
            googleMap.animateCamera(update);
        }
    }

}