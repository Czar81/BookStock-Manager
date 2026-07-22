package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class Venta {

    private String[] historial;
    private int cantidadVentas;
    private int contadorVenta;
    private Inventario inventario;

    public Venta(Inventario inventario) {
        this.historial = new String[100]; // máximo de ventas a guardar
        this.cantidadVentas = 0;
        this.contadorVenta = 1;
        this.inventario = inventario;
    }

    public void registrarVenta() {
        String cliente = pedirCliente();
        if (cliente.isEmpty()) {
            return;
        }

        String[] rows = new String[50]; // arreglo fijo de líneas de venta
        int cantidadRows = 0;
        double totalVenta = 0;

        totalVenta = agregarLibros(rows, cantidadRows, totalVenta);
        cantidadRows = contarRows(rows);

        if (cantidadRows == 0) {
            JOptionPane.showMessageDialog(null, "No se agregó ningún libro. Venta cancelada.");
            return;
        }

        guardarVenta(cliente, rows, cantidadRows, totalVenta);
    }

    private String pedirCliente() {
        String cliente = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente:");

        // Si se selecciona calcelar cliente va a ser null
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Venta cancelada.");
            return "";
        }

        cliente = cliente.trim();
        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre del cliente no puede estar vacío.");
            return "";
        }

        return cliente;
    }

    private String pedirCodigoLibro() {
        String codigo = JOptionPane.showInputDialog(null,
                "Ingrese el código del libro (o cancele para terminar):");

        if (codigo == null) {
            return "";
        }

        return codigo.trim();
    }

    private int pedirCantidad(Libro libro) {
        String input = JOptionPane.showInputDialog(null,
                "Stock disponible: " + libro.getStock() + "\nIngrese la cantidad a vender:");

        if (input == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada. No se agregó el libro.");
            return -1;
        }

        input = input.trim();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
            return -1;
        }

        boolean esNumero = true;
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                esNumero = false;
            }
        }

        if (!esNumero) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
            return -1;
        }

        int cantidad = Integer.parseInt(input);
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.");
            return -1;
        }

        return cantidad;
    }

    private boolean validarStock(Libro libro, int cantidad) {
        if (libro.getStock() < cantidad) {
            JOptionPane.showMessageDialog(null,
                    "No hay suficiente stock disponible para \"" + libro.getTitulo() + "\".\n"
                            + "Stock disponible: " + libro.getStock());
            return false;
        }
        return true;
    }

    private double agregarLibros(String[] rows, int cantidadRows, double totalVenta) {
        String[] codigosAgregados = new String[50]; // codigos de libros ya agregados a esta venta

        while (true) {
            String codigo = pedirCodigoLibro();
            if (codigo.isEmpty()) {
                break;
            }

            Libro libro = inventario.buscarPorCodigo(codigo);
            if (libro == null) {
                JOptionPane.showMessageDialog(null, "No se encontró ningún libro con ese código.");
                continue;
            }

            boolean duplicado = false;
            for (int i = 0; i < cantidadRows; i++) {
                if (codigosAgregados[i].equals(libro.getCodigo())) {
                    duplicado = true;
                }
            }

            if (duplicado) {
                JOptionPane.showMessageDialog(null, "Ese libro ya fue agregado a esta venta.");
                continue;
            }

            int cantidad = pedirCantidad(libro);
            if (cantidad == -1) {
                continue;
            }

            if (!validarStock(libro, cantidad)) {
                continue;
            }

            if (cantidadRows >= rows.length) {
                JOptionPane.showMessageDialog(null, "No se pueden agregar más libros a esta venta.");
                break;
            }

            codigosAgregados[cantidadRows] = libro.getCodigo();
            totalVenta = procesarLinea(rows, cantidadRows, libro, cantidad, totalVenta);
            cantidadRows++;

            int continuar = JOptionPane.showConfirmDialog(null,
                    "¿Desea agregar otro libro a esta venta?",
                    "Continuar", JOptionPane.YES_NO_OPTION);
            if (continuar != JOptionPane.YES_OPTION) {
                break;
            }
        }
        return totalVenta;
    }

    private double procesarLinea(String[] rows, int cantidadRows, Libro libro,
            int cantidad, double totalVenta) {
        double subtotal = libro.getPrecio() * cantidad;

        rows[cantidadRows] = libro.getCodigo() + " | " + libro.getTitulo()
                + " x" + cantidad + " = $" + subtotal;

        inventario.restarStock(libro.getTitulo(), cantidad);

        JOptionPane.showMessageDialog(null,
                "Agregado: " + libro.getTitulo() + " x" + cantidad + " ($" + subtotal + ")");

        return totalVenta + subtotal;
    }

    private int contarRows(String[] rows) {
        int contador = 0;
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] != null) {
                contador++;
            }
        }
        return contador;
    }

    private void guardarVenta(String cliente, String[] rows, int cantidadRows, double total) {
        String codigoVenta = "VTA-" + contadorVenta;
        contadorVenta++;
        String fecha = "02/07/2026 00:00"; // Hardcodear hasta poder usar LocalDateTime o Date

        String resumen = construirResumen(codigoVenta, cliente, rows, cantidadRows, total, fecha);
        agregarHistorial(resumen);

        JOptionPane.showMessageDialog(null, "Venta registrada exitosamente.\n\n" + resumen);
    }

    private void agregarHistorial(String resumen) {
        if (cantidadVentas < historial.length) {
            historial[cantidadVentas] = resumen;
            cantidadVentas++;
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden guardar más ventas en el historial.");
        }
    }

    private String construirResumen(String codigoVenta, String cliente,
            String[] rows, int cantidadRows, double total, String fecha) {
        String resumen = "Código de venta: " + codigoVenta + "\n"
                + "Cliente: " + cliente + "\n"
                + "Fecha: " + fecha + "\n"
                + "Libros:\n";

        for (int i = 0; i < cantidadRows; i++) {
            resumen += "  - " + rows[i] + "\n";
        }

        resumen += "Total: $" + total;

        return resumen;
    }

    public String[] getHistorial() {
        return historial;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }
}