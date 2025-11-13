/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Dennis
 */

public class JuegoControlador {
    private static JuegoControlador instancia;
    private boolean juegoAutomatico;
    private TableroControlador tablero;
    private TombolaController tombola;

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
        tombola = TombolaController.getInstancia();
        this.tablero = TableroControlador.getInstancia();
        //this.cartones = new ArrayList<>();
    }
    
    //PUBLICOS------------------------------------------------------------------
    
    public void IniciarJuego() throws InterruptedException {
    tombola.iniciarTombola(juegoAutomatico);
    if(juegoAutomatico) {
        juegoAutomaticoTimer.start();
     }
    }
    
    /**
     * Desmarca un número del tablero y cartones (solo si el juego esta en manual);
     * @param numero
     */
    public void marcarNumero(int numero) {
        if (numero < 1 || numero > 75) return;       
            tablero.MarcarNumero(numero);            
            tombola.procesarNumero(numero);
            //CARTONES
        
    }
    
    /**
     * Desmarca un número del tablero y cartones (solo si el juego esta en manual);
     * @param numero
     */
    public void desmarcarNumero(int numero) {
        if (numero < 1 || numero > 75) return;       
        if (!juegoAutomatico) {
            tablero.DesmarcarNumero(numero);            
            tombola.removerNumeroManual(numero);
            //CARTONES
        }
    }
    
    /**
     * Reinicia absolutamente todo el juego
     */
    public void reiniciarJuego() {
        tablero.reiniciarTablero();
        tombola.reiniciarTombola();
        juegoAutomaticoTimer.stop();
        // CARTONES + TOMBOLA
    }
    
    //PRIVADOS------------------------------------------------------------------
    
    private Timer juegoAutomaticoTimer = new Timer(1000, new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
        //Se genera un numero automatico por medio de la tombola llamando a generarNumeroAutomatico(), se asigna el numero a una variable y se da a procesarNumero(numero) para que lo marque en el tablero 
        int numero = tombola.generarNumeroAutomatico();
            System.out.println(tombola.getTombola().getNumerosSalidos().size() + "numeros");
        marcarNumero(numero); 
        } catch (IllegalStateException ex) {
            // Ya no hay más números disponibles para y manda un mensaje 
            juegoAutomaticoTimer.stop();
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Tómbola vacía", JOptionPane.INFORMATION_MESSAGE);
        }
        //procesarNumero(50); //Prueba cambiar por generar numero tombola
      }
    });
    
    }
    

