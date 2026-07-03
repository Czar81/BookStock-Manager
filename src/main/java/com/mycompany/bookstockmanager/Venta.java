package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class Venta {

    private String[] historial;
    private int cantidadVentas;
    private int contadorVenta;

    public Venta() {
        this.historial      = new String[100]; // máximo de ventas a guardar
        this.cantidadVentas = 0;
        this.contadorVenta  = 1;
    }

    public void registrarVenta() {
        String cliente = pedirCliente();
        if (cliente == null) {
            return;
        }

        String[] rows = new String[50]; // arreglo fijo de líneas de venta
        int cantidadRows = 0;
        double totalVenta = 0;

        totalVenta = agregarLibros(rows, cantidadRows, totalVenta);
        cantidadRows = contarRows(rows);

        if (cantidadRows == 0) {
            JOptionPane.showMessageDialog(null,
                    "No se agregó ningún libro. Venta cancelada.",
                    "Venta vacía", JOptionPane.WARNING_MESSAGE);
            return;
        }

        guardarVenta(cliente, rows, cantidadRows, totalVenta);
    }

    private String pedirCliente() {
        // TODO: mostrar input con JOptionPane para nombre del cliente
        // validar que no esté vacío, retornar null si cancela
        return null;
    }

    private Libro pedirLibro() {
        // TODO: mostrar input para código de libro
        // buscar en inventario
        // mostrar error si no existe, retornar null si cancela o no encuentra
        return null;
    }

    private int pedirCantidad(Libro libro) {
        // TODO: mostrar input con stock disponible del libro
        // parsear a entero, validar que sea mayor a 0
        // retornar -1 si cancela o es inválido
        return -1;
    }

    private boolean esDuplicado(String[] rows, int cantidadRows, Libro libro) {
        // TODO: recorrer rows desde 0 hasta cantidadRows
        // verificar si alguna posición contiene el código del libro
        // retornar true si ya fue agregado
        return false;
    }

    private boolean validarStock(Libro libro, int cantidad) {
        // TODO: verificar disponibilidad
        // mostrar advertencia si no hay suficiente stock
        // retornar false si no hay stock suficiente
        return false;
    }

    private double agregarLibros(String[] rows, int cantidadRows, double totalVenta) {
        while (true) {
            Libro libro = pedirLibro();
            if (libro == null) {
                break;
            }

            if (esDuplicado(rows, cantidadRows, libro)) {
                JOptionPane.showMessageDialog(null,
                        "Ese libro ya fue agregado a esta venta.",
                        "Duplicado", JOptionPane.WARNING_MESSAGE);
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
                JOptionPane.showMessageDialog(null,
                        "No se pueden agregar más libros a esta venta.",
                        "Límite alcanzado", JOptionPane.WARNING_MESSAGE);
                break;
            }

            totalVenta = procesarLinea(rows, cantidadRows, libro, cantidad, totalVenta);
            cantidadRows++;

            int continuar = JOptionPane.showConfirmDialog(null,
                    "¿Desea agregar otro libro a esta venta?",
                    "Continuar", JOptionPane.YES_NO_OPTION);
            if (continuar != JOptionPane.YES_OPTION){
                break;
            }
        }
        return totalVenta;
    }

    private double procesarLinea(String[] rows, int cantidadRows, Libro libro,
                                 int cantidad, double totalVenta) {
        // TODO: calcular subtotal con precio * cantidad
        // construir el String de la línea y guardarlo en rows[cantidadRows]
        // mostrar confirmación con JOptionPane
        // retornar totalVenta + subtotal
        return totalVenta;
    }

    private int contarRows(String[] rows) {
        int contador = 0;
        for (String r : rows) {
            if (r != null) {
                contador++;
            }
        }
        return contador;
    }

    private void guardarVenta(String cliente, String[] rows, int cantidadRows, double total) {
        String codigoVenta = String.format("VTA-%04d", contadorVenta++);
        String fecha = "02/07/2026 00:00"; // Hardcodear hasta poder usar LocalDateTime o Date

        String resumen = construirResumen(codigoVenta, cliente, rows, cantidadRows, total, fecha);
        agregarHistorial(resumen);

        JOptionPane.showMessageDialog(null,
                "Venta registrada exitosamente.\n\n" + resumen,
                "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    private void agregarHistorial(String resumen) {
        if (cantidadVentas < historial.length) {
            historial[cantidadVentas] = resumen;
            cantidadVentas++;
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pueden guardar más ventas en el historial.",
                    "Historial lleno", JOptionPane.WARNING_MESSAGE);
        }
    }

    private String construirResumen(String codigoVenta, String cliente,
                                    String[] rows, int cantidadRows, double total, String fecha) {
        // TODO: armar el resumen completo
        // incluir codigoVenta, cliente, fecha
        // recorrer rows desde 0 hasta cantidadRows y agregar cada línea
        // incluir el total
        return "";
    }

    public String[] getHistorial() {
        return historial;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }
}