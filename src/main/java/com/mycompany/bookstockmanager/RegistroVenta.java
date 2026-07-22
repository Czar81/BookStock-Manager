package com.mycompany.bookstockmanager;

public class RegistroVenta {

    private String codigoVenta;
    private String cliente;
    private String fecha;
    private String[] libros;
    private int cantidadLibros;
    private double total;

    public RegistroVenta(String codigoVenta, String cliente, String fecha,
            String[] libros, int cantidadLibros, double total) {
        this.codigoVenta = codigoVenta;
        this.cliente = cliente;
        this.fecha = fecha;
        this.libros = libros;
        this.cantidadLibros = cantidadLibros;
        this.total = total;
    }

    public String getResumen() {
        String resumen = "Código de venta: " + codigoVenta + "\n"
                + "Cliente: " + cliente + "\n"
                + "Fecha: " + fecha + "\n"
                + "Libros:\n";

        for (int i = 0; i < cantidadLibros; i++) {
            resumen += "  - " + libros[i] + "\n";
        }

        resumen += "Total: $" + total;

        return resumen;
    }

    public String getCodigoVenta() {
        return codigoVenta;
    }

    public String getCliente() {
        return cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public String[] getLibros() {
        return libros;
    }

    public int getCantidadLibros() {
        return cantidadLibros;
    }

    public double getTotal() {
        return total;
    }
}