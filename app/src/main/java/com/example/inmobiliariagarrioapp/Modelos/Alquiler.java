package com.example.inmobiliariagarrioapp.Modelos;

import java.io.Serializable;
import java.util.Date;

public class Alquiler implements Serializable {
    private int Id ;
    private Double Precio ;
    private Date Fecha_Inicio ;
    private Date Fecha_Fin ;
    private Inquilino Inquilino ;
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

    public Date getFecha_Inicio() {
        return Fecha_Inicio;
    }

    public void setFecha_Inicio(Date fecha_Inicio) {
        Fecha_Inicio = fecha_Inicio;
    }

    public Date getFecha_Fin() {
        return Fecha_Fin;
    }

    public void setFecha_Fin(Date fecha_Fin) {
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

    public Alquiler(int id, Double precio, Date fecha_Inicio, Date fecha_Fin, com.example.inmobiliariagarrioapp.Modelos.Inquilino inquilino, com.example.inmobiliariagarrioapp.Modelos.Inmueble inmueble) {
        Id = id;
        Precio = precio;
        Fecha_Inicio = fecha_Inicio;
        Fecha_Fin = fecha_Fin;
        Inquilino = inquilino;
        Inmueble = inmueble;
    }
}