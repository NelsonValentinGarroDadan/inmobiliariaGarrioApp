package com.example.inmobiliariagarrioapp.Modelos;

import java.io.Serializable;

public class Inmueble implements Serializable {
    private int Id ;
    private String Longitud ;
    private String Latitud ;
    private int CAmbientes ;
    private String Tipo ;
    private String Uso ;
    private Double Precio ;
    private boolean Disponible ;
   private Propietario Propietario ;

    public Inmueble(int id, String longitud, String latitud, int CAmbientes, String tipo, String uso, Double precio, boolean disponible, com.example.inmobiliariagarrioapp.Modelos.Propietario propietario) {
        Id = id;
        Longitud = longitud;
        Latitud = latitud;
        this.CAmbientes = CAmbientes;
        Tipo = tipo;
        Uso = uso;
        Precio = precio;
        Disponible = disponible;
        Propietario = propietario;
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

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    public boolean isDisponible() {
        return Disponible;
    }

    public void setDisponible(boolean disponible) {
        Disponible = disponible;
    }

    public com.example.inmobiliariagarrioapp.Modelos.Propietario getPropietario() {
        return Propietario;
    }

    public void setPropietario(com.example.inmobiliariagarrioapp.Modelos.Propietario propietario) {
        Propietario = propietario;
    }
}
