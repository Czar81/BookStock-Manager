package com.mycompany.bookstockmanager;

public class Inventario {

    private Libro[] listaLibros;
    private int contadorLibros;

    public Inventario() {
        this.listaLibros = new Libro[100]; // Capacidad máxima de 100 libros
        this.contadorLibros = 0;
    }

    public boolean agregarLibro(Libro libro) {
        if (buscarPorCodigo(libro.getCodigo()) != null) {
            System.out.println("Ya existe un libro con el código '" + libro.getCodigo() + "'.");
            return false;
        }

        if (contadorLibros < listaLibros.length) {
            listaLibros[contadorLibros] = libro;
            contadorLibros++;
            System.out.println("Libro '" + libro.getTitulo() + "' registrado exitosamente.");
            return true;
        }

        System.out.println("No se puede agregar más libros. Inventario lleno.");
        return false;
    }

    public void mostrarLibros() {
        if (contadorLibros == 0) {
            System.out.println("El inventario está vacío.");
            return;
        }

        System.out.println("\n--- LISTA DE LIBROS EN INVENTARIO ---");
        for (int i = 0; i < contadorLibros; i++) {
            Libro libro = listaLibros[i];
            System.out.println("Código: " + libro.getCodigo() + 
                               " | Título: " + libro.getTitulo() + 
                               " | Autor: " + libro.getAutor() + 
                               " | Categoría: " + libro.getCategoria() + 
                               " | Stock: " + libro.getStock() + 
                               " | Precio: ₡" + libro.getPrecio());
        }
    }

    public Libro buscarPorCodigo(String codigo) {
        for (int i = 0; i < contadorLibros; i++) {
            if (listaLibros[i].getCodigo().equalsIgnoreCase(codigo)) {
                return listaLibros[i];
            }
        }
        return null;
    }

    public boolean verificarStock(String codigo, int cantidad) {
        for (int i = 0; i < contadorLibros; i++) {
            Libro l = listaLibros[i];
            if (l.getCodigo().equalsIgnoreCase(codigo)) {
                return l.getStock() >= cantidad;
            }
        }
        return false;
    }

    public void restarStock(String codigo, int cantidadARestar) {
        for (int i = 0; i < contadorLibros; i++) {
            Libro l = listaLibros[i];
            if (l.getCodigo().equalsIgnoreCase(codigo)) {
                l.setStock(l.getStock() - cantidadARestar);
                break;
            }
        }
    }

    public boolean actualizarStock(String codigo, int nuevoStock) {
        Libro libro = buscarPorCodigo(codigo);
        if (libro != null) {
            libro.setStock(nuevoStock);
            return true;
        }
        return false;
    }

    public boolean eliminarLibro(String codigo) {
        for (int i = 0; i < contadorLibros; i++) {
            if (listaLibros[i].getCodigo().equalsIgnoreCase(codigo)) {
                // Desplaza los elementos hacia la izquierda para llenar el hueco
                for (int j = i; j < contadorLibros - 1; j++) {
                    listaLibros[j] = listaLibros[j + 1];
                }
                listaLibros[contadorLibros - 1] = null; // Limpia la última posición
                contadorLibros--; // Reduce la cuenta de libros
                return true;
            }
        }
        return false;
    }

    public Libro[] getListaLibros() {
        return listaLibros;
    }

    public int getContadorLibros() {
        return contadorLibros;
    }
}
