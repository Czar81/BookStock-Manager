package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class Ubicacion {

    private String[][][] estantes;
    private int cantidadEstantes;
    private int filas;
    private int columnas;

    public Ubicacion() {
        cantidadEstantes = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántos estantes tiene la bodega?"));
        filas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántas filas tiene cada estante?"));
        columnas = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuántas columnas tiene cada estante?"));

        estantes = new String[cantidadEstantes][filas][columnas];

        for (int e = 0; e < cantidadEstantes; e++) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    estantes[e][i][j] = "Libre";
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Bodega inicializada con " + cantidadEstantes + " estantes de "
                + filas + " filas y " + columnas + " columnas cada uno.");
    }

    public boolean estaLibre(int estante, int fila, int columna) {
        if (estante < 0 || estante >= cantidadEstantes || fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        return estantes[estante][fila][columna].equals("Libre");
    }

    public boolean asignarLibro(int estante, int fila, int columna, String codigoLibro) {
        if (!estaLibre(estante, fila, columna)) {
            JOptionPane.showMessageDialog(null, "Esa posición ya está ocupada o no existe.");
            return false;
        }
        estantes[estante][fila][columna] = codigoLibro;
        JOptionPane.showMessageDialog(null, "Libro " + codigoLibro + " asignado en E" + (estante + 1)
                + ", fila " + fila + ", columna " + columna + ".");
        return true;
    }

    public String buscarUbicacion(String codigoLibro) {
        for (int e = 0; e < cantidadEstantes; e++) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (estantes[e][i][j].equals(codigoLibro)) {
                        return "E" + (e + 1) + ", Fila " + i + ", Columna " + j;
                    }
                }
            }
        }
        return null;
    }

    public boolean reubicarLibro(String codigoLibro, int nuevoEstante, int nuevaFila, int nuevaColumna) {

        int estanteOrigen = -1;
        int filaOrigen = -1;
        int columnaOrigen = -1;

        for (int e = 0; e < cantidadEstantes; e++) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (estantes[e][i][j].equals(codigoLibro)) {
                        estanteOrigen = e;
                        filaOrigen = i;
                        columnaOrigen = j;
                    }
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

        estantes[estanteOrigen][filaOrigen][columnaOrigen] = "Libre";
        estantes[nuevoEstante][nuevaFila][nuevaColumna] = codigoLibro;

        JOptionPane.showMessageDialog(null, "Libro reubicado a E" + (nuevoEstante + 1)
                + ", fila " + nuevaFila + ", columna " + nuevaColumna + ".");
        return true;
    }

    public boolean liberarEspacio(String codigoLibro) {
        for (int e = 0; e < cantidadEstantes; e++) {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (estantes[e][i][j].equals(codigoLibro)) {
                        estantes[e][i][j] = "Libre";
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void mostrarMapaEstantes() {
        String mapa = "=== MAPA DE ESTANTES ===\n\n";

        for (int e = 0; e < cantidadEstantes; e++) {
            mapa += "--- Estante E" + (e + 1) + " ---\n";
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    mapa += "[ " + estantes[e][i][j] + " ]  ";
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
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (estantes[e][i][j].equals("Libre")) {
                        libres++;
                    }
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

