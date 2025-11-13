/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Carton;
import Modelo.EnumModoJuego;
import Modelo.ReglaGanadora;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Jeshuan
 */
public class CartonController {
private ArrayList<Carton> cartonesParticipantes;
private ReglaGanadora reglaActual;
private boolean juegoIniciado;
private boolean [] numerosCantados;
private final int MAX_NUMEROS = 75;
private Random rand;

    public CartonController() {
      this.cartonesParticipantes= new ArrayList<>();
      this.juegoIniciado=false;
      this.rand=new Random();
      limpiarTombola();
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
public boolean eliminarCarton(String id){
    if(juegoIniciado){
        return false;
    }
    return cartonesParticipantes.removeIf(c -> c.getId().equalsIgnoreCase(id));
}
public boolean iniciarPartida(EnumModoJuego modo){
    if(cartonesParticipantes.isEmpty()){
        return false;
    }
    this.reglaActual=modo.getRegla();
    limpiarTombola();
    
    for(Carton c : cartonesParticipantes){
        c.limpiarMarcas();
    }
    this.juegoIniciado = true;
    return true;
}
public int cantarSiguienteNumero(){
    if(!juegoIniciado){
        return -1;
    }
    int numeroCantado = generarNuevoNumero();
    if(numeroCantado ==-1){
        finalizarPartida();
        return -1;
        
    }
    for(Carton carton: cartonesParticipantes){
        carton.marcarNumero(numeroCantado);
    }
    verificarGanadores();
    return numeroCantado;
}
private void limpiarTombola(){
   this.numerosCantados = new boolean[76];
}
private void finalizarPartida(){
    this.juegoIniciado=false;
}
private void verificarGanadores(){
   for(Carton carton: cartonesParticipantes){
       if(carton.verificarVictoria(reglaActual)){
          finalizarPartida();
          return;
       }
   }
}
private int generarNuevoNumero() {
    int numero;
    int contadorNumerosRestantes = 0;
        for(int i = 1; i <= MAX_NUMEROS; i++){
            if(!numerosCantados[i]){
                contadorNumerosRestantes++;
            }
        }
        
        if (contadorNumerosRestantes == 0) {
            return -1;
        }
       
        do {
            numero = rand.nextInt(MAX_NUMEROS) + 1; 
        } while (numerosCantados[numero]);       
        // Marcar el número como cantado
        numerosCantados[numero] = true; 
        return numero;
    }

    public ArrayList<Carton> getCartonesParticipantes() {
        return cartonesParticipantes;
    }

    public boolean isJuegoIniciado() {
        return juegoIniciado;
    }

    public boolean[] getNumerosCantados() {
        return numerosCantados;
    }

}
