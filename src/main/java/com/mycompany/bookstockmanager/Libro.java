package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class Libro {

    private String codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private int stock;
    private double precio;

    public Libro(String codigo, String titulo, String autor,
                 String categoria, int stock, double precio) {

        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.stock = stock;
        this.precio = precio;

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void mostrarDatos() {

        JOptionPane.showMessageDialog(null,
                "Código: " + codigo +
                "\nTítulo: " + titulo +
                "\nAutor: " + autor +
                "\nCategoría: " + categoria +
                "\nStock: " + stock +
                "\nPrecio: ₡" + precio);

    }

}