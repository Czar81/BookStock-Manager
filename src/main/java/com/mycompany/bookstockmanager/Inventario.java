package com.mycompany.bookstockmanager;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Inventario {
    private ArrayList<Libro> listaLibro;

    public Inventario(){
        this.listaLibro = new ArrayList<>();
    }
    
    public boolean verificarStock(String titulo, int cantidad) {
        for(Libro l : listaLibro){
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l.getStock() >= cantidad;
            }   

        }
    
    
        return true;
    }

    public void restarStock(String titulo, int cantidadARestar){
        for(Libro l : listaLibro){
            if (l.getTitulo().equalsIgnoreCase(titulo)){
                int StockActual = l.getStock();
                l.setStock(stockActual - cantidadARestar);
            break;
            }
        }
    }
}
