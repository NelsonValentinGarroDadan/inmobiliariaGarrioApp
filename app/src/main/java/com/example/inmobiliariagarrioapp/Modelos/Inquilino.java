package com.example.inmobiliariagarrioapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Inquilino implements Serializable {
    @SerializedName("Id")
    private int Id ;
    @SerializedName("Persona")
    private Persona Persona ;
    @SerializedName("Longitud")
    private String Longitud;
    @SerializedName("Latitud")
    private String Latitud ;

    public Inquilino() {
    }

    public Inquilino(int id, com.example.inmobiliariagarrioapp.Modelos.Persona persona, String longitud, String latitud) {
        Id = id;
        Persona = persona;
        Longitud = longitud;
        Latitud = latitud;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public com.example.inmobiliariagarrioapp.Modelos.Persona getPersona() {
        return Persona;
    }

    public void setPersona(com.example.inmobiliariagarrioapp.Modelos.Persona persona) {
        Persona = persona;
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
}
