package com.example.inmobiliariagarrioapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Alquiler implements Serializable {
    @SerializedName("id")
    private int Id ;
    @SerializedName("precio")
    private Double Precio ;
    @SerializedName("fecha_Inicio")
    private String Fecha_Inicio ;
    @SerializedName("fecha_Fin")
    private String Fecha_Fin ;
    @SerializedName("inquilino")
    private Inquilino Inquilino ;
    @SerializedName("inmueble")
    private Inmueble Inmueble ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public String getFecha_Inicio() {
        return Fecha_Inicio;
    }

    public void setFecha_Inicio(String fecha_Inicio) {
        Fecha_Inicio = fecha_Inicio;
    }

    public String getFecha_Fin() {
        return Fecha_Fin;
    }

    public void setFecha_Fin(String fecha_Fin) {
        Fecha_Fin = fecha_Fin;
    }

    public com.example.inmobiliariagarrioapp.Modelos.Inquilino getInquilino() {
        return Inquilino;
    }

    public void setInquilino(com.example.inmobiliariagarrioapp.Modelos.Inquilino inquilino) {
        Inquilino = inquilino;
    }

    public com.example.inmobiliariagarrioapp.Modelos.Inmueble getInmueble() {
        return Inmueble;
    }

    public void setInmueble(com.example.inmobiliariagarrioapp.Modelos.Inmueble inmueble) {
        Inmueble = inmueble;
    }

    public Alquiler() {
    }

    public Alquiler(int id, Double precio, String fecha_Inicio, String fecha_Fin, com.example.inmobiliariagarrioapp.Modelos.Inquilino inquilino, com.example.inmobiliariagarrioapp.Modelos.Inmueble inmueble) {
        Id = id;
        Precio = precio;
        Fecha_Inicio = fecha_Inicio;
        Fecha_Fin = fecha_Fin;
        Inquilino = inquilino;
        Inmueble = inmueble;
    }
}
