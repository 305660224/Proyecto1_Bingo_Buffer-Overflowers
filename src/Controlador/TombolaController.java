/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Tombola;
import Vista.TombolaGUI;

/**
 *
 * @author huete
 */
public class TombolaController {
    // Aplicando Singleton
    private static TombolaController instancia; 
    private final Tombola tombola;
    private final TombolaGUI tombolaGUI; //AÑADIDO
    
    //constructor
    private TombolaController() {
        this.tombola = new Tombola();
        this.tombolaGUI = TombolaGUI.getInstancia(); //AÑADIDO
    }
    
    public static TombolaController getInstancia() {
        if (instancia == null) {
            instancia = new TombolaController(); //AÑADIDO
        }
        return instancia;
    }
    
    public Tombola getTombola() {
        return tombola;
    }
    //PUBLICOS------------------------------------------------------------------
    
    //utilidad de metodos
    public int generarNumeroAutomatico() {
       return tombola.generarNumero();
    }

    public void ingresarNumeroManual(int numero) {
        tombola.agregarNumeroManual(numero);
        //procesarNumero(numero);
    }
    
    public void removerNumeroManual(int numero) { //AÑADIDO
        tombola.removerNumeroManual(numero);
        tombolaGUI.actualizarMostrador(-1);
    }
    
    public void iniciarTombola(boolean juegoAutomatico) { //AÑADIDO
    reiniciarTombola();
    tombolaGUI.bloquearModoJuego();
    tombolaGUI.actualizarInterfazModo(juegoAutomatico);
    tombolaGUI.iniciar();
    }
    
    public void reiniciarTombola() {
        tombola.reiniciar();
        reiniciarTombolaGUI(); //AÑADIDO
    }

    public boolean numeroYaSalio(int numero) {
        return tombola.numeroYaSalio(numero);
    }
        /**
     * Marca numero en tablero y cartones :p
     * @param numero Número a procesar
     */
    public void procesarNumero(int numero) { //AÑADIDO    
        if (!numeroYaSalio(numero))tombolaGUI.actualizarMostrador(numero);
        tombolaGUI.limpiartxt();
        ingresarNumeroManual(numero);
        // CARTONES + TOMBOLA
    }
    
    public void bloquearModoJuego() { //AÑADIDO    
        tombolaGUI.bloquearModoJuego();
    }
    //PRIVADOS------------------------------------------------------------------
    private void reiniciarTombolaGUI() { //AÑADIDO
        tombolaGUI.reiniciar();
        tombolaGUI.actualizarMostrador(-1);
    }    

}

