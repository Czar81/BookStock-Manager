package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class MenuPrincipal {

   private Inventario inventario = new Inventario();

    public void MostrarMenu() {

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(null,
                    "=== SISTEMA DE GESTIÓN DE INVENTARIO Y VENTAS ===\n"
                            + "1. Gestión de estantes\n"
                            + "2. Gestión de libros\n"
                            + "3. Gestión de ventas\n"
                            + "4. Reportes y consultas\n"
                            + "5. Salir\n");

            if (opcion == null) {
                // El usuario presionó "Cancelar"
                break;
            }

            switch (opcion) {
                case "1":
                    menuEstantes();
                    break;
                case "2":
                    menuLibros();
                    break;
                case "3":
                    menuVentas();
                    break;
                case "4":
                    menuReportes();
                    break;
                case "5":
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
            }

        } while (!opcion.equals("5"));
    }

    // SUBMENUS

    private void menuEstantes() {
        // TODO: Implementar submenú de estantes
        JOptionPane.showMessageDialog(null, "Mostrando menú de gestión de estantes...");
    }

   private void menuLibros() {
    String subOpcion;

    do {
        subOpcion = JOptionPane.showInputDialog(null,
                "=== GESTIÓN DE LIBROS ===\n"
                        + "1. Agregar nuevo libro\n"
                        + "2. Mostrar todos los libros\n"
                        + "3. Buscar libro por código\n"
                        + "4. Eliminar libro\n"
                        + "5. Volver al menú principal\n");

        if (subOpcion == null) {
            break; // Si da clic en cancelar, sale al menú principal
        }

        switch (subOpcion) {
            case "1":
                // Pedir datos del libro
                String codigo = JOptionPane.showInputDialog("Ingrese el código del libro:");
                if (codigo == null) break;

                String titulo = JOptionPane.showInputDialog("Ingrese el título del libro:");
                if (titulo == null) break;

                String autor = JOptionPane.showInputDialog("Ingrese el autor del libro:");
                if (autor == null) break;

                String categoria = JOptionPane.showInputDialog("Ingrese la categoría del libro:");
                if (categoria == null) break;

                int stock = 0;
                double precio = 0.0;

                try {
                    stock = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad en stock:"));
                    precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del libro:"));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: Debe ingresar números válidos para stock y precio.");
                    break;
                }

                // Crear objeto y guardar en inventario
                Libro nuevoLibro = new Libro(codigo, titulo, autor, categoria, stock, precio);
                inventario.agregarLibro(nuevoLibro);
                JOptionPane.showMessageDialog(null, "¡Libro agregado exitosamente!");
                break;

            case "2":
                // Muestra la lista en consola
                inventario.mostrarLibros();
                break;

            case "3":
                // Buscar libro
                String codBuscar = JOptionPane.showInputDialog("Ingrese el código a buscar:");
                if (codBuscar != null) {
                    Libro hallado = inventario.buscarPorCodigo(codBuscar);
                    if (hallado != null) {
                        String info = "--- LIBRO ENCONTRADO ---\n"
                                + "Código: " + hallado.getCodigo() + "\n"
                                + "Título: " + hallado.getTitulo() + "\n"
                                + "Autor: " + hallado.getAutor() + "\n"
                                + "Categoría: " + hallado.getCategoria() + "\n"
                                + "Stock: " + hallado.getStock() + "\n"
                                + "Precio: ₡" + hallado.getPrecio();
                        JOptionPane.showMessageDialog(null, info);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún libro con ese código.");
                    }
                }
                break;

            case "4":
                // Eliminar libro
                String codEliminar = JOptionPane.showInputDialog("Ingrese el código del libro a eliminar:");
                if (codEliminar != null) {
                    boolean eliminado = inventario.eliminarLibro(codEliminar);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Libro eliminado con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo eliminar. Código no encontrado.");
                    }
                }
                break;

            case "5":
                // Volver
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
        }

    } while (!subOpcion.equals("5"));
}

    private void menuVentas() {
        // TODO: Implementar submenú de ventas
        JOptionPane.showMessageDialog(null, "Mostrando menú de gestión de ventas...");
    }

    private void menuReportes() {
    reporte rep = new reporte();
    String subOpcion;

    do {
        subOpcion = JOptionPane.showInputDialog(null,
                "=== REPORTES Y CONSULTAS ===\n"
                        + "1. Reporte de inventario completo\n"
                        + "2. Reporte de bajo stock\n"
                        + "3. Búsquedas (Título / Autor)\n"
                        + "4. Reporte de ventas\n"
                        + "5. Reporte de ocupación de estantes\n"
                        + "6. Volver al menú principal\n");

        if (subOpcion == null) break;

        switch (subOpcion) {
            case "1":
                rep.reporteInventarioCompleto(inventario);
                break;
            case "2":
                String limiteStr = JOptionPane.showInputDialog("Ingrese el límite de stock para la alerta (ej. 3):");
                if (limiteStr != null && !limiteStr.trim().isEmpty()) {
                    try {
                        int limite = Integer.parseInt(limiteStr.trim());
                        rep.reporteBajoStock(inventario, limite);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número entero.");
                    }
                }
                break;
            case "3":
                String textoBuscar = JOptionPane.showInputDialog("Ingrese título o autor a buscar:");
                if (textoBuscar != null && !textoBuscar.trim().isEmpty()) {
                    rep.buscarPorTituloOAutor(inventario, textoBuscar.trim());
                }
                break;
            case "4":
                rep.reporteVentas();
                break;
            case "5":
                rep.reporteOcupacionEstantes();
                break;
            case "6":
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
        }

    } while (!subOpcion.equals("6"));
}

}