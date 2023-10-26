package com.example.inmobiliariagarrioapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Inmueble implements Serializable {
    @SerializedName("id")
    private int Id;
    @SerializedName("longitud")
    private String Longitud;
    @SerializedName("latitud")
    private String Latitud;
    @SerializedName("cAmbientes")
    private int CAmbientes;
    @SerializedName("tipo")
    private String Tipo;
    @SerializedName("uso")
    private String Uso;
    @SerializedName("precio")
    private double Precio;
    @SerializedName("disponible")
    private boolean Disponible;
    @SerializedName("imagen")
    private String imagen;
    private Propietario propietario;

    public Inmueble(int id, String longitud, String latitud, int CAmbientes, String tipo, String uso, double precio, boolean disponible, Propietario propietario , String imagen) {
        Id = id;
        Longitud = longitud;
        Latitud = latitud;
        this.CAmbientes = CAmbientes;
        Tipo = tipo;
        Uso = uso;
        Precio = precio;
        Disponible = disponible;
       this. propietario = propietario;
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLongitud() {
        return Longitud;
    }

    public void setLongitud(String longitud) {
        Longitud = longitud;
    }

    public String getLatitud() {
        return Latitud;
    }

    public void setLatitud(String latitud) {
        Latitud = latitud;
    }

    public int getCAmbientes() {
        return CAmbientes;
    }

    public void setCAmbientes(int CAmbientes) {
        this.CAmbientes = CAmbientes;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getUso() {
        return Uso;
    }

    public void setUso(String uso) {
        Uso = uso;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public boolean isDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean disponible) {
        Disponible = disponible;
    }

    public Propietario getPropietario() {
        return this.propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
}
