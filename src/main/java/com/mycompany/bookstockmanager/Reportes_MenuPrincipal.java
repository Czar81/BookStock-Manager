package com.mycompany.bookstockmanager;
import javax.swing.JOptionPane; 

public class Reportes_MenuPrincipal{
    Inventario inventario = new Inventario();

    public void MostrarMenu(){
        
        String opcion; 

        do{
    opcion = JOptionPane.showInputDialog(null, "===SISTEMA DE BIBLIOTECA===" 
    +"1. Ver inventario.\n"
    +"2. Buscar libro.\n"
    +"3. Reportes.\n"
    +"4. Salir.\n"
);
    switch(opcion){
        case "1":
            JOptionPane.showMessageDialog(null, "Mostrando inventario...");
            inventario.mostrarInventario();
            break;
        case "2":
            JOptionPane.showMessageDialog(null, "Buscando libro...");
            break;
        case "3":
            JOptionPane.showMessageDialog(null, "Mostrando reportes...");
            break;
        case "4":
            JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
            break;
        default:
            JOptionPane.showMessageDialog(null, "Opción no válida. Por favor, seleccione una opción válida.");
    
        }
} while(!opcion.equals("4"));
}
}