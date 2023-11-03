package com.example.inmobiliariagarrioapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Inquilino implements Serializable {
    @SerializedName("id")
    private int Id ;
    @SerializedName("persona")
    private Persona Persona ;
    @SerializedName("direccion")
    private String Direccion;

    public Inquilino() {
    }

    public Inquilino(int id, Persona persona, String direccion) {
        Id = id;
        Persona = persona;
        Direccion = direccion;
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

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }


}
