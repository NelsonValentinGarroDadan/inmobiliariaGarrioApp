package com.example.inmobiliariagarrioapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Persona implements Serializable {
    @SerializedName("id")
    private int Id;

    @SerializedName("dni")
    private long DNI;

    @SerializedName("nombre")
    private String Nombre;

    @SerializedName("apellido")
    private String Apellido;

    @SerializedName("telefono")
    private long Telefono;

    public Persona(int id, long DNI, String nombre, String apellido, long telefono) {
        Id = id;
        this.DNI = DNI;
        Nombre = nombre;
        Apellido = apellido;
        Telefono = telefono;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public long getDNI() {
        return DNI;
    }

    public void setDNI(long DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public long getTelefono() {
        return Telefono;
    }

    public void setTelefono(long telefono) {
        Telefono = telefono;
    }
}
