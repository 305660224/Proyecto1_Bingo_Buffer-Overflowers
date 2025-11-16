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
private static CartonController instancia;
private boolean [] numerosCantados;
private final int MAX_NUMEROS = 75;
private Random rand;

    public CartonController() {
      this.cartonesParticipantes= new ArrayList<>();
      this.juegoIniciado=false;
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
public boolean eliminarCarton(String id){
    if(juegoIniciado){
        return false;
    }
    return cartonesParticipantes.removeIf(c -> c.getId().equalsIgnoreCase(id));
}
public void marcarNumeroEnTodos(int numeroCantado){
    for(Carton carton: cartonesParticipantes){
        carton.marcarNumero(numeroCantado);
    }
}
  public void desmarcarEnTodos(int numero) {
    for (Carton c : cartonesParticipantes) {
        c.desmarcarNumero(numero);
    }
}
public void limpiarMarcaEnTodos(){
     for (Carton c : cartonesParticipantes) {
        c.limpiarMarcas();
    }
}
private void finalizarPartida(){//ELIMINAR(AHI VEREMOS)
    this.juegoIniciado=false;
}
public boolean verificarGanadores(ReglaGanadora regla){
   for(Carton carton: cartonesParticipantes){
       if(carton.verificarVictoria(reglaActual)){
          return true;
       }
   }
   return false;
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
