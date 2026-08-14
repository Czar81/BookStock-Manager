package com.mycompany.bookstockmanager.reporte;

import com.mycompany.bookstockmanager.inventario.Inventario;
import com.mycompany.bookstockmanager.libro.Libro;
import com.mycompany.bookstockmanager.registroventa.RegistroVenta;
import com.mycompany.bookstockmanager.ubicacion.Ubicacion;
import com.mycompany.bookstockmanager.venta.Venta;
import javax.swing.JOptionPane;

public class Reporte {

    // Reporte de Inventario Completo
    public void reporteInventarioCompleto(Inventario inventario) {
        inventario.mostrarLibros();
    }

    // Reporte de Bajo Stock
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

    // Reporte de Ocupación de Estantes
    public void reporteOcupacionEstantes(Ubicacion ubicacion) {
        int libres = ubicacion.contarEspaciosLibres();
        int ocupados = ubicacion.contarEspaciosOcupados();

        String resultado = "=== OCUPACIÓN DE ESTANTES ===\n"
                + "Espacios ocupados: " + ocupados + "\n"
                + "Espacios libres: " + libres + "\n";

        JOptionPane.showMessageDialog(null, resultado);
        ubicacion.mostrarMapaEstantes();
    }

    // Reporte de Ventas
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

    // Busqueda por titulo, autor o ubicacion
    public void buscarPorTituloOAutorOUbicacion(Inventario inventario, Ubicacion ubicacion, String texto) {
        String resultado = "=== RESULTADOS DE BÚSQUEDA ('" + texto + "') ===\n";
        boolean encontrado = false;

        for (int i = 0; i < inventario.getContadorLibros(); i++) {
            Libro l = inventario.getListaLibros()[i];
            if (l != null) {
                String ubicacionLibro = ubicacion.buscarUbicacion(l.getCodigo());

                boolean coincideTitulo = l.getTitulo().toLowerCase().contains(texto.toLowerCase());
                boolean coincideAutor = l.getAutor().toLowerCase().contains(texto.toLowerCase());
                boolean coincideUbicacion = ubicacionLibro != null
                        && ubicacionLibro.toLowerCase().contains(texto.toLowerCase());

                if (coincideTitulo || coincideAutor || coincideUbicacion) {
                    resultado += "• [" + l.getCodigo() + "] " + l.getTitulo() + " - " + l.getAutor()
                            + " (Stock: " + l.getStock() + " | Ubicación: " + ubicacionLibro + ")\n";
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
