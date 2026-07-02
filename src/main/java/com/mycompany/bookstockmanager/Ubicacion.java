package com.mycompany.bookstockmanager;

import javax.swing.JOptionPane;

public class Ubicacion {

    private String[][] estantes;
    private int filas;
    private int columnas;

    public Ubicacion(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        estantes = new String[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                estantes[i][j] = null;
            }
        }
    }

    public boolean estaLibre(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        return estantes[fila][columna] == null;
    }

    public boolean asignarLibro(int fila, int columna, String codigoLibro) {
        if (!estaLibre(fila, columna)) {
            JOptionPane.showMessageDialog(null, "Esa posición ya está ocupada o no existe.");
            return false;
        }
        estantes[fila][columna] = codigoLibro;
        return true;
    }

    public boolean liberarPosicion(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        estantes[fila][columna] = null;
        return true;
    }

    public boolean moverLibro(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        if (estaLibre(filaOrigen, colOrigen)) {
            JOptionPane.showMessageDialog(null, "No hay ningún libro en esa posición de origen.");
            return false;
        }
        if (!estaLibre(filaDestino, colDestino)) {
            JOptionPane.showMessageDialog(null, "La posición destino ya está ocupada.");
            return false;
        }
        String codigo = estantes[filaOrigen][colOrigen];
        estantes[filaOrigen][colOrigen] = null;
        estantes[filaDestino][colDestino] = codigo;
        return true;
    }

    public String obtenerCodigoEn(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return null;
        }
        return estantes[fila][columna];
    }

    public void mostrarMapaEstantes() {
        StringBuilder mapa = new StringBuilder();
        mapa.append("=== MAPA DE ESTANTES ===\n\n");

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (estantes[i][j] == null) {
                    mapa.append("[ Libre ]");
                } else {
                    mapa.append("[" + estantes[i][j] + "]");
                }
                mapa.append("  ");
            }
            mapa.append("\n");
        }

        JOptionPane.showMessageDialog(null, mapa.toString());
    }

    public int contarEspaciosLibres() {
        int libres = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (estantes[i][j] == null) {
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
