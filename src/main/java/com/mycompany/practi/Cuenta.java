/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practi;
import java.time.LocalDate;

public class Cuenta {
    private int numeroCuenta;
    private TipoCuenta tipo;
    private double saldo;
    private LocalDate fechaApertura;
    private Movimiento[] movimientos;
    private int totalMovimientos;
    private Cliente cliente;

    public Cuenta(int numeroCuenta, TipoCuenta tipo, double saldo,Cliente cliente) {
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.saldo = saldo;
         this.fechaApertura = LocalDate.now();
        this.movimientos = new Movimiento[50];
        this.totalMovimientos = 0;
        this.cliente = cliente;
    }
    public void agregarMovimiento(Movimiento movimiento) {
        if (totalMovimientos < movimientos.length) {
            movimientos[totalMovimientos++] = movimiento;
        }
    }
    public Movimiento[] getMovimientos() {
        return movimientos;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public TipoCuenta getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public int getTotalMovimientos() {
        return totalMovimientos;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
  }
}
    

