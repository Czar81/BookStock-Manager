package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class reporte {
// 1. Reporte de Inventario Completo
    public void reporteInventarioCompleto(Inventario inventario) {
        inventario.mostrarLibros();
    }

// 2. Reporte de Bajo Stock
    public void reporteBajoStock(Inventario inventario, int limite) {
            String resultado = "=== LIBROS CON BAJO STOCK (<= " + limite + ") ===\n";
            boolean hayBajoStock = false;

            // Recorremos el arreglo de libros del inventario
            for (int i = 0; i < inventario.getContadorLibros(); i++) {
                Libro l = inventario.getListaLibros()[i];
                if (l != null && l.getStock() <= limite) {
                    resultado += "- " + l.getTitulo() + " | Stock: " + l.getStock() + " | Código: " + l.getCodigo() + "\n";
                    hayBajoStock = true;
                }
            }
           if (!hayBajoStock) {
            JOptionPane.showMessageDialog(null, "No hay libros con un stock igual o menor a " + limite + ".");
        } else {
            JOptionPane.showMessageDialog(null, resultado);
        }

for (int i = 0; i < inventario.getContadorLibros(); i++) {
            Libro l = inventario.getListaLibros()[i];
            if (l != null) {
                boolean coincideTitulo = l.getTitulo().toLowerCase().contains(texto.toLowerCase());
                boolean coincideAutor = l.getAutor().toLowerCase().contains(texto.toLowerCase());

                if (coincideTitulo || coincideAutor) {
                    resultado += "• [" + l.getCodigo() + "] " + l.getTitulo() + " - " + l.getAutor() + " (Stock: " + l.getStock() + ")\n";
                    encontrado = true;
                }
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, resultado);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias para: " + texto);
        }
    }
public void reporteOcupacionEstantes() {
        JOptionPane.showMessageDialog(null, "Reporte de estantes (Integración pendiente).");
    }

    public void reporteVentas() {
        JOptionPane.showMessageDialog(null, "Reporte de ventas (Integración pendiente).");
    }
    
    public void buscarPorTituloOAutor(Inventario inventario, String texto) {
    String resultado = "=== RESULTADOS DE BÚSQUEDA ('" + texto + "') ===\n";
    boolean encontrado = false;

    for (int i = 0; i < inventario.getContadorLibros(); i++) {
        Libro l = inventario.getListaLibros()[i];
        if (l != null) {
            boolean coincideTitulo = l.getTitulo().toLowerCase().contains(texto.toLowerCase());
            boolean coincideAutor = l.getAutor().toLowerCase().contains(texto.toLowerCase());

            if (coincideTitulo || coincideAutor) {
                resultado += "• [" + l.getCodigo() + "] " + l.getTitulo() + " - " + l.getAutor() + " (Stock: " + l.getStock() + ")\n";
                encontrado = true;
            }
        }
    }

    if (encontrado) {
        JOptionPane.showMessageDialog(null, resultado);
    } else {
        JOptionPane.showMessageDialog(null, "No se encontraron coincidencias para: " + texto);
    }
}
}