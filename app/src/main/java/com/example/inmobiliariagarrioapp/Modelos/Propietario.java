package com.example.inmobiliariagarrioapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Propietario implements Serializable {
    @SerializedName("id")
    private int Id;

    @SerializedName("personaId")
    private int PersonaId;

    @SerializedName("persona")
    private Persona Persona;

    @SerializedName("mail")
    private String Mail;

    @SerializedName("password")
    private String Password;

    public Propietario(int id, com.example.inmobiliariagarrioapp.Modelos.Persona persona, String mail, String password) {
        Id = id;
        Persona = persona;
        Mail = mail;
        Password = password;
    }

    public Propietario(int id, com.example.inmobiliariagarrioapp.Modelos.Persona persona) {
        Id = id;
        Persona = persona;
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

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
