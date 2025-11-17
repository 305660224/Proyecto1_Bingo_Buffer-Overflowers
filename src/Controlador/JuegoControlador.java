/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.EnumModoJuego;
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
    private boolean juegoIniciado;
    private TableroControlador tablero;
    private TombolaController tombola;
    private CartonController carton;
    private EnumModoJuego modoJuego;

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

    public void setJuegoIniciado(boolean juegoIniciado) {
        this.juegoIniciado = juegoIniciado;
    }    

    public void setModoJuego(EnumModoJuego modoJuego) {
        this.modoJuego = modoJuego;
    }
    
    
    
    // CONTRUCTOR
    private JuegoControlador() {
        tombola = TombolaController.getInstancia();
        this.tablero = TableroControlador.getInstancia();
        this.carton = CartonController.getInstancia();
        this.modoJuego = modoJuego.NORMAL;
    }
    
    //PUBLICOS------------------------------------------------------------------
    
    public void IniciarJuego() throws InterruptedException {
    if (carton.getCartonesParticipantes().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Agregue almenos un cartón!.", "No existen Cartones", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    setJuegoIniciado(true);
    tombola.iniciarTombola(juegoAutomatico);
    if(juegoAutomatico) {
        juegoAutomaticoTimer.start();
     }
    }
    
    public void agregarCarton() {
        tombola.bloquearModoJuego();
        if (juegoIniciado) {
            JOptionPane.showMessageDialog(null, "No se pueden añadir más cartones mientras la partida está iniciada", "Juego en Curso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (juegoAutomatico) {
            // Modo automático - crear cartón automático
            String resultado = carton.agregarCartonAutomatico();
            carton.cerrarCrearCartonManual();
            JOptionPane.showMessageDialog(null, resultado, "Cartón Agregado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Modo manual - abrir interfaz para crear cartón manual
            carton.abrirCrearCartonManual();
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
            carton.marcarNumeroEnTodos(numero);
            verificarGanadores();
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
            carton.desmarcarEnTodos(numero);
        }
    }
    
    /**
     * Reinicia absolutamente todo el juego
     */
    public void reiniciarJuego() {
        tablero.reiniciarTablero();
        tombola.reiniciarTombola();
        juegoAutomaticoTimer.stop();
        carton.limpiarMarcaEnTodos();
        setJuegoIniciado(false);
        carton.reiniciarAparienciaCartones();
    }
    
        private void verificarGanadores() {
        if (modoJuego == null) {
            modoJuego = EnumModoJuego.NORMAL;
        }
        
        // Usar el método existente pero pasarle la regla correcta
        boolean hayGanador = carton.verificarGanadores(modoJuego.getRegla());
        
        if (hayGanador) {
            // ¡Hay ganador!
            juegoAutomaticoTimer.stop();
            setJuegoIniciado(false);
            
            // Mostrar mensaje de victoria
            String mensaje = "GANADOR!" + modoJuego.getModoJuego();
            JOptionPane.showMessageDialog(null, mensaje, "¡BINGO!", JOptionPane.INFORMATION_MESSAGE);
        }
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

