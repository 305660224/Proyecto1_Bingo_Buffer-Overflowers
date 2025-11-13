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
        procesarNumero(numero);
    }
    
    public void removerNumeroManual(int numero) { //AÑADIDO
        tombola.removerNumeroManual(numero);
        actualizarMostrador(-1);
    }
    
    public void iniciarTombola(boolean juegoAutomatico) { //AÑADIDO
    reiniciarTombola();
    actualizarInterfazModo(juegoAutomatico);
    tombolaGUI.getBtnComenzar().setEnabled(false);
    tombolaGUI.getBtnAutomatico().setEnabled(false);
    tombolaGUI.getBtnManual().setEnabled(false);
    }
    
    public void reiniciarTombola() {
        tombola.reiniciar();
        reiniciarTombolaGUI(); //AÑADIDO
    }

    public boolean numeroYaSalio(int numero) {
        return tombola.numeroYaSalio(numero);
    }
    
     /**
     * Actualiza la interfaz según el modo de juego
     */
    public void actualizarInterfazModo(boolean juegoAutomatico) { //AÑADIDO
        if (tombolaGUI != null) {
            tombolaGUI.getBtnMarcar().setEnabled(!juegoAutomatico);
            tombolaGUI.getBtnDesmarcar().setEnabled(!juegoAutomatico);
            tombolaGUI.getTxtNumero().setEnabled(!juegoAutomatico);           
            }
        }
    
    //PRIVADOS------------------------------------------------------------------
    private void reiniciarTombolaGUI() { //AÑADIDO
        tombolaGUI.getBtnComenzar().setEnabled(true);
        tombolaGUI.getBtnAutomatico().setEnabled(true);
        tombolaGUI.getBtnManual().setEnabled(true);
        
        tombolaGUI.getBtnMarcar().setEnabled(false);
        tombolaGUI.getBtnDesmarcar().setEnabled(false);
        tombolaGUI.getTxtNumero().setEnabled(false);
        actualizarMostrador(-1);
    }
    
    /**
     * Marca numero en tablero y cartones :p
     * @param numero Número a procesar
     */
    public void procesarNumero(int numero) { //AÑADIDO    
        actualizarMostrador(numero);
        tombolaGUI.getTxtNumero().setText("");
        // CARTONES + TOMBOLA
    }
    
        /**
     * Actualiza el mostrador con el último número
     * @param numero Número a mostrar (-1 para reset)
     */
    private void actualizarMostrador(int numero) { //AÑADIDO
        if (tombolaGUI != null) {
            if (numero == -1) {
                tombolaGUI.getTxtMostrador().setText("00");
            } else { tombolaGUI.getTxtMostrador().setText(String.valueOf(numero));
            }
        }
    }
}

