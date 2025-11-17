/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Carton;
import Modelo.CartonFactory;
import Modelo.ReglaGanadora;
import Vista.GUICartonJugable;
import Vista.GUICrearCarton;
import Vista.MainFrame;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import  javax.swing.JButton;
/**
 *
 * @author Jeshuan
 */
public class CartonController {
private ArrayList<Carton> cartonesParticipantes;
private boolean juegoIniciado;
private static CartonController instancia;
private boolean [] numerosCantados;
private CartonFactory cartonFactory;
private GUICrearCarton crearcarton;
 private ArrayList<GUICartonJugable> cartonesVisuales; 

    public CartonController() {
      this.cartonesParticipantes= new ArrayList<>();
      this.juegoIniciado=false;
        this.cartonesVisuales = new ArrayList<>();
      this.crearcarton=GUICrearCarton.getInstancia();
      this.cartonFactory=CartonFactory.getInstancia();
      
    }
    public static CartonController getInstancia(){
        if(instancia==null){
           instancia = new CartonController();
        }
        return instancia;
    }
public String agregarCarton(String id, boolean esAutomatico){
    if(juegoIniciado){
      return "No se pueden añadir más cartones mientras la partida esta iniciada";
    }
    for(Carton c: cartonesParticipantes){
       if(c.getId().equalsIgnoreCase(id)){
         return "Ya existe un carton con este ID"; 
       }
    }
    Carton nuevoCarton = new Carton(id);
    if(esAutomatico){
       nuevoCarton.generarCartonAutomatico();
    }
    cartonesParticipantes.add(nuevoCarton);
    return "Carton "+ id + " agregado con exito";
}

    /**
     * Verifica todos los cartones según la regla proporcionada
     * Retorna true si hay al menos un ganador
     */
    public boolean verificarGanadores(ReglaGanadora regla) { 
        for (Carton carton : cartonesParticipantes) {
            if (carton.verificarVictoria(regla)) {
                // Cartón ganador encontrado
                resaltarCartonGanador(carton);
                return true;
            }
        }
        return false;
    }
    
        private void resaltarCartonGanador(Carton cartonGanador) {
        for (GUICartonJugable cartonVisual : cartonesVisuales) {
            if (cartonVisual.getTxtId().getText().equals(cartonGanador.getId())) {
                // Cambiar el fondo para indicar victoria
                cartonVisual.getContentPane().setBackground(Color.green);               
                break;
            }
        }
    }
        
    public void reiniciarAparienciaCartones() {
    for (GUICartonJugable cartonVisual : cartonesVisuales) {
        cartonVisual.getContentPane().setBackground(new java.awt.Color(0,240,240));       
    }
}
    

    /**
     * Agrega un cartón automático
     */
    public String agregarCartonAutomatico() {
        
        try {
            Carton nuevoCarton = cartonFactory.crearCartonAutomatico();
            cartonesParticipantes.add(nuevoCarton);
            
            // Crear y mostrar la GUI del cartón jugable
            mostrarCartonJugable(nuevoCarton);
            
            return "Cartón " + nuevoCarton.getId() + " agregado con éxito (Automático)";
        } catch (Exception e) {
            return "Error al crear cartón automático: " + e.getMessage();
        }
    }
    
        /**
     * Agrega un cartón manual con los números proporcionados
     */
    public String agregarCartonManual(String[][] numerosIngresados) {
        try {
            CartonFactory.ResultadoCreacionCarton resultado = cartonFactory.crearCartonManual(numerosIngresados);
            
            if (resultado.fueExitoso()) {
                Carton nuevoCarton = resultado.getCarton();
                cartonesParticipantes.add(nuevoCarton);

                mostrarCartonJugable(nuevoCarton);
                
                return "Cartón " + nuevoCarton.getId() + " agregado con éxito (Manual)";
            } else {
                return resultado.getMensajeError();
            }
        } catch (Exception e) {
            return "Error al crear cartón manual: " + e.getMessage();
        }
    }
    
private void mostrarCartonJugable(Carton carton) {
    try {
        // Crear nuevo cartón
        GUICartonJugable guiCarton = new GUICartonJugable();
        
        int[][] numeros = carton.getMatrizNumerica();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int posicion = i * 5 + j + 1;
                JButton boton = guiCarton.getMapaBotones().get(posicion);
                
            cartonesParticipantes.add(carton);
            cartonesVisuales.add(guiCarton);
                
                if (i == 2 && j == 2) {
                    boton.setText("FREE");
                } else {
                    boton.setText(String.valueOf(numeros[i][j]));
                }
            }
        }
        
        guiCarton.getTxtId().setText(carton.getId());
        
        // Buscar el MainFrame y agregar el cartón
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            if (window instanceof MainFrame) {
                MainFrame mainFrame = (MainFrame) window;
                mainFrame.getjDesktopPane1().add(guiCarton);
                guiCarton.setVisible(true);
                break;
            }
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    /**
     * Abre la interfaz para crear cartón manual
     */
    public void abrirCrearCartonManual() {
        GUICrearCarton guiCrear = GUICrearCarton.getInstancia();
        guiCrear.setVisible(true);
        guiCrear.limpiarCampos();
    }
    
    /**
     * Abre la interfaz para crear cartón manual
     */
    public void cerrarCrearCartonManual() {
        GUICrearCarton guiCrear = GUICrearCarton.getInstancia();
        guiCrear.setVisible(false);
        guiCrear.limpiarCampos();
    }
    
    
public boolean eliminarCarton(String id){
    cartonesVisuales.removeIf(cartonVisual -> 
        cartonVisual.getTxtId().getText().equalsIgnoreCase(id)
    );
    return cartonesParticipantes.removeIf(c -> c.getId().equalsIgnoreCase(id));
}
public void marcarNumeroEnTodos(int numeroCantado){
    for(Carton carton: cartonesParticipantes){
        carton.marcarNumero(numeroCantado);
        }
        for (GUICartonJugable cartonVisual : cartonesVisuales) {
            cartonVisual.marcarNumero(numeroCantado);
        }
}
  public void desmarcarEnTodos(int numero) {
    for (Carton c : cartonesParticipantes) {
        c.desmarcarNumero(numero);
    }
    for (GUICartonJugable cartonVisual : cartonesVisuales) {
        cartonVisual.desmarcarNumero(numero);
    }
    
}
public void limpiarMarcaEnTodos(){
     for (Carton c : cartonesParticipantes) {
        c.limpiarMarcas();
    }
    for (GUICartonJugable cartonVisual : cartonesVisuales) {
        cartonVisual.limpiar();
    } 
}

    //GETS Y SETS
    public ArrayList<Carton> getCartonesParticipantes() {
        return cartonesParticipantes;
    }

    public boolean isJuegoIniciado() {
        return juegoIniciado;
    }

    public void setJuegoIniciado(boolean juegoIniciado) {
        this.juegoIniciado = juegoIniciado;
    }
    

    public boolean[] getNumerosCantados() {
        return numerosCantados;
    }

}
