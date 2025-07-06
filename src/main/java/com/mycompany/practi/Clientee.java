/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practi;

public class Clientee {
    private String nombreCompleto;
    private Usuarioo usuario;

    public Clientee(String nombreCompleto, Usuarioo usuario) {
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombreCompleto;
    }

    public Usuarioo getUsuario() {
        return usuario;
    }   
    public String obtenerDetalles() {
        return "Nombre del cliente: " + " " + nombreCompleto + " " + usuario.obtenerDetalles();
    }
    
}
