/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Tombola;

/**
 *
 * @author huete
 */
public class TombolaController {
    // Aplicando Singleton
    private static TombolaController instancia; 
    private final Tombola tombola;
    
    //constructor
    private TombolaController() {
        this.tombola = new Tombola();
    }
    
    public static TombolaController getInstancia() {
        if (instancia == null) {
            instancia = new TombolaController();
        }
        return instancia;
    }
    
    public Tombola getTombola() {
        return tombola;
    }
        
    //utilidad de metodos
    public int generarNumeroAutomatico() {
       return tombola.generarNumero();
    }

    public void ingresarNumeroManual(int numero) {
        tombola.agregarNumeroManual(numero);
    }

    public void reiniciarTombola() {
        tombola.reiniciar();
    }

    public boolean numeroYaSalio(int numero) {
        return tombola.numeroYaSalio(numero);
    }
}

