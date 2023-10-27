package com.example.inmobiliariagarrioapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Inquilino implements Serializable {
    @SerializedName("id")
    private int Id ;
    @SerializedName("persona")
    private Persona Persona ;
    @SerializedName("longitud")
    private String Longitud;
    @SerializedName("latitud")
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
