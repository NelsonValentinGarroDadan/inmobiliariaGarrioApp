package com.example.inmobiliariagarrioapp.Modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Pago implements Serializable {
    @SerializedName("Id")
    private int Id;
    @SerializedName("NroPago")
    private int NroPago ;
    @SerializedName("Alquiler")
    private Alquiler Alquiler ;
    @SerializedName("Fecha")
    private Date Fecha;
    @SerializedName("Importe")
    private Double Importe ;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getNroPago() {
        return NroPago;
    }

    public void setNroPago(int nroPago) {
        NroPago = nroPago;
    }

    public com.example.inmobiliariagarrioapp.Modelos.Alquiler getAlquiler() {
        return Alquiler;
    }

    public void setAlquiler(com.example.inmobiliariagarrioapp.Modelos.Alquiler alquiler) {
        Alquiler = alquiler;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public Double getImporte() {
        return Importe;
    }

    public void setImporte(Double importe) {
        Importe = importe;
    }

    public Pago(int id, int nroPago, com.example.inmobiliariagarrioapp.Modelos.Alquiler alquiler, Date fecha, Double importe) {
        Id = id;
        NroPago = nroPago;
        Alquiler = alquiler;
        Fecha = fecha;
        Importe = importe;
    }
}
