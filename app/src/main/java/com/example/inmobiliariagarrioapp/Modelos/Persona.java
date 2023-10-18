package com.example.inmobiliariagarrioapp.Modelos;

import java.io.Serializable;

public class Persona implements Serializable {
    private int Id ;
    private int DNI ;
    private String Nombre ;
    private String Apellido ;
    private long Telefono ;

    public Persona(int id, int DNI, String nombre, String apellido, long telefono) {
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

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
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
