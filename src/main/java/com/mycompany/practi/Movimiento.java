/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practi;

import java.time.LocalDate;


public class Movimiento {

    private int numeroTransaccion;
    private String tipo;
    private double monto;
    private String detalle;
    private LocalDate fecha;
    
    int numCuenta;  // Para retiros, depósitos, compras
    int numCuentaDepositar; // Para transferencias (cuenta destino)
    
    // Este es el que usás cuando querés asignar tú la fecha (por ejemplo en generarDatos)
    public Movimiento(int numeroTransaccion, String tipo, double monto, String detalle, LocalDate fecha) {
        this.numeroTransaccion = numeroTransaccion;
        this.tipo = tipo;
        this.monto = monto;
        this.detalle = detalle;
        this.fecha = fecha;
    }
    
    // Este es el que se usa cuando no se da fecha explícita (usa la actual)
    public Movimiento(int numeroTransaccion, String tipo, double monto, String detalle) {
        this.numeroTransaccion = numeroTransaccion;
        this.tipo = tipo;
        this.monto = monto;
        this.detalle = detalle;
        this.fecha = LocalDate.now();//Fecha actual
    }

    public String mostrar() {
        return "Transacción: " + numeroTransaccion
                + "\nTipo: " + tipo
                + "\nMonto: $" + monto
                + "\nDetalle: " + detalle
                + "\nFecha: " + fecha;
    }
    

    public int getNumeroTransaccion() {
        return numeroTransaccion;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public String getDetalle() {
        return detalle;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
