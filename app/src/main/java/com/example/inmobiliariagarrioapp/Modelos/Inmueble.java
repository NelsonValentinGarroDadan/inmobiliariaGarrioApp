package com.example.inmobiliariagarrioapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import okhttp3.MultipartBody;

public class Inmueble implements Serializable {
    @SerializedName("id")
    private int Id;
    @SerializedName("direccion")
    private String Direccion;
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
    private MultipartBody.Part image;

    public MultipartBody.Part getImage() {
        return image;
    }

    public void setImage(MultipartBody.Part image) {
        this.image = image;
    }

    public Inmueble(int id, String direccion, int CAmbientes, String tipo, String uso, double precio, boolean disponible, Propietario propietario , String imagen) {
        Id = id;
        Direccion = direccion;
        this.CAmbientes = CAmbientes;
        Tipo = tipo;
        Uso = uso;
        Precio = precio;
        Disponible = disponible;
       this. propietario = propietario;
        this.imagen = imagen;
    }

    public Inmueble() {

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

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
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
