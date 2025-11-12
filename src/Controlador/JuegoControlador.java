/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.TombolaGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Dennis
 */

public class JuegoControlador {
    private static JuegoControlador instancia;
    private TombolaGUI tombolaGUI;
    private TableroControlador tablero;
    private boolean juegoAutomatico;
    //private TombolaControlador tombola;
    //private Lista<CartonControlador> cartones;
    
    //GETS
    public static JuegoControlador getInstancia() {
        //SINGLETON
        if (instancia == null) {
            instancia = new JuegoControlador();
        }
        return instancia;
    }
    
    //SETS
    public void setJuegoAutomatico(boolean juegoAutomatico) {
        this.juegoAutomatico = juegoAutomatico;
    }
    
    // CONTRUCTOR
    private JuegoControlador() {
        tombolaGUI = TombolaGUI.getInstancia();
        this.tablero = TableroControlador.getInstancia();
        //this.cartones = new ArrayList<>();
    }
    
    //PUBLICOS------------------------------------------------------------------
    
    public void IniciarJuego() throws InterruptedException {
    actualizarInterfazModo();
    tombolaGUI.getBtnComenzar().setEnabled(false);
    tombolaGUI.getBtnAutomatico().setEnabled(false);
    tombolaGUI.getBtnManual().setEnabled(false);
    if(juegoAutomatico) generarNumeroAuto.start();
    }
    /**
     * Marca numero en tablero y cartones :p
     * @param numero Número a procesar
     */
    public void procesarNumero(int numero) {
        if (numero < 1 || numero > 75) return;       
        tablero.MarcarNumero(numero);
        actualizarMostrador(numero);
        tombolaGUI.getTxtNumero().setText("");
        // CARTONES + TOMBOLA
    }
    
    /**
     * Desmarca un número del tablero y cartones (solo si el juego esta en manual);
     * @param numero
     */
    public void desmarcarNumero(int numero) {
        if (numero < 1 || numero > 75) return;
        
        if (!juegoAutomatico) {
            tablero.DesmarcarNumero(numero);
            actualizarMostrador(-1); 
            tombolaGUI.getTxtMostrador().setText("00");
            //CARTONES
        }
    }
    
    /**
     * Reinicia absolutamente todo el juego
     */
    public void reiniciarJuego() {
        tablero.reiniciarTablero();
        tombolaGUI.getBtnComenzar().setEnabled(true);
        tombolaGUI.getBtnMarcar().setEnabled(false);
        tombolaGUI.getBtnDesmarcar().setEnabled(false);
        tombolaGUI.getBtnAutomatico().setEnabled(true);
        tombolaGUI.getBtnManual().setEnabled(true);
        tombolaGUI.getTxtNumero().setEnabled(false);
        actualizarMostrador(-1);
        generarNumeroAuto.stop();
        // CARTONES + TOMBOLA
    }
    
    //PRIVADOS------------------------------------------------------------------
    
    /**
     * Actualiza el mostrador con el último número
     * @param numero Número a mostrar (-1 para reset)
     */
    private void actualizarMostrador(int numero) {
        if (tombolaGUI != null) {
            if (numero == -1) {
                tombolaGUI.getTxtMostrador().setText("00");
                tombolaGUI.getTxtNumero().setText("");
            } else { tombolaGUI.getTxtMostrador().setText(String.valueOf(numero));
            }
        }
    }
    
    /**
     * Actualiza la interfaz según el modo de juego
     */
    private void actualizarInterfazModo() {
        if (tombolaGUI != null) {
            tombolaGUI.getBtnMarcar().setEnabled(!juegoAutomatico);
            tombolaGUI.getBtnDesmarcar().setEnabled(!juegoAutomatico);
            tombolaGUI.getTxtNumero().setEnabled(!juegoAutomatico);           
            }
        }
    
    private Timer generarNumeroAuto = new Timer(1000, new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        procesarNumero(50); //Prueba cambiar por generar numero tombola
      }
    });
    
    }
    

