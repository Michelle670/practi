/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practi;
import java.util.Random;

public class Cliente {
    private String id;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String usuario;
    private String clave;
    private boolean estado;
    private int intentosFallidos; 
    private int numerosCuentas[];
    private int cantidadCuentas;
    private int tarjetaAcceso[][];

    public Cliente(String id, String nombreCompleto, String telefono, String correo, String usuario) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario = usuario;
        this.clave = "";
        this.estado = true;
        this.intentosFallidos = 0;
        this.numerosCuentas = new int[5];
        this.cantidadCuentas = 0;
        this.tarjetaAcceso = new int[4][5];
        generarTarjetaAcceso();
    }
    private void generarTarjetaAcceso() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                Random random = new Random();
                tarjetaAcceso[i][j] = random.nextInt(81) + 10;
            }
        }
    }
    public void agregarNumeroCuenta(int numero) {
        if (cantidadCuentas < 5) {
            numerosCuentas[cantidadCuentas++] = numero;
        }
    }
    public String mostrarClienteConClaveOculta() {
        String oculta = "";
        for (int i = 0; i < clave.length(); i++) {
            oculta += "*";
        }
        String estadoTexto = "Inactivo";
        if (estado) {
            estadoTexto = "Activo";
        }
        return "ID: " + id
                + "\nNombre: " + nombreCompleto
                + "\nTeléfono: " + telefono
                + "\nCorreo: " + correo
                + "\nUsuario: " + usuario
                + "\nClave: " + oculta
                + "\nEstado: " + (estadoTexto);
    }

    public String getId() {
        return id;
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean isEstado() {
        return estado;
    }
    public void activar() {
        estado = true;
    }

    public void desactivar() {
        estado = false;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }
    
    public void aumentarIntentosFallidos(){
        intentosFallidos++;
    }
    
    public void resetearIntentosFallidos(){
        intentosFallidos = 0;
    }
    

    public int[] getNumerosCuentas() {
        return numerosCuentas;
    }

    public int getCantidadCuentas() {
        return cantidadCuentas;
    }

    public int[][] getTarjetaAcceso() {
        return tarjetaAcceso;
    }

}

