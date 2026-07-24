package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class Reporte {

    // 1. Reporte de Inventario Completo
    public void reporteInventarioCompleto(Inventario inventario) {
        inventario.mostrarLibros();
    }

    // 2. Reporte de Bajo Stock
    public void reporteBajoStock(Inventario inventario, int limite) {
        String resultado = "=== LIBROS CON BAJO STOCK (<= " + limite + ") ===\n";
        boolean hayBajoStock = false;

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
    }

    // 3. Reporte de Ocupación de Estantes
    public void reporteOcupacionEstantes(Ubicacion ubicacion) {
        int libres = ubicacion.contarEspaciosLibres();
        int ocupados = ubicacion.contarEspaciosOcupados();

        String resultado = "=== OCUPACIÓN DE ESTANTES ===\n"
                + "Espacios ocupados: " + ocupados + "\n"
                + "Espacios libres: " + libres + "\n";

        JOptionPane.showMessageDialog(null, resultado);
        ubicacion.mostrarMapaEstantes();
    }

    // 4. Reporte de Ventas
    public void reporteVentas(Venta venta) {
        if (venta.getCantidadVentas() == 0) {
            JOptionPane.showMessageDialog(null, "No se han registrado ventas todavía.");
            return;
        }

        String resultado = "=== HISTORIAL DE VENTAS ===\n\n";
        double totalGeneral = 0;

        for (int i = 0; i < venta.getCantidadVentas(); i++) {
            RegistroVenta registro = venta.getHistorial()[i];
            resultado += registro.getResumen() + "\n\n";
            totalGeneral += registro.getTotal();
        }

        resultado += "Total general: $" + totalGeneral;

        JOptionPane.showMessageDialog(null, resultado);
    }

    // 5. Búsqueda por título o autor
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
