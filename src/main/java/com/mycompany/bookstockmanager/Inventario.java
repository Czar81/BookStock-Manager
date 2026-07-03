package com.mycompany.bookstockmanager;

public class Inventario {
    private Libro[] listaLibro;
    private int cantidadLibros;

    public Inventario() {
        this.listaLibro = new Libro[100]; // tamaño fijo máximo
        this.cantidadLibros = 0;
    }
    
    public boolean verificarStock(String titulo, int cantidad) {
        for (int i = 0; i < cantidadLibros; i++) {
            Libro l = listaLibro[i];
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l.getStock() >= cantidad;
            }   

        }
    
    
        return true;
    }

    public void restarStock(String titulo, int cantidadARestar) {
        for (int i = 0; i < cantidadLibros; i++) {
            Libro l = listaLibro[i];
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                int stockActual = l.getStock();
                l.setStock(stockActual - cantidadARestar);
                break;
            }
        }
    }
}
