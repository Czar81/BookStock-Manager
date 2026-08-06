package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class Ubicacion {
    private String[][] estantes;
    private int cantidadEstantes;
    private int filas;
    private int columnas;

    public Ubicacion() {
        cantidadEstantes = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántos estantes tiene la bodega?"));
        filas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántas filas tiene cada estante?"));
        columnas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántas columnas tiene cada estante?"));
        estantes = new String[cantidadEstantes][filas * columnas];
        for (int e = 0; e < cantidadEstantes; e++) {
            for (int p = 0; p < filas * columnas; p++) {
                estantes[e][p] = "Libre";
            }
        }
        JOptionPane.showMessageDialog(null, "Bodega inicializada con " + cantidadEstantes + " estantes de " + filas + " filas y " + columnas + " columnas cada uno.");
    }

    public boolean estaLibre(int estante, int fila, int columna) {
        if (estante < 0 || estante >= cantidadEstantes || fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        int posicion = fila * columnas + columna;
        return estantes[estante][posicion].equals("Libre");
    }

    public boolean asignarLibro(int estante, int fila, int columna, String codigoLibro) {
        if (!estaLibre(estante, fila, columna)) {
            JOptionPane.showMessageDialog(null, "Esa posición ya está ocupada o no existe.");
            return false;
        }
        int posicion = fila * columnas + columna;
        estantes[estante][posicion] = codigoLibro;
        JOptionPane.showMessageDialog(null, "Libro " + codigoLibro + " asignado en E" + (estante + 1) + ", fila " + fila + ", columna " + columna + ".");
        return true;
    }

    public String buscarUbicacion(String codigoLibro) {
        for (int e = 0; e < cantidadEstantes; e++) {
            for (int p = 0; p < filas * columnas; p++) {
                if (estantes[e][p].equals(codigoLibro)) {
                    int fila = p / columnas;
                    int columna = p % columnas;
                    return "E" + (e + 1) + ", Fila " + fila + ", Columna " + columna;
                }
            }
        }
        return null;
    }

    public boolean reubicarLibro(String codigoLibro, int nuevoEstante, int nuevaFila, int nuevaColumna) {
        int estanteOrigen = -1;
        int posicionOrigen = -1;
        for (int e = 0; e < cantidadEstantes; e++) {
            for (int p = 0; p < filas * columnas; p++) {
                if (estantes[e][p].equals(codigoLibro)) {
                    estanteOrigen = e;
                    posicionOrigen = p;
                }
            }
        }
        if (estanteOrigen == -1) {
            JOptionPane.showMessageDialog(null, "No se encontró ese libro en ningún estante.");
            return false;
        }
        if (!estaLibre(nuevoEstante, nuevaFila, nuevaColumna)) {
            JOptionPane.showMessageDialog(null, "La posición destino ya está ocupada.");
            return false;
        }
        estantes[estanteOrigen][posicionOrigen] = "Libre";
        int posicionDestino = nuevaFila * columnas + nuevaColumna;
        estantes[nuevoEstante][posicionDestino] = codigoLibro;
        JOptionPane.showMessageDialog(null, "Libro reubicado a E" + (nuevoEstante + 1) + ", fila " + nuevaFila + ", columna " + nuevaColumna + ".");
        return true;
    }

    public void mostrarMapaEstantes() {
        String mapa = "=== MAPA DE ESTANTES ===\n\n";
        for (int e = 0; e < cantidadEstantes; e++) {
            mapa += "--- Estante E" + (e + 1) + " ---\n";
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    int posicion = i * columnas + j;
                    mapa += "[ " + estantes[e][posicion] + " ]  ";
                }
                mapa += "\n";
            }
            mapa += "\n";
        }
        mapa += "Libres: " + contarEspaciosLibres() + " | Ocupados: " + contarEspaciosOcupados();
        JOptionPane.showMessageDialog(null, mapa);
    }

    public int contarEspaciosLibres() {
        int libres = 0;
        for (int e = 0; e < cantidadEstantes; e++) {
            for (int p = 0; p < filas * columnas; p++) {
                if (estantes[e][p].equals("Libre")) {
                    libres++;
                }
            }
        }
        return libres;
    }

    public int contarEspaciosOcupados() {
        return (cantidadEstantes * filas * columnas) - contarEspaciosLibres();
    }

    public int getCantidadEstantes() {
        return cantidadEstantes;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
