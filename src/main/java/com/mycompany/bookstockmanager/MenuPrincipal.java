package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class MenuPrincipal {
    
    private Inventario inventario = new Inventario();
    private Venta venta = new Venta(inventario);

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

        int opcion;

        do {
            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "GESTIÓN DE LIBROS\n\n"
                            + "1. Registrar libro\n"
                            + "2. Consultar libro\n"
                            + "3. Editar libro\n"
                            + "4. Volver al menú principal"));

            switch (opcion) {

                case 1:
                    registrarLibro();
                    break;

                case 2:
                    consultarLibro();
                    break;

                case 3:
                    editarLibro();
                    break;

                case 4:
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción inválida.");
            }

        } while (opcion != 4);
    }
    
    private void registrarLibro() {

        String codigo = JOptionPane.showInputDialog(
                "Digite el código del libro:");

        String titulo = JOptionPane.showInputDialog(
                "Digite el título del libro:");

        String autor = JOptionPane.showInputDialog(
                "Digite el autor del libro:");

        String categoria = JOptionPane.showInputDialog(
                "Digite la categoría del libro:");

        int cantidad = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Digite la cantidad del libro:"));

        double precio = Double.parseDouble(
                JOptionPane.showInputDialog(
                        "Digite el precio del libro:"));

        Libro nuevoLibro = new Libro(
                codigo,
                titulo,
                autor,
                categoria,
                cantidad,
                precio
        );

        boolean agregado = inventario.agregarLibro(nuevoLibro);

        if (agregado == true) {
            JOptionPane.showMessageDialog(null,
                    "Libro registrado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudo registrar el libro.\n"
                    + "El código ya existe o el inventario está lleno.");
        }
    }
    
    private void consultarLibro() {

        String codigo = JOptionPane.showInputDialog(
                "Digite el código del libro:");

        Libro libro = inventario.buscarPorCodigo(codigo);

        if (libro != null) {

            JOptionPane.showMessageDialog(null,
                    "Código: " + libro.getCodigo()
                    + "\nTítulo: " + libro.getTitulo()
                    + "\nAutor: " + libro.getAutor()
                    + "\nCategoría: " + libro.getCategoria()
                    + "\nCantidad: " + libro.getStock()
                    + "\nPrecio: ₡" + libro.getPrecio());

        } else {

            JOptionPane.showMessageDialog(null,
                    "No existe un libro con ese código.");

        }

    }
    
    private void editarLibro() {

    String codigo = JOptionPane.showInputDialog(
            "Digite el código del libro que desea editar:");

    Libro libro = inventario.buscarPorCodigo(codigo);

    if (libro != null) {

        String nuevoTitulo = JOptionPane.showInputDialog(
                "Digite el nuevo título:",
                libro.getTitulo());

        String nuevoAutor = JOptionPane.showInputDialog(
                "Digite el nuevo autor:",
                libro.getAutor());

        String nuevaCategoria = JOptionPane.showInputDialog(
                "Digite la nueva categoría:",
                libro.getCategoria());

        int nuevaCantidad = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Digite la nueva cantidad:",
                        libro.getStock()));

        double nuevoPrecio = Double.parseDouble(
                JOptionPane.showInputDialog(
                        "Digite el nuevo precio:",
                        libro.getPrecio()));

        libro.setTitulo(nuevoTitulo);
        libro.setAutor(nuevoAutor);
        libro.setCategoria(nuevaCategoria);
        libro.setStock(nuevaCantidad);
        libro.setPrecio(nuevoPrecio);

        JOptionPane.showMessageDialog(null,
                "Libro actualizado correctamente.");

    } else {

        JOptionPane.showMessageDialog(null,
                "No existe un libro con ese código.");
    }
}

    private void menuVentas() {

        int opcion;

        do {
            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "GESTIÓN DE VENTAS\n\n"
                            + "1. Registrar venta\n"
                            + "2. Consultar historial de ventas\n"
                            + "3. Volver al menú principal"));

            switch (opcion) {

                case 1:
                    venta.registrarVenta();
                    break;

                case 2:
                    consultarVentas();
                    break;

                case 3:
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción inválida.");
            }

        } while (opcion != 3);
    }

    private void consultarVentas() {

        RegistroVenta[] historial = venta.getHistorial();
        int cantidadVentas = venta.getCantidadVentas();

        if (cantidadVentas == 0) {
            JOptionPane.showMessageDialog(null, "No hay ventas registradas.");
            return;
        }

        String resumen = "";
        for (int i = 0; i < cantidadVentas; i++) {
            resumen += historial[i].getResumen() + "\n\n";
        }

        JOptionPane.showMessageDialog(null, resumen);
    }

    private void menuReportes() {
        // TODO: Implementar submenú de reportes
        JOptionPane.showMessageDialog(null, "Mostrando menú de reportes y consultas...");
    }

}