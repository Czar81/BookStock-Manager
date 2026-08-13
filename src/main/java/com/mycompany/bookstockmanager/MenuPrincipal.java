package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class MenuPrincipal {

    private Inventario inventario = new Inventario();
    private Venta venta = new Venta(inventario);
    private Ubicacion ubicacion = new Ubicacion();
    private Reporte reporte = new Reporte();

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

        int opcion;

        do {
            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "GESTIÓN DE ESTANTES\n\n"
                            + "1. Ver mapa de estantes\n"
                            + "2. Ver espacios libres y ocupados\n"
                            + "3. Consultar ubicación de un libro\n"
                            + "4. Volver al menú principal"));

            switch (opcion) {

                case 1:
                    ubicacion.mostrarMapaEstantes();
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null,
                            "Espacios libres: " + ubicacion.contarEspaciosLibres()
                            + "\nEspacios ocupados: " + ubicacion.contarEspaciosOcupados());
                    break;

                case 3:
                    consultarUbicacionLibro();
                    break;

                case 4:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }

        } while (opcion != 4);
    }

    private void consultarUbicacionLibro() {

        String codigo = JOptionPane.showInputDialog(
                "Digite el código del libro:");

        String ubicacionLibro = ubicacion.buscarUbicacion(codigo);

        if (ubicacionLibro != null) {

            JOptionPane.showMessageDialog(null,
                    "Ubicación: " + ubicacionLibro);

        } else {

            JOptionPane.showMessageDialog(null,
                    "No se encontró ese libro en ningún estante.");
        }
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
                        + "4. Mostrar todos los libros\n"
                        + "5. Reubicar libro\n"
                        + "6. Eliminar libro\n"
                        + "7. Volver al menú principal"
                )
        );


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
                    inventario.mostrarLibros();
                    break;

                case 5:
                    reubicarLibro();
                    break;

                case 6:
                     eliminarLibro();
                    break;

                case 7:
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción inválida.");
            }

    } while (opcion != 7);
}

    private void eliminarLibro() {

        String codigo = JOptionPane.showInputDialog(
                "Digite el código del libro que desea eliminar:");

        Libro libro = inventario.buscarPorCodigo(codigo);

        if (libro != null) {

            String confirmacion = JOptionPane.showInputDialog(
                    "¿Está seguro de que desea eliminar el libro \""
                    + libro.getTitulo() + "\"? (Si/No)");

            if (confirmacion != null && confirmacion.equalsIgnoreCase("Si")) {

                boolean eliminado = inventario.eliminarLibro(codigo);

                if (eliminado == true) {

                    ubicacion.liberarEspacio(codigo);

                    JOptionPane.showMessageDialog(null,
                            "Libro eliminado correctamente.");

                } else {

                    JOptionPane.showMessageDialog(null,
                            "No se pudo eliminar el libro.");
                }
            }

        } else {

            JOptionPane.showMessageDialog(null,
                    "No existe un libro con ese código.");
        }
    }
    

    private void registrarLibro() {

        String codigo = JOptionPane.showInputDialog(
                "Digite el código del libro:");

        if (inventario.buscarPorCodigo(codigo) != null) {

            JOptionPane.showMessageDialog(null,
                    "No se pudo registrar el libro.\n"
                    + "El código ya existe.");

            return;
        }

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

        int numeroEstante = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Digite el número de estante:\n"
                        + "Disponible del 1 al "
                        + ubicacion.getCantidadEstantes()));

        int fila = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Digite la fila:\n"
                        + "Disponible del 1 al "
                        + ubicacion.getFilas()));

        int columna = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Digite la columna:\n"
                        + "Disponible del 1 al "
                        + ubicacion.getColumnas()));

        numeroEstante = numeroEstante - 1;
        fila = fila - 1;
        columna = columna - 1;

        boolean asignado = ubicacion.asignarLibro(
                numeroEstante,
                fila,
                columna,
                codigo);

        if (asignado == true) {

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
                        "No se pudo registrar el libro.");
            }
        }
    }

    private void consultarLibro() {

        String codigo = JOptionPane.showInputDialog(
                "Digite el código del libro:");

        Libro libro = inventario.buscarPorCodigo(codigo);

        if (libro != null) {

            String ubicacionLibro
                    = ubicacion.buscarUbicacion(libro.getCodigo());

            JOptionPane.showMessageDialog(null,
                    "Código: " + libro.getCodigo()
                    + "\nTítulo: " + libro.getTitulo()
                    + "\nAutor: " + libro.getAutor()
                    + "\nCategoría: " + libro.getCategoria()
                    + "\nCantidad: " + libro.getStock()
                    + "\nPrecio: ₡" + libro.getPrecio()
                    + "\nUbicación: " + ubicacionLibro);

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

    private void reubicarLibro() {

        String codigo = JOptionPane.showInputDialog(
                "Digite el código del libro que desea reubicar:");

        Libro libro = inventario.buscarPorCodigo(codigo);

        if (libro != null) {

            String ubicacionActual = ubicacion.buscarUbicacion(codigo);

            JOptionPane.showMessageDialog(null,
                    "Ubicación actual: " + ubicacionActual);

            int nuevoEstante = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "Digite el nuevo estante:\n"
                            + "Disponible del 1 al "
                            + ubicacion.getCantidadEstantes()));

            int nuevaFila = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "Digite la nueva fila:\n"
                            + "Disponible del 1 al "
                            + ubicacion.getFilas()));

            int nuevaColumna = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "Digite la nueva columna:\n"
                            + "Disponible del 1 al "
                            + ubicacion.getColumnas()));

            nuevoEstante = nuevoEstante - 1;
            nuevaFila = nuevaFila - 1;
            nuevaColumna = nuevaColumna - 1;

            boolean reubicado = ubicacion.reubicarLibro(
                    codigo,
                    nuevoEstante,
                    nuevaFila,
                    nuevaColumna);

            if (reubicado == true) {

                JOptionPane.showMessageDialog(null,
                        "El libro fue reubicado correctamente.");
            }

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

        int opcion;

        do {
            opcion = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "REPORTES Y CONSULTAS\n\n"
                            + "1. Reporte de inventario completo\n"
                            + "2. Reporte de bajo stock\n"
                            + "3. Reporte de ocupación de estantes\n"
                            + "4. Reporte de ventas\n"
                            + "5. Buscar por título, autor o ubicación\n"
                            + "6. Volver al menú principal"));

            switch (opcion) {

                case 1:
                    reporte.reporteInventarioCompleto(inventario);
                    break;

                case 2:
                    reporteBajoStockMenu();
                    break;

                case 3:
                    reporte.reporteOcupacionEstantes(ubicacion);
                    break;

                case 4:
                    reporte.reporteVentas(venta);
                    break;

                case 5:
                    buscarLibroMenu();
                    break;

                case 6:
                    break;

                default:
                    JOptionPane.showMessageDialog(null,
                            "Opción inválida.");
            }

        } while (opcion != 6);
    }

    private void reporteBajoStockMenu() {

        int limite = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Digite el límite de stock:"));

        reporte.reporteBajoStock(inventario, limite);
    }

    private void buscarLibroMenu() {

        String texto = JOptionPane.showInputDialog(
                "Digite el título, autor o ubicación a buscar:");

        reporte.buscarPorTituloOAutorOUbicacion(inventario, ubicacion, texto);
    }

}
