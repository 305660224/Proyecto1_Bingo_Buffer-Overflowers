/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Tablero;
import Vista.TableroGUI;
import java.util.HashMap;
import javax.swing.JTextField;
/**
 *
 * @author ASUS
 */
public class TableroControlador {
    //ATRIBUTOS
    private static TableroControlador instancia;
    private Tablero tablero;
    private TableroGUI tableroGUI;
    private HashMap<Integer, JTextField> numerosTxts;
    
    //GETS
    public Tablero getModel() { return tablero; }
    public static TableroControlador getInstancia() {
        //SINGLETON
        if (instancia == null) {
            instancia = new TableroControlador();
        }
        return instancia;
    }
    
    //CONSTRUCTOR
    private TableroControlador() {
        this.tablero = new Tablero();
        this.tableroGUI = TableroGUI.getInstancia();
        this.numerosTxts = new HashMap<>();
        inicializarHashMap();
    }    
        
    //PUBLICOS------------------------------------------------------------------
    
    /**
     * Desmarca en el tablero el numero enviado
     * @param numero 
     */
    public void DesmarcarNumero(int numero) {
            tablero.removerNumero(numero);
            desmarcarBoton(numero);
    }
    
    /**
     * Marca en el tablero el numero enviado
     * @param numero 
     */
    public void MarcarNumero(int numero) {
            tablero.agregarNumero(numero);
            marcarBoton(numero);
    }
    
    /**
     * Reinicia el tablero desmarcardando todos los numeros
     */
    public void reiniciarTablero() {
        for (JTextField boton : numerosTxts.values()) {
            boton.setEnabled(true);
        }
        tablero.reiniciar();
    }    
    
    //PRIVADOS------------------------------------------------------------------
    private void marcarBoton(int numero) {
        JTextField boton = numerosTxts.get(numero);
        if (boton != null) {
            boton.setEnabled(false);
        }
    }
    
    private void desmarcarBoton(int numero) {
        JTextField boton = numerosTxts.get(numero);
        if (boton != null) {
            boton.setEnabled(true);
        }
    }
    
    private void inicializarHashMap() {
        //lista de Jbuttons
        HashMap<Integer, JTextField> numerosTxtsGUI = tableroGUI.getNumerosTxts();
        if (numerosTxtsGUI != null) {
            this.numerosTxts.putAll(numerosTxtsGUI);
        }
    }

}