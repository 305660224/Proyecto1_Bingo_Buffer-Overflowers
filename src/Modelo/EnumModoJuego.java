/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author huete
 */
public enum EnumModoJuego {
   
    NORMAL("Juego Normal", new ReglaNormal()),
    CUATRO_ESQUINAS("Cuatro Esquinas", new ReglaCuatroEsquinas()),
    CARTON_LLENO("Cartón Lleno", new ReglaCartonLleno());
    

    //variable
    private String modoJuego;
    //regla  aplicando strategy
    private final ReglaGanadora regla;
    
    //constructor
    EnumModoJuego(String modoJuego, ReglaGanadora regla) {
        this.modoJuego = modoJuego;
        this.regla = regla;
    }
    
    //metodo getter
    public ReglaGanadora getRegla(){
        return regla;
    }
    
    //metodo getter
    public String getModoJuego() {
        return modoJuego;
    }
    
    //usado para pruebas
    @Override
    public String toString() {
        return modoJuego;
    }
}