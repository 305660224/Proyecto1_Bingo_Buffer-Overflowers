/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author huete
 */
public enum ModoJuego {
   
    NORMAL("Juego Normal"),
    CUATRO_ESQUINAS("Cuatro Esquinas"),
    CARTON_LLENO("Cartón Lleno");
    

    //variable
    private final String descripcion;
    
    //constructor
    ModoJuego(String descripcion) {
        this.descripcion = descripcion;
    }
    //metodos getter
    public String getDescripcion() {
        return descripcion;
    }
    
    @Override
    public String toString() {
        return descripcion;
    }
}