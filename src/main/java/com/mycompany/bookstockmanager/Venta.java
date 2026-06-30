package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Venta {

    private ArrayList<String> historial;
    private Inventario inventario;
    private int contadorVenta;

    public Venta(Inventario inventario) {
        this.inventario    = inventario;
        this.historial     = new ArrayList<>();
        this.contadorVenta = 1;
    }

    public void registrarVenta() {
        String cliente = pedirCliente();
        if (cliente == null) return;

        ArrayList<String> lineas = new ArrayList<>();
        double totalVenta = 0;

        totalVenta = agregarLibros(lineas, totalVenta);

        if (lineas.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No se agregó ningún libro. Venta cancelada.",
                    "Venta vacía", JOptionPane.WARNING_MESSAGE);
            return;
        }

        guardarVenta(cliente, lineas, totalVenta);
    }

    private String pedirCliente() {
        // TODO: mostrar input con JOptionPane para nombre del cliente
        // validar que no esté vacío, retornar null si cancela
        return null;
    }

    private Libro pedirLibro() {
        // TODO: mostrar input para código de libro
        // buscar en inventario con buscarPorCodigo()
        // mostrar error si no existe, retornar null si cancela o no encuentra
        return null;
    }

    private int pedirCantidad(Libro libro) {
        // TODO: mostrar input con stock disponible del libro
        // parsear a entero, validar que sea mayor a 0
        // retornar -1 si cancela o es inválido
        return -1;
    }

    private boolean esDuplicado(ArrayList<String> lineas, Libro libro) {
        // TODO: recorrer lineas y verificar si alguna contiene el código del libro
        // retornar true si ya fue agregado
        return false;
    }

    private boolean validarStock(Libro libro, int cantidad) {
        // TODO: usar inventario.hayStock() para verificar disponibilidad
        // mostrar advertencia si no hay suficiente stock
        // retornar false si no hay stock suficiente
        return false;
    }

    private double agregarLibros(ArrayList<String> lineas, double totalVenta) {
        while (true) {
            Libro libro = pedirLibro();
            if (libro == null) break;

            if (esDuplicado(lineas, libro)) {
                JOptionPane.showMessageDialog(null,
                        "Ese libro ya fue agregado a esta venta.",
                        "Duplicado", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            int cantidad = pedirCantidad(libro);
            if (cantidad == -1) continue;

            if (!validarStock(libro, cantidad)) continue;

            totalVenta = procesarLinea(lineas, libro, cantidad, totalVenta);

            int continuar = JOptionPane.showConfirmDialog(null,
                    "¿Desea agregar otro libro a esta venta?",
                    "Continuar", JOptionPane.YES_NO_OPTION);
            if (continuar != JOptionPane.YES_OPTION) break;
        }
        return totalVenta;
    }

    private double procesarLinea(ArrayList<String> lineas, Libro libro,
                                 int cantidad, double totalVenta) {
        // TODO: calcular subtotal con precio * cantidad
        // llamar inventario.descontarStock()
        // construir el String de la línea y agregarlo a lineas
        // mostrar confirmación con JOptionPane
        // retornar totalVenta + subtotal
        return totalVenta;
    }

    private void guardarVenta(String cliente, ArrayList<String> lineas, double total) {
        String codigoVenta = String.format("VTA-%04d", contadorVenta++);
        String fecha = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        String resumen = construirResumen(codigoVenta, cliente, lineas, total, fecha);
        historial.add(resumen);

        JOptionPane.showMessageDialog(null,
                "Venta registrada exitosamente.\n\n" + resumen,
                "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    private String construirResumen(String codigoVenta, String cliente,
                                    ArrayList<String> lineas, double total, String fecha) {
        // TODO: usar StringBuilder para armar el resumen completo
        // incluir codigoVenta, cliente, fecha, cada línea de lineas y el total
        return "";
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }
}