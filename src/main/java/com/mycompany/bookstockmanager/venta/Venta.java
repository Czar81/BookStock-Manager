package com.mycompany.bookstockmanager.venta;

import com.mycompany.bookstockmanager.inventario.Inventario;
import com.mycompany.bookstockmanager.libro.Libro;
import com.mycompany.bookstockmanager.registroventa.RegistroVenta;
import javax.swing.JOptionPane;

public class Venta {

    private RegistroVenta[] historial;
    private int cantidadVentas;
    private int contadorVenta;
    private Inventario inventario;

    public Venta(Inventario inventario) {
        this.historial = new RegistroVenta[100]; // máximo de ventas a guardar
        this.cantidadVentas = 0;
        this.contadorVenta = 1;
        this.inventario = inventario;
    }

    public void registrarVenta() {
        String cliente = pedirCliente();
        if (cliente == null) {
            return;
        }

        String[] rows = new String[50]; // arreglo fijo de líneas de venta
        double totalVenta = 0;

        totalVenta = agregarLibros(rows, totalVenta);
        int cantidadRows = contarRows(rows);

        if (cantidadRows == 0) {
            JOptionPane.showMessageDialog(null, "No se agregó ningún libro. Venta cancelada.");
            return;
        }

        guardarVenta(cliente, rows, cantidadRows, totalVenta);
    }

    private String pedirCliente() {
        String cliente = JOptionPane.showInputDialog(null, "Ingrese el nombre del cliente:");

        // Si se selecciona cancelar cliente va a ser null
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Venta cancelada.");
            return null;
        }
        return cliente;
    }

    private String pedirCodigoLibro() {
        String codigo = JOptionPane.showInputDialog(null,
                "Ingrese el código del libro (o cancele para terminar):");

        if (codigo == null) {
            return null;
        }

        return codigo.trim();
    }

    private int pedirCantidad(Libro libro) {
        String input = JOptionPane.showInputDialog(null,
                "Stock disponible: " + libro.getStock() + "\nIngrese la cantidad a vender:");

        if (input == null) {
            JOptionPane.showMessageDialog(null, "Operación cancelada. No se agregó el libro.");
            return -1;
        }

        input = input.trim();

        if (input == null) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
            return -1;
        }

        int cantidad = Integer.parseInt(input);
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.");
            return -1;
        }

        return cantidad;
    }

    private boolean validarStock(Libro libro, int cantidad) {
        if (libro.getStock() < cantidad) {
            JOptionPane.showMessageDialog(null,
                    "No hay suficiente stock disponible para \"" + libro.getTitulo() + "\".\n"
                            + "Stock disponible: " + libro.getStock());
            return false;
        }
        return true;
    }

    private double agregarLibros(String[] rows, double totalVenta) {
        String[] codigosAgregados = new String[50]; // codigos de libros ya agregados a esta venta
        int cantidadRows = 0;
        boolean terminar = false;

        while (!terminar) {
            String codigo = pedirCodigoLibro();

            if (codigo == null) {
                terminar = true;
            } else {
                Libro libro = inventario.buscarPorCodigo(codigo);

                if (libro == null) {
                    JOptionPane.showMessageDialog(null, "No se encontró ningún libro con ese código.");
                } else {
                    boolean duplicado = false;
                    for (int i = 0; i < cantidadRows; i++) {
                        if (codigosAgregados[i].equals(libro.getCodigo())) {
                            duplicado = true;
                        }
                    }

                    if (duplicado) {
                        JOptionPane.showMessageDialog(null, "Ese libro ya fue agregado a esta venta.");
                    } else {
                        int cantidad = pedirCantidad(libro);

                        if (cantidad == -1) {
                            // Cantidad inválida o cancelada, no se agrega nada.
                        } else if (!validarStock(libro, cantidad)) {
                            // Stock insuficiente, el mensaje ya se mostró en validarStock().
                        } else if (cantidadRows >= rows.length) {
                            JOptionPane.showMessageDialog(null, "No se pueden agregar más libros a esta venta.");
                            terminar = true;
                        } else {
                            codigosAgregados[cantidadRows] = libro.getCodigo();
                            totalVenta = procesarLinea(rows, cantidadRows, libro, cantidad, totalVenta);
                            cantidadRows++;

                            String continuar = JOptionPane.showInputDialog(null,
                                    "¿Desea agregar otro libro a esta venta? (Si/No)");
                            if (continuar == null || continuar.equalsIgnoreCase("No")) {
                                terminar = true;
                            }
                        }
                    }
                }
            }
        }
        return totalVenta;
    }

    private double procesarLinea(String[] rows, int cantidadRows, Libro libro,
            int cantidad, double totalVenta) {
        double subtotal = libro.getPrecio() * cantidad;

        rows[cantidadRows] = libro.getCodigo() + " | " + libro.getTitulo()
                + " x" + cantidad + " = $" + subtotal;

        inventario.restarStock(libro.getCodigo(), cantidad);

        JOptionPane.showMessageDialog(null,
                "Agregado: " + libro.getTitulo() + " x" + cantidad + " ($" + subtotal + ")");

        return totalVenta + subtotal;
    }

    private int contarRows(String[] rows) {
        int contador = 0;
        for (int i = 0; i < rows.length; i++) {
            if (rows[i] != null) {
                contador++;
            }
        }
        return contador;
    }

    private void guardarVenta(String cliente, String[] rows, int cantidadRows, double total) {
        String codigoVenta = "VTA-" + contadorVenta;
        contadorVenta++;
        String fecha = "02/07/2026 00:00"; // Hardcodear hasta poder usar LocalDateTime o Date

        RegistroVenta registro = new RegistroVenta(codigoVenta, cliente, fecha, rows, cantidadRows, total);
        agregarHistorial(registro);

        JOptionPane.showMessageDialog(null, "Venta registrada exitosamente.\n\n" + registro.getResumen());
    }

    private void agregarHistorial(RegistroVenta registro) {
        if (cantidadVentas < historial.length) {
            historial[cantidadVentas] = registro;
            cantidadVentas++;
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden guardar más ventas en el historial.");
        }
    }

    public RegistroVenta[] getHistorial() {
        return historial;
    }

    public int getCantidadVentas() {
        return cantidadVentas;
    }
}