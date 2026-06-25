package com.mycompany.bookstockmanager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {

    private String codigoVenta;
    private String nombreCliente;
    private Libro libro;
    private int cantidad;
    private double total;
    private LocalDateTime fechaHora;

    public Venta(String codigoVenta, String nombreCliente, Libro libro, int cantidad) {
        this.codigoVenta = codigoVenta;
        this.nombreCliente = nombreCliente;
        this.libro = libro;
        this.cantidad = cantidad;
        this.total = libro.getPrecio() * cantidad;
        this.fechaHora = LocalDateTime.now();
    }

    public String getCodigo() {
        return codigoVenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public Libro getLibro() {
        return libro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getFechaFormateada() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fechaHora.format(fmt);
    }

    public String toResumen() {
        return "Código:   " + codigoVenta
                + "\nCliente:  " + nombreCliente
                + "\nLibro:    " + libro.getTitulo()
                + "\nCantidad: " + cantidad
                + "\nTotal:    $" + String.format("%.2f", total)
                + "\nFecha:    " + getFechaFormateada();
    }
}