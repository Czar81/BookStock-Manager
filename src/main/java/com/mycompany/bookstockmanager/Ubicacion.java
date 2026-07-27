package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class Ubicacion {

    private String[][] estantes;
    private int filas;
    private int columnas;

    public Ubicacion() {
        filas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántas filas de estantes tiene la bodega?"));
        columnas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántas columnas de estantes tiene la bodega?"));
        estantes = new String[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                estantes[i][j] = "Libre";
            }
        }

        JOptionPane.showMessageDialog(null, "Bodega inicializada con " + filas + " filas y " + columnas + " columnas.");
    }

    public boolean estaLibre(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        return estantes[fila][columna].equals("Libre");
    }

    public void asignarEspacio(int fila, int columna, String codigoLibro) {
        if (!estaLibre(fila, columna)) {
            JOptionPane.showMessageDialog(null, "Esa posición ya está ocupada.");
        } else {
            estantes[fila][columna] = codigoLibro;
            JOptionPane.showMessageDialog(null, "Libro asignado en fila " + fila + ", columna " + columna + ".");
        }
    }

    public void liberarEspacio(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            JOptionPane.showMessageDialog(null, "Posición no válida.");
        } else {
            estantes[fila][columna] = "Libre";
            JOptionPane.showMessageDialog(null, "Posición liberada correctamente.");
        }
    }

    public void mostrarMapaEstantes() {
        String mapa = "=== MAPA DE ESTANTES ===\n\n";
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                mapa += "[ " + estantes[i][j] + " ]  ";
            }
            mapa += "\n";
        }
        mapa += "\nLibres: " + contarEspaciosLibres() + " | Ocupados: " + contarEspaciosOcupados();
        JOptionPane.showMessageDialog(null, mapa);
    }

    public int contarEspaciosLibres() {
        int libres = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (estantes[i][j].equals("Libre")) {
                    libres++;
                }
            }
        }
        return libres;
    }

    public int contarEspaciosOcupados() {
        return (filas * columnas) - contarEspaciosLibres();
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
