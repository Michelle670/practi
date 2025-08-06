/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
//        System.out.println("Hola tío");
//        System.out.println("Hola Michelle");

package com.mycompany.practi;

import javax.swing.JOptionPane;
import java.util.Random;
import java.time.LocalDate;

public class Practi {

    private static Cliente[] clientes = new Cliente[30];
    private static int clientesCount = 0;
    private static Cuenta[] cuentas = new Cuenta[150];
    private static int cuentasCount = 0;
    private static int numeroCuentaActual = 4710;
    private static int usuarioNum = 40;
    private static int numeroTransaccionActual = 1000;

    public static void main(String[] args) {
        while (true) {
            String opcion = JOptionPane.showInputDialog(null,
                    "Menú Principal:\n"
                    + "1. BANCO\n"
                    + "2. CLIENTES\n"
                    + "3. SALIR");

            if (opcion == null || opcion.equals("3")) {
                break;
            }

            switch (opcion) {
                case "1":
                    menuBanco();
                    break;
                case "2":
                    menuClientes();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    private static void menuBanco() {
        while (true) {
            String opcion = JOptionPane.showInputDialog(null,
                    "Menú Banco:\n"
                    + "1. Generar datos\n"
                    + "2. Mostrar clientes\n"
                    + "3. Mostrar cuentas y movimientos\n"
                    + "4. Agregar nuevo cliente\n"
                    + "5. Agregar nueva cuenta\n"
                    + "6. Buscar cliente\n"
                    + "7. Buscar cuenta\n"
                    + "8. Generar reportes\n"
                    + "9. Volver al menu principal");

            if (opcion == null || opcion.equals("9")) {
                break;
            }

            switch (opcion) {
                case "1":
                    generarDatos();
                    break;
                case "2":
                    mostrarClientes();
                    break;
                case "3":
                    mostrarCuentas();
                    break;
                case "4":
                    agregarCliente();
                    break;
                case "5":
                    agregarCuenta();
                    break;
                case "6":
                    buscarCliente();
                    break;
                case "7":
                    buscarCuenta();
                    break;
                case "8":
                    generarReportes();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    private static void generarDatos() {
        if (clientesCount > 0 || cuentasCount > 0) {
            JOptionPane.showMessageDialog(null, "Los datos ya fueron generados.");
            return;
        }

        String nombres[] = {
            "Carmen Ramirez", "Ana Solano", "Carlos Jiménez", "Sofia Torres", "Jose Soto",
            "Andrea López", "Jorge Rivera", "Daniela Vargas", "Ricardo Mora", "Laura Acuña"
        };
        String id[] = {
            "12", "13", "14", "15", "16",
            "17", "18", "19", "20", "21"
        };
        String correos[] = {
            "a1@correo.com", "a2@correo.com", "a3@correo.com", "a4@correo.com", "a5@correo.com",
            "a6@correo.com", "a7@correo.com", "a8@correo.com", "a9@correo.com", "a10@correo.com"
        };
        String telefonos[] = {
            "6000-0001", "6000-0002", "6000-0003", "6000-0004", "6000-0005",
            "6000-0006", "6000-0007", "6000-0008", "6000-0009", "6000-0010"
        };

        for (int i = 0; i < 10; i++) {
            String nombre = nombres[i];
            String usuarioBase = "";

            for (int j = 0; j < nombre.length(); j++) {
                char c = nombre.charAt(j);
                if (c == ' ') {
                    break;
                }
                usuarioBase = usuarioBase + c;
            }

            String usuario = usuarioBase.toLowerCase() + usuarioNum;
            usuarioNum++;
            Cliente nuevo = new Cliente(id[i], nombres[i], telefonos[i], correos[i], usuario);
            nuevo.setClave("******");
            clientes[clientesCount] = nuevo;
            clientesCount++;
        }
        TipoCuenta tipoPermitidos[] = {TipoCuenta.Ahorros, TipoCuenta.Corriente, TipoCuenta.Inversión, TipoCuenta.Planilla};

        for (int i = 0; i < 12; i++) {
            Cliente cliente = clientes[i % 9];

            if (cliente.getCantidadCuentas() < 5) {
                TipoCuenta tipo = tipoPermitidos[i % 4];
                double saldo = 100 + i * 10;
                Cuenta cuenta = new Cuenta(numeroCuentaActual, tipo, saldo, cliente);
                numeroCuentaActual++;
                cuentas[cuentasCount] = cuenta;
                cuentasCount++;

                cliente.agregarNumeroCuenta(cuenta.getNumeroCuenta());

                Random random = new Random();
                int cantidad = random.nextInt(0, 6);

                LocalDate fecha = LocalDate.now();
                for (int j = 0; j < cantidad; j++) {
                    String tipoMov;
                    if (j % 2 == 0) {
                        tipoMov = "Depósito";
                    } else {
                        tipoMov = "Retiro";
                    }

                    double monto = 10 + j * 5;
                    Movimiento mov = new Movimiento(numeroTransaccionActual, tipoMov, monto, "", fecha);
                    numeroTransaccionActual++;
                    cuenta.agregarMovimiento(mov);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Datos generados con éxito.");
    }

    private static void mostrarClientes() {
        if (clientesCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registradosen el sistema.");
            return;
        }

        System.out.println("\nLISTA DE CLIENTES REGISTRADOS:");
        for (int i = 0; i < clientesCount; i++) {
            System.out.println(clientes[i].mostrarClienteConClaveOculta() + "\n");
        }
    }

    private static void mostrarCuentas() {
        if (cuentasCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay cuentas registradas.");
            return;
        }

        System.out.println("\nLISTA DE CUENTAS REGISTRADAS:");
        for (int i = 0; i < cuentasCount; i++) {
            System.out.println("Número: " + cuentas[i].getNumeroCuenta()
                    + ", Tipo: " + cuentas[i].getTipo()
                    + ", Saldo: $" + cuentas[i].getSaldo() + "\n");

            Movimiento movimientos[] = cuentas[i].getMovimientos();
            int totalMov = cuentas[i].getTotalMovimientos();
            if (totalMov == 0) {
                System.out.println("  Sin movimientos.");
            } else {
                for (int j = 0; j < totalMov; j++) {
                    System.out.println("  - " + movimientos[j].mostrar());
                }
            }
            System.out.println(); // línea en blanco entre cuentas
        }
    }

    private static void agregarCliente() {
        if (clientesCount >= 30) {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más clientes (máximo 30).");
            return;
        }
        // Solicitar ID del cliente
        String id = "";
        boolean idValido = false;
        while (!idValido) {
            id = JOptionPane.showInputDialog("Ingrese el ID del cliente:");
            if (id == null) {
                return;
            }

            boolean repetido = false;
            for (int i = 0; i < clientesCount; i++) {
                if (clientes[i].getId().equals(id)) {
                    repetido = true;
                    break;
                }
            }

            if (repetido) {
                int opcion = JOptionPane.showOptionDialog(
                        null,
                        "Cliente ya agregado en el sistema.",
                        "ID Repetido",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new Object[]{"Agregar otro ID", "Cancelar"},
                        "Agregar otro ID");

                if (opcion == 0) {
                    // vuelve a pedir ID 
                } else {
                    return;
                }
            } else {
                idValido = true;
            }
        }

        // Nombre completo
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre completo:");
        if (nombre == null) {
            return;
        }

        // Teléfono con validación 
        String telefono = "";
        boolean telefonoValido = false;
        while (!telefonoValido) {
            telefono = JOptionPane.showInputDialog("Ingrese el teléfono (formato 0000-0000):");
            if (telefono == null) {
                return;
            }

            boolean valido = true;
            if (telefono.length() == 9 && telefono.charAt(4) == '-') {
                for (int i = 0; i < telefono.length(); i++) {
                    if (i != 4 && (telefono.charAt(i) < '0' || telefono.charAt(i) > '9')) {
                        valido = false;
                        break;
                    }
                }
            } else {
                valido = false;
            }

            if (valido) {
                telefonoValido = true;
            } else {
                JOptionPane.showMessageDialog(null, "Formato inválido. Debe ser 0000-0000.");
            }
        }

        // Correo con validación 
        String correo = "";
        boolean correoValido = false;
        while (!correoValido) {
            correo = JOptionPane.showInputDialog("Ingrese el correo electrónico:");
            if (correo == null) {
                return;
            }

            if (correo.contains("@") && correo.indexOf("@") < correo.indexOf(".")) {
                correoValido = true;
            } else {
                int opcion = JOptionPane.showOptionDialog(
                        null,
                        "Correo inválido. Debe tener un @ y un punto después.",
                        "Correo Inválido",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new Object[]{"Agregar otro correo", "Cancelar"},
                        "Agregar otro correo");

                if (opcion == 0) {
                    // vuelve a pedir correo
                } else {
                    return;
                }
            }
        }

        // Generar usuario automáticamente
        String primerNombre = "";
        for (int i = 0; i < nombre.length(); i++) {
            char c = nombre.charAt(i);
            if (c == ' ') {
                break;
            }
            primerNombre = primerNombre + c;
        }
        String usuario = primerNombre + usuarioNum;
        usuarioNum++;

        // Crear cliente
        Cliente nuevo = new Cliente(id, nombre, telefono, correo, usuario);
        nuevo.setClave(""); // clave vacía

        clientes[clientesCount] = nuevo;
        clientesCount++;

        // Mostrar mensaje con los datos del cliente
        String mensaje = "Cliente fue agregado exitosamente.\n"
                + "ID: " + id + "\n"
                + "Nombre: " + nombre + "\n"
                + "Teléfono: " + telefono + "\n"
                + "Correo: " + correo + "\n"
                + "Usuario: " + usuario;

        JOptionPane.showMessageDialog(null, mensaje);
    }

    private static void agregarCuenta() {
        if (cuentasCount >= 150) {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más cuentas (límite alcanzado).");
            return;
        }
        String idCliente = "";
        Cliente cliente = null;
        boolean clienteValido = false;

        while (!clienteValido) {
            idCliente = JOptionPane.showInputDialog("Ingrese el ID del cliente:");
            if (idCliente == null) {
                return;// usuario canceló
            }
            for (int i = 0; i < clientesCount; i++) {
                if (clientes[i].getId().equals(idCliente)) {
                    cliente = clientes[i];
                    clienteValido = true;
                    break;
                }

            }

            if (!clienteValido) {
                int opcion = JOptionPane.showOptionDialog(
                        null,
                        "Cliente no encontrado.",
                        "Error",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new Object[]{"Ingresar otro ID", "Cancelar"},
                        "Ingresar otro ID");

                if (opcion != 0) {
                    return;
                }
            }

            // Validar que el cliente tenga menos de 5 cuentas
            if (cliente.getCantidadCuentas() >= 5) {
                int opcion = JOptionPane.showOptionDialog(
                        null,
                        "El cliente ya tiene 5 cuentas.",
                        "Límite de cuentas",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        new Object[]{"Ingresar otro ID", "Cancelar"},
                        "Ingresar otro ID");
                if (opcion == 0) {
                    agregarCuenta(); // reinicia proceso
                }
                return;
            }
            // Seleccionar tipo de cuenta
            Object tipos[] = {"Cuenta corriente", "Ahorros", "Inversión", "Planilla"};
            int opcionTipo = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione el tipo de cuenta:",
                    "Tipo de cuenta",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    tipos,
                    tipos[0]);
            if (opcionTipo == -1) {
                return;
            }
            TipoCuenta tipo = null;

            switch (opcionTipo) {
                case 0:
                    tipo = TipoCuenta.Corriente;
                    break;
                case 1:
                    tipo = TipoCuenta.Ahorros;
                    break;
                case 2:
                    tipo = TipoCuenta.Inversión;
                    break;
                case 3:
                    tipo = TipoCuenta.Planilla;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción de tipo inválida.");
                    return;
            }
            double saldo = -1;
            boolean saldoValido = false;

            while (!saldoValido) {
                String saldoStr = JOptionPane.showInputDialog("Ingrese el saldo inicial de la cuenta:");
                if (saldoStr == null) {
                    return;
                }

                if (esNumeroValido(saldoStr)) {
                    saldo = Double.parseDouble(saldoStr);
                    if (saldo >= 0) {
                        saldoValido = true;
                    } else {
                        int op = JOptionPane.showOptionDialog(
                                null,
                                "El saldo debe ser mayor o igual a cero.",
                                "Saldo inválido",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                new Object[]{"Ingresar otro saldo", "Cancelar"},
                                "Ingresar otro saldo");
                        if (op != 0) {
                            return;
                        }
                    }
                } else {
                    int op = JOptionPane.showOptionDialog(
                            null,
                            "Ingrese un número válido.",
                            "Error",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new Object[]{"Intentar de nuevo", "Cancelar"},
                            "Intentar de nuevo");
                    if (op != 0) {
                        return;
                    }
                }
            }

            Cuenta nueva = new Cuenta(numeroCuentaActual, tipo, saldo, cliente);
            nueva.setCliente(cliente);  // Asociación directa  

            cuentas[cuentasCount] = nueva;
            cuentasCount++;

            cliente.agregarNumeroCuenta(numeroCuentaActual);
            numeroCuentaActual++;

            JOptionPane.showMessageDialog(null,
                    "Cuenta creada exitosamente.\nNúmero: " + nueva.getNumeroCuenta()
                    + "\nTipo: " + tipo + "\nSaldo: $" + saldo);
        }
    }

    private static boolean esNumeroValido(String numStr) {
        if (numStr == null) {
            return false;
        }

        if (numStr.length() == 0) {
            return false;
        }
        boolean punto = false;

        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);
            if (c == '.') {
                if (punto) {
                    return false;
                }
                punto = true;
            } else if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    private static void buscarCliente() {
        if (clientesCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes en el sistema.");
            return;
        }

        String id = JOptionPane.showInputDialog("Ingrese el ID del cliente a buscar:");
        if (id == null) {
            return;
        }

        boolean encontrado = false;
        for (int i = 0; i < clientesCount; i++) {
            if (clientes[i].getId().equals(id)) {
                Cliente c = clientes[i];
                String estadoTexto = "Inactivo";
                if (c.isEstado()) {
                    estadoTexto = "Activo";
                }

                String info = "Cliente encontrado:\n"
                        + "ID: " + c.getId() + "\n"
                        + "Nombre: " + c.getNombreCompleto() + "\n"
                        + "Teléfono: " + c.getTelefono() + "\n"
                        + "Correo: " + c.getCorreo() + "\n"
                        + "Usuario: " + c.getUsuario() + "\n"
                        + "Estado: " + estadoTexto + "\n"
                        + "Cantidad de cuentas: " + c.getCantidadCuentas();
                JOptionPane.showMessageDialog(null, info);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        }
    }

    private static void buscarCuenta() {
        if (cuentasCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay cuentas registradas.");
            return;
        }

        String numStr = JOptionPane.showInputDialog("Ingrese el número de cuenta a buscar:");
        if (numStr == null) {
            return;
        }

        boolean esNumero = true;
        for (int i = 0; i < numStr.length(); i++) {
            char c = numStr.charAt(i);
            if (c < '0' || c > '9') {
                esNumero = false;
            }
        }

        if (!esNumero) {
            JOptionPane.showMessageDialog(null, "Número inválido.");
            return;
        }

        int numeroBuscado = Integer.parseInt(numStr);
        boolean encontrado = false;

        for (int i = 0; i < cuentasCount; i++) {
            if (cuentas[i].getNumeroCuenta() == numeroBuscado) {
                Cuenta c = cuentas[i];
                Cliente cli = cuentas[i].getCliente();
                String info = "Cuenta encontrada:\n"
                        + "Número: " + c.getNumeroCuenta() + "\n"
                        + "Tipo: " + c.getTipo() + "\n"
                        + "Saldo: $" + c.getSaldo() + "\n"
                        + "Fecha de apertura: " + c.getFechaApertura() + "\n"
                        + "Movimientos: " + c.getTotalMovimientos() + "\n"
                        + "Cliente ID: " + cli.getId() + "\n"
                        + "Cliente nombre: " + cli.getNombreCompleto();

                JOptionPane.showMessageDialog(null, info);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
        }
    }

    public static void generarReportes() {
        if (clientesCount == 0 || cuentasCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes ni cuentas registradas en el sistema.");
            return;
        }
        boolean salir = false;
        // Inicialización de filtros sin filtro = null o ""
        String filtroID = null;         // null indica sin filtro
        String filtroEstado = "";       // cadena vacía indica sin filtro
        String filtroTipo = "";         // cadena vacía indica sin filtro
        String filtroSaldo = "";        // cadena vacía indica sin filtro
        double valorSaldo = 0;

        while (!salir) {
            // Mostrar valores actuales en el menú
            String mostrarID;
            if (filtroID == null) {
                mostrarID = "Todos";
            } else {
                mostrarID = filtroID;
            }

            String mostrarEstado;
            if (filtroEstado.equals("")) {
                mostrarEstado = "Todos";
            } else {
                mostrarEstado = filtroEstado;
            }

            String mostrarTipo;
            if (filtroTipo.equals("")) {
                mostrarTipo = "Todos";
            } else {
                mostrarTipo = filtroTipo;
            }

            String mostrarSaldo;
            if (filtroSaldo.equals("+")) {
                mostrarSaldo = "+" + valorSaldo;
            } else if (filtroSaldo.equals("-")) {
                mostrarSaldo = "-" + valorSaldo;
            } else {
                mostrarSaldo = "Todos";
            }

            String menu = "Filtros del Cliente\n"
                    + "Filtros de Cuenta\n"
                    + "ID del cliente: " + mostrarID + "\n"
                    + "Tipo de cuenta: " + mostrarTipo + "\n"
                    + "Estado: " + mostrarEstado + "\n"
                    + "Saldo: " + mostrarSaldo + "\n"
                    + "Seleccione una opción:\n"
                    + "1. Filtros del Cliente\n"
                    + "2. Filtros de la Cuenta\n"
                    + "3. Reporte\n"
                    + "4. Volver";

            String opcion = JOptionPane.showInputDialog(menu);

            if (opcion == null || opcion.equals("4")) {
                salir = true;
            } else if (opcion.equals("1")) {
                String idIngresado = JOptionPane.showInputDialog("Ingrese el ID del cliente (vacío para quitar filtro):");
                if (idIngresado != null) {
                    idIngresado = idIngresado.trim();
                    if (idIngresado.length() == 0) {
                        filtroID = null;  // sin filtro
                    } else {
                        boolean existe = false;
                        for (int i = 0; i < clientesCount; i++) {
                            if (clientes[i].getId().equals(idIngresado)) {
                                filtroID = idIngresado;
                                existe = true;
                                break;
                            }
                        }
                        if (!existe) {
                            JOptionPane.showMessageDialog(null, "ID no encontrado. Se quitará el filtro.");
                            filtroID = null;
                        }
                    }
                }
                String estados[] = {"Activo", "Desactivo", "Quitar filtro"};
                int estadoBTN = JOptionPane.showOptionDialog(
                        null,
                        "Seleccione el estado:",
                        "Estado",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        estados,
                        estados[2]);

                if (estadoBTN == 0) {
                    filtroEstado = "Activo";
                } else if (estadoBTN == 1) {
                    filtroEstado = "Desactivo";
                } else {
                    filtroEstado = "";
                }

            } else if (opcion.equals("2")) {
                String tipos[] = {"Cuenta corriente", "Ahorros", "Inversión", "Planilla", "Quitar filtro"};
                int tipoBTN = JOptionPane.showOptionDialog(
                        null,
                        "Seleccione el tipo de cuenta:",
                        "Tipo",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        tipos,
                        tipos[4]);

                if (tipoBTN >= 0 && tipoBTN <= 3) {
                    filtroTipo = tipos[tipoBTN];
                } else {
                    filtroTipo = "";
                }

                String textoSaldo = JOptionPane.showInputDialog("Ingrese el saldo (vacío para quitar filtro):");
                if (textoSaldo == null || textoSaldo.trim().length() == 0) {
                    valorSaldo = 0;
                    filtroSaldo = "";
                } else {
                    textoSaldo = textoSaldo.trim();
                    boolean esNumero = true;
                    int puntos = 0;
                    for (int i = 0; i < textoSaldo.length(); i++) {
                        char ch = textoSaldo.charAt(i);
                        if (ch == '.') {
                            puntos++;
                            if (puntos > 1) {
                                esNumero = false;
                                break;
                            }
                        } else if (ch < '0' || ch > '9') {
                            esNumero = false;
                            break;
                        }
                    }
                    if (!esNumero) {
                        JOptionPane.showMessageDialog(null, "Saldo inválido. Se quitará el filtro.");
                        valorSaldo = 0;
                        filtroSaldo = "";
                    } else {
                        valorSaldo = Double.parseDouble(textoSaldo);

                        String opcionesSaldo[] = {"Mayor", "Menor", "Quitar filtro"};
                        int btn = JOptionPane.showOptionDialog(
                                null,
                                "¿Cómo desea aplicar el filtro de saldo?",
                                "Filtro Saldo",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                opcionesSaldo,
                                opcionesSaldo[2]);

                        if (btn == 0) {
                            filtroSaldo = "+";
                        } else if (btn == 1) {
                            filtroSaldo = "-";
                        } else {
                            filtroSaldo = "";
                        }
                    }
                }

            } else if (opcion.equals("3")) {
                System.out.println("\n===== FILTROS APLICADOS =====");

                if (filtroID == null) {
                    System.out.println("ID del cliente: Todos");
                } else {
                    System.out.println("ID del cliente: " + filtroID);
                }

                if (filtroEstado.equals("")) {
                    System.out.println("Estado: Todos");
                } else {
                    System.out.println("Estado: " + filtroEstado);
                }

                if (filtroTipo.equals("")) {
                    System.out.println("Tipo de cuenta: Todos");
                } else {
                    System.out.println("Tipo de cuenta: " + filtroTipo);
                }

                if (filtroSaldo.equals("+")) {
                    System.out.println("Saldo: +" + valorSaldo);
                } else if (filtroSaldo.equals("-")) {
                    System.out.println("Saldo: -" + valorSaldo);
                } else {
                    System.out.println("Saldo: Todos");
                }

                System.out.println("\n===== RESULTADOS =====");

                for (int i = 0; i < cuentasCount; i++) {
                    Cuenta c = cuentas[i];
                    Cliente cli = c.getCliente();
                    if (cli != null) {
                        boolean mostrar = true;

                        if (filtroID != null && !cli.getId().equals(filtroID)) {
                            mostrar = false;
                        }
                        if (!filtroEstado.equals("")) {
                            if (filtroEstado.equals("Activo") && !cli.isEstado()) {
                                mostrar = false;
                            }
                            if (filtroEstado.equals("Desactivo") && cli.isEstado()) {
                                mostrar = false;
                            }
                        }
                        if (!filtroTipo.equals("")) {
                            TipoCuenta tipo = c.getTipo();
                            switch (tipo) {
                                case Corriente:
                                    if (!filtroTipo.equalsIgnoreCase("Corriente")) {
                                        mostrar = false;
                                    }
                                    break;
                                case Ahorros:
                                    if (!filtroTipo.equalsIgnoreCase("Ahorros")) {
                                        mostrar = false;
                                    }
                                    break;
                                case Inversión:
                                    if (!filtroTipo.equalsIgnoreCase("Inversión")) {
                                        mostrar = false;
                                    }
                                    break;
                                case Planilla:
                                    if (!filtroTipo.equalsIgnoreCase("Planilla")) {
                                        mostrar = false;
                                    }
                                    break;
                            }
                        }
                        if (filtroSaldo.equals("+") && c.getSaldo() >= valorSaldo) {
                            mostrar = false;
                        }
                        if (filtroSaldo.equals("-") && c.getSaldo() <= valorSaldo) {
                            mostrar = false;
                        }

                        if (mostrar) {
                            System.out.println("[Id cliente]: " + cli.getId()
                                    + " [Estado]: " + cli.isEstado()
                                    + " [Número de cuenta]: " + c.getNumeroCuenta()
                                    + " [Tipo de cuenta]: " + c.getTipo()
                                    + " [Saldo]: $" + c.getSaldo());
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    private static Cliente buscarClientePorID(String id) {
        for (int i = 0; i < clientesCount; i++) {
            if (clientes[i].getId().equals(id)) {
                return clientes[i];
            }
        }
        return null;
    }

    private static void menuClientes() {
        if (clientesCount == 0) {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados en el sistema.");
            return;
        }
        // Paso 1: pedir usuario
        String usuario = JOptionPane.showInputDialog("Ingrese su usuario: ");
        if (usuario == null) {
            return;
        }
        Cliente cliente = null;
        for (int i = 0; i < clientesCount; i++) {
            if (clientes[i].getUsuario().equals(usuario)) {
                cliente = clientes[i];
                break;
            }
        }

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "No hay ningún cliente con ese usuario.");
            return;
        }

        if (!cliente.isEstado()) {
            JOptionPane.showMessageDialog(null, "Este cliente está inactivo. Comuníquese con el banco.");
            return;
        }

        // Paso 2: clave vacía → pedir nueva
        if (cliente.getClave().equals("")) {
            establecerNuevaClave(cliente);
            if (cliente.getClave().equals("")) {
                JOptionPane.showMessageDialog(null, "No se estableció una clave. Acceso cancelado.");
                return;
            }
        }

        // Paso 3: Validar clave con 3 intentos
        int intentos = 0;
        boolean claveCorrecta = false;

        while (intentos < 3 && !claveCorrecta) {
            String claveIngresada = JOptionPane.showInputDialog("Ingrese su clave:");
            if (claveIngresada == null) {
                return;
            }

            if (claveIngresada.equals(cliente.getClave())) {
                claveCorrecta = true;
            } else {
                intentos++;
                if (intentos < 3) {
                    int opcion = JOptionPane.showOptionDialog(
                            null,
                            "Clave incorrecta. ¿Desea intentar de nuevo?",
                            "Clave inválida",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[]{"Sí", "No"},
                            "Sí");

                    if (opcion != 0) {
                        return; // eligió "No"
                    }
                    // Si eligió "Sí", vuelve al inicio del while
                }
            }
        }

        if (!claveCorrecta) {
            cliente.desactivar();
            JOptionPane.showMessageDialog(null, "Superó los intentos permitidos. Cliente desactivado.");
            return;
        }

        // Paso 4: Validar con tarjeta de acceso
        mostrarTarjetaYValidar(cliente);
    }

    private static void establecerNuevaClave(Cliente cliente) {
        boolean repetir = true;

        while (repetir) {
            String claveNueva = JOptionPane.showInputDialog("Ingrese una nueva clave (6-10 caracteres, al menos una letra y un número):");
            if (claveNueva == null) {
                return;
            }

            boolean tieneNumero = false;
            boolean tieneLetra = false;

            for (int i = 0; i < claveNueva.length(); i++) {
                char c = claveNueva.charAt(i);
                if (c >= '0' && c <= '9') {
                    tieneNumero = true;

                } else if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                    tieneLetra = true;
                }
            }
            // Verificar los requisitos
            if (claveNueva.length() >= 6 && claveNueva.length() <= 10 && tieneLetra && tieneNumero) {
                String confirmacion = JOptionPane.showInputDialog("Confirme la nueva clave:");
                if (confirmacion == null) {
                    return;
                }

                if (claveNueva.equals(confirmacion)) {
                    cliente.setClave(claveNueva);
                    JOptionPane.showMessageDialog(null, "Clave establecida con éxito.");
                    repetir = false;
                } else {
                    JOptionPane.showMessageDialog(null, "Las claves no coinciden.");
                    int opcion = JOptionPane.showOptionDialog(
                            null,
                            "¿Desea intentar otra vez?",
                            "Confirmar clave",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[]{"Sí", "No"},
                            "Sí");
                    if (opcion != 0) {
                        return;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "La clave no cumple con los requisitos:\n"
                        + "- Entre 6 y 10 caracteres\n"
                        + "- Al menos un número\n"
                        + "- Al menos una letra");

                int opcion = JOptionPane.showOptionDialog(
                        null,
                        "¿Desea intentar otra vez?",
                        "Clave inválida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"Sí", "No"},
                        "Sí");
                if (opcion != 0) {
                    return;
                }
            }
        }
    }

    private static void mostrarTarjetaYValidar(Cliente cliente) {
        int tarjeta[][] = cliente.getTarjetaAcceso();

        // Mostrar tarjeta en consola
        System.out.println("TARJETA DE ACCESO: ");
        char columnas[] = {'A', 'B', 'C', 'D', 'E'};
        System.out.print("   ");
        for (int c = 0; c < 5; c++) {
            System.out.print(" " + columnas[c] + " ");
        }
        System.out.println();
        for (int f = 0; f < 4; f++) {
            System.out.print(" " + (f + 1) + " ");
            for (int c = 0; c < 5; c++) {
                System.out.print("[" + tarjeta[f][c] + "]");
            }
            System.out.println();
        }
        // Generar 3 accesos aleatorios
        int filas[] = new int[3];
        int columnasAleatorias[] = new int[3];
        String posiciones[] = new String[3];

        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            filas[i] = rand.nextInt(0, 4);
            columnasAleatorias[i] = rand.nextInt(0, 5);
            posiciones[i] = (char) ('A' + columnasAleatorias[i]) + "" + (filas[i] + 1);
        }
        // Pedir al cliente los valores de esas posiciones
        String mensaje = "Ingrese los valores de las siguientes posiciones de su tarjeta (separados por guiones):\n";
        for (int i = 0; i < 3; i++) {
            mensaje += posiciones[i] + ": ";
            if (i < 2) {
                mensaje += " - ";
            }
        }

        String entrada = JOptionPane.showInputDialog(mensaje);
        if (entrada == null || entrada.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Debe ingresar los 3 valores separados por guiones.");
            return;
        }

        String valoresIngresados[] = separarValores(entrada);
        if (valoresIngresados == null) {
            JOptionPane.showMessageDialog(null, "Formato incorrecto. Debe usar guiones. Ej: 12-45-33");
            return;
        }
        boolean accesoCorrecto = true;

        for (int i = 0; i < 3; i++) {
            // Validar que cada valor solo tenga dígitos (números)
            String val = valoresIngresados[i];
            if (val == null || val.length() == 0) {
                accesoCorrecto = false;
                break;
            }

            boolean soloDigitos = true;
            for (int j = 0; j < val.length(); j++) {
                if (val.charAt(j) < '0' || val.charAt(j) > '9') {
                    soloDigitos = false;
                    break;
                }
            }
            if (!soloDigitos) {
                accesoCorrecto = false;
                break;
            }

            // Convertir a int, validamos que sea número
            int ingresado = 0;
            for (int k = 0; k < val.length(); k++) {
                ingresado = ingresado * 10 + (val.charAt(k) - '0');
            }

            int correcto = tarjeta[filas[i]][columnasAleatorias[i]];
            if (ingresado != correcto) {
                accesoCorrecto = false;
                break;
            }
        }

        if (!accesoCorrecto) {
            JOptionPane.showMessageDialog(null, "Acceso incorrecto. Verifique los datos e intente de nuevo.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Acceso validado con éxito.");

        menuClienteOpciones(cliente);
    }

    private static String[] separarValores(String entrada) {
        String resultado[] = new String[3];
        int posIni = 0;
        int posGuion;
        for (int i = 0; i < 2; i++) {
            posGuion = -1;
            for (int j = posIni; j < entrada.length(); j++) {
                if (entrada.charAt(j) == '-') {
                    posGuion = j;
                    break;
                }
            }
            if (posGuion == -1) {
                return null;
            }
            resultado[i] = entrada.substring(posIni, posGuion).trim();
            posIni = posGuion + 1;
        }
        resultado[2] = entrada.substring(posIni).trim();
        return resultado;
    }

    private static void menuClienteOpciones(Cliente cliente) {
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "MENÚ CLIENTE\n"
                    + "1. REALIZAR TRANSACCIONES\n"
                    + "2. MIS CUENTAS\n"
                    + "3. ACTUALIZAR DATOS\n"
                    + "4. SALIR");

            if (opcion == null || opcion.equals("4")) {
                return; // salir del menú
            }

            switch (opcion) {
                case "1":
                    realizarTransacciones(cliente);
                    break;
                case "2":
                    mostrarMisCuentas(cliente);
                    break;
                case "3":
                    actualizarDatosCliente(cliente);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

    private static void realizarTransacciones(Cliente cliente) {
        int numeros[] = cliente.getNumerosCuentas();
        int total = cliente.getCantidadCuentas();

        if (total == 0) {
            JOptionPane.showMessageDialog(null, "Este cliente no tiene cuentas.");
            return;
        }
        // Mostrar cuentas disponibles
        String lista = "Seleccione una cuenta:\n";
        for (int i = 0; i < total; i++) {
            Cuenta cuent = null;
            for (int j = 0; j < cuentasCount; j++) {
                if (cuentas[j].getNumeroCuenta() == numeros[i]) {
                    cuent = cuentas[j];
                    break;
                }
            }
            if (cuent != null) {
                lista += (i + 1) + " - " + cuent.getTipo() + ": ₡" + cuent.getSaldo() + "\n";
            }
        }
        String opcionCuentaStr = JOptionPane.showInputDialog(lista);
        if (opcionCuentaStr == null) {
            return;
        }
        int indiceCuenta;
        if (opcionCuentaStr.length() == 1) {
            indiceCuenta = opcionCuentaStr.charAt(0) - '0';
        } else {
            indiceCuenta = -1;
        }

        if (indiceCuenta < 1 || indiceCuenta > total) {
            JOptionPane.showMessageDialog(null, "Opción inválida.");
            return;
        }
        int numeroSeleccionado = numeros[indiceCuenta - 1];

        Cuenta cuentaSeleccionada = null;
        for (int j = 0; j < cuentasCount; j++) {
            if (cuentas[j].getNumeroCuenta() == numeroSeleccionado) {
                cuentaSeleccionada = cuentas[j];
                break;
            }
        }

        if (cuentaSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
            return;
        }
        // Elegir tipo de transacción
        Object opcionesTransaccion[] = {"Depósito", "Retiro"};
        int opcion = JOptionPane.showOptionDialog(
                null,
                "Seleccione el tipo de transacción:",
                "Tipo de transacción",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcionesTransaccion,
                opcionesTransaccion[0]);

        if (opcion == -1) {
            return;
        }
        // Pedir monto
        String montoStr = JOptionPane.showInputDialog("Ingrese el monto:");
        if (montoStr == null) {
            return;
        }

        double monto = 0;
        boolean valido = true;

        for (int i = 0; i < montoStr.length(); i++) {
            char c = montoStr.charAt(i);
            if ((c < '0' || c > '9') && c != '.') {
                valido = false;
                break;
            }
        }

        if (!valido || montoStr.length() == 0) {
            JOptionPane.showMessageDialog(null, "Monto inválido.");
            return;
        }
        monto = Double.parseDouble(montoStr);
        if (monto <= 0) {
            JOptionPane.showMessageDialog(null, "El monto debe ser mayor que cero.");
            return;
        }
        // Verificar saldo si es retiro
        if (opcion == 1 && monto > cuentaSeleccionada.getSaldo()) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para retiro.");
            return;
        }
        String tipo;
        if (opcion == 0) {
            tipo = "Depósito";
        } else {
            tipo = "Retiro";
        }

        double saldoActual = cuentaSeleccionada.getSaldo();
        double nuevoSaldo;

        if (opcion == 0) {
            nuevoSaldo = saldoActual + monto;
        } else {
            nuevoSaldo = saldoActual - monto;
        }

        cuentaSeleccionada.setSaldo(nuevoSaldo);

        Movimiento nuevo = new Movimiento(1, tipo, monto, "Realizado por menú cliente");
        cuentaSeleccionada.agregarMovimiento(nuevo);

        JOptionPane.showMessageDialog(null, tipo + " exitoso. Nuevo saldo: ₡" + nuevoSaldo);
    }

    private static void mostrarMisCuentas(Cliente cliente) {
        int numeros[] = cliente.getNumerosCuentas();
        int total = cliente.getCantidadCuentas();

        if (total == 0) {
            System.out.println("Este cliente no tiene cuentas registradas.");
            return;
        }

        System.out.println("\nMIS CUENTAS:");
        for (int i = 0; i < total; i++) {
            int numero = numeros[i];

            // Buscar cuenta en el arreglo general de cuentas
            Cuenta cuenta = null;
            for (int j = 0; j < cuentasCount; j++) {
                if (cuentas[j].getNumeroCuenta() == numero) {
                    cuenta = cuentas[j];
                    break;
                }
            }

            if (cuenta != null) {
                System.out.println("Número: " + cuenta.getNumeroCuenta()
                        + ", Tipo: " + cuenta.getTipo()
                        + ", Saldo: $" + cuenta.getSaldo());

                Movimiento movimientos[] = cuenta.getMovimientos();
                int totalMov = cuenta.getTotalMovimientos();

                if (totalMov == 0) {
                    System.out.println("  Sin movimientos.");
                } else {
                    for (int k = 0; k < totalMov; k++) {
                        System.out.println("  " + (k + 1) + ". " + movimientos[k].mostrar());
                    }
                }

                System.out.println(); // Línea separadora entre cuentas
            }
        }
    }

    private static void actualizarDatosCliente(Cliente cliente) {
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "¿Qué desea actualizar?\n"
                    + "1. Nombre completo\n"
                    + "2. Teléfono\n"
                    + "3. Correo electrónico\n"
                    + "4. Salir");

            if (opcion == null || opcion.equals("4")) {
                return; // salir
            }

            switch (opcion) {
                case "1":
                    String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre completo: ");
                    if (nuevoNombre != null) {
                        boolean tieneLetras = false;
                        for (int i = 0; i < nuevoNombre.length(); i++) {
                            char c = nuevoNombre.charAt(i);
                            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == ' ') {
                                tieneLetras = true;
                            }
                        }
                        if (tieneLetras) {
                            cliente.setNombreCompleto(nuevoNombre);
                            JOptionPane.showMessageDialog(null, "Nombre actualizado.");
                        } else {
                            JOptionPane.showMessageDialog(null, "El nombre debe contener letras.");
                        }
                    }
                    break;

                case "2":
                    boolean telefonoValido = false;
                    while (!telefonoValido) {
                        String nuevoTel = JOptionPane.showInputDialog("Ingrese el nuevo teléfono (formato 0000-0000):");
                        if (nuevoTel == null) {
                            return;
                        }

                        if (nuevoTel.length() == 9 && nuevoTel.charAt(4) == '-') {
                            boolean esNumero = true;
                            for (int i = 0; i < 9; i++) {
                                if (i != 4) {
                                    char c = nuevoTel.charAt(i);
                                    if (c < '0' || c > '9') {
                                        esNumero = false;
                                    }
                                }
                            }
                            if (esNumero) {
                                telefonoValido = true;
                                cliente.setTelefono(nuevoTel);
                                JOptionPane.showMessageDialog(null, "Teléfono actualizado.");
                            } else {
                                JOptionPane.showMessageDialog(null, "El teléfono debe tener solo números y un guion.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Formato incorrecto. Use 0000-0000.");
                        }
                    }
                    break;

                case "3":
                    boolean correoValido = false;
                    while (!correoValido) {
                        String nuevoCorreo = JOptionPane.showInputDialog("Ingrese el nuevo correo electrónico:");
                        if (nuevoCorreo == null) {
                            return;
                        }

                        int posiArroba = -1;
                        int posPunto = -1;

                        for (int i = 0; i < nuevoCorreo.length(); i++) {
                            if (nuevoCorreo.charAt(i) == '@') {
                                posiArroba = i;
                            }
                            if (nuevoCorreo.charAt(i) == '.') {
                                posPunto = i;
                            }
                        }

                        if (posiArroba != -1 && posPunto != -1 && posPunto > posiArroba) {
                            correoValido = true;
                            cliente.setCorreo(nuevoCorreo);
                            JOptionPane.showMessageDialog(null, "Correo actualizado.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Correo inválido. Asegúrese de incluir '@' y un '.' después.");
                        }
                    }
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }
}//final de todo









