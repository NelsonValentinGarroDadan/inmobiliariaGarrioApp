package com.example.inmobiliariagarrioapp.ui.MenuNav.ui.Inicio;
import android.app.Application;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class InicioViewModel extends AndroidViewModel {

    private Context context;
    //private FusedLocationProviderClient fused;
   // private MutableLiveData<MapaActual> miMapaM;
    //private LatLng ultimaUbicacion;

    public InicioViewModel(@NonNull Application application) {
        super(application);
        context = application;
        //fused = LocationServices.getFusedLocationProviderClient(context);
    }
/*

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
    public void obtenerUltimaUbicacion() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> tarea = fused.getLastLocation();
        tarea.addOnSuccessListener(context.getMainExecutor(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    ultimaUbicacion = new LatLng(location.getLatitude(),location.getLongitude());
                    obtenerMapa();

                }
            }
        });
    }

    public class MapaActual implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            LatLng Cabildo = new LatLng(-33.16017,-66.32583);
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.addMarker(new MarkerOptions()
                    .position(Cabildo)
                    .title("Inmubles La Punta")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            );
            CameraPosition camera = new CameraPosition.Builder()
                    .target(Cabildo)
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(camera);
            googleMap.animateCamera(update);
        }
    }*/

}