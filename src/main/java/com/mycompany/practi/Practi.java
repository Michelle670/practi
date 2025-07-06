/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practi;

import javax.swing.JOptionPane;

public class Practi {

    public static void main(String[] args) {
//        System.out.println("Hola tío");
//        System.out.println("Hola Michelle");

        Usuarioo usuario1 = new Usuarioo("saratg", "1234");
        Usuarioo usuario2 = new Usuarioo("andresbf", "5678");

        Clientee cliente1 = new Clientee("Sara Torres", usuario1);
        Clientee cliente2 = new Clientee("Andrés Brenes", usuario2);

        String usuarioIngresado = JOptionPane.showInputDialog("Ingrese su usuario:");
        String claveIngresada = JOptionPane.showInputDialog("Ingrese su clave:");

        if (cliente1.getUsuario().validarLogin(usuarioIngresado, claveIngresada)) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + cliente1.getNombre());
        } else if (cliente2.getUsuario().validarLogin(usuarioIngresado, claveIngresada)) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + cliente2.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o clave incorrectos.");
        }
    }
}
