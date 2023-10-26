package com.example.inmobiliariagarrioapp.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.inmobiliariagarrioapp.Modelos.Alquiler;
import com.example.inmobiliariagarrioapp.Modelos.Inmueble;
import com.example.inmobiliariagarrioapp.Modelos.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public class ApiClientRetrofit {
    private static final String URLBASE = "http://192.168.0.120:5000/";
    private static ApiInmobiliaria apiInmobiliaria;
    public static ApiInmobiliaria getApiInmobiliaria(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit rtf = new Retrofit.Builder()
                .baseUrl(URLBASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiInmobiliaria = rtf.create(ApiInmobiliaria.class);
        return apiInmobiliaria;
    }
    public static boolean guardarToken(Context context , String token){
        if(token == null) return false;
        if(token.length() == 0) return false;
        SharedPreferences sp = context.getSharedPreferences("token.xml",0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token",token);
        editor.commit();
        return true;
    }
    public static String leerToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences("token.xml",0);
        return sp.getString("token","");
    }

    public static void guardarPerfil(Context context, Propietario p) {
        SharedPreferences sp = context.getSharedPreferences("perfil.xml", 0);
        SharedPreferences.Editor editor = sp.edit();

        // Convierte el objeto Propietario a una cadena JSON
        Gson gson = new Gson();
        String propietarioJson = gson.toJson(p);

        editor.putString("perfil", propietarioJson);
        editor.apply();  // Usar apply() es más eficiente que commit()
    }
    public static Propietario obtenerPerfil(Context context) {
        SharedPreferences sp = context.getSharedPreferences("perfil.xml", 0);
        String propietarioJson = sp.getString("perfil", null);

        if (propietarioJson != null) {
            Gson gson = new Gson();
            return gson.fromJson(propietarioJson, Propietario.class);
        } else {
            return null;  // Si no se encuentra ningún perfil guardado
        }
    }
    public interface ApiInmobiliaria{
        @FormUrlEncoded
        @POST("api/Propietarios/login")
        Call<String> login(@Field("Mail") String mail, @Field("Password") String password);

        @GET("api/Propietarios/perfil")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        @FormUrlEncoded
        @PATCH("api/Propietarios/update")
        Call<Propietario> modificarPerfil(@Header("Authorization") String token
                , @Field("Nombre") String Nombre
                , @Field("Apellido") String Apellido
                , @Field("DNI") long DNI
                , @Field("Telefono") long Telefono);

        @GET("api/Inmuebles/obtenerXPerfil")
        Call<List<Inmueble>> obtenerPropiedades(@Header("Authorization") String token);
        @FormUrlEncoded
        @PATCH("api/Inmuebles/cambiarDisponibilidad")
        Call<Inmueble> cambiarDisponibilidad(@Header("Authorization") String token,@Field("id") int id, @Field("estado") boolean estado);
        @GET("api/Alquileres/obtenerXPerfil")
        Call<List<Alquiler>> obtenerPropiedadesAlquiladas(@Header("Authorization") String token);
    }

}
