package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class MenuPrincipal {

    Inventario inventario = new Inventario();

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
        // TODO: Implementar submenú de libros
        JOptionPane.showMessageDialog(null, "Mostrando menú de gestión de libros...");
    }

    private void menuVentas() {
        // TODO: Implementar submenú de ventas
        JOptionPane.showMessageDialog(null, "Mostrando menú de gestión de ventas...");
    }

    private void menuReportes() {
        // TODO: Implementar submenú de reportes
        JOptionPane.showMessageDialog(null, "Mostrando menú de reportes y consultas...");
    }

}