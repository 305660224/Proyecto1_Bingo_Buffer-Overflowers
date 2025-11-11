/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.HashSet;

/**
 *
 * @author Dennis
 */
public class Tablero {
    //ATRIBUTOS
    private HashSet<Integer> numerosSalidos;
    private int ultimoNumero;
    //CONSTRUCTOR
    public Tablero() {
        numerosSalidos = new HashSet<>();
        ultimoNumero = -1;
    }
    
     /**
     * Remueve numeros al tablero, entre 1 al 75 que allan salido en la tombola.
     * @param numero
     * @return Si el numero fue agregado "true" sino "false"
     */
    public boolean removerNumero(int numero) {
        if (numero >= 1 && numero <= 75 && numerosSalidos.contains(numero)) {
            numerosSalidos.remove(numero);
            ultimoNumero = numero;
            return true;
        }
        return false;
    }
    
    /**
     * Agrega numeros al tablero, entre 1 al 75 que allan salido en la tombola.
     * @param numero
     * @return Si el numero fue agregado "true" sino "false"
     */
    public boolean agregarNumero(int numero) {
        if (numero >= 1 && numero <= 75 && !numerosSalidos.contains(numero)) {
            numerosSalidos.add(numero);
            ultimoNumero = numero;
            return true;
        }
        return false;
    }
    /**
     *Valida si un numero esta en el tablero
     * @param numero
     * @return Si existe el numero en el tablero retorna "true"
     */
    public boolean contieneNumero(int numero) {
        return numerosSalidos.contains(numero);
    }
    
    /**
     *Limpia el tablero
     */
    public void reiniciar() {
        numerosSalidos.clear();
        ultimoNumero = -1;
    }
    
    //METODOS GET
    public HashSet<Integer> getNumerosSalidos() { return numerosSalidos; }
    public int getUltimoNumero() { return ultimoNumero; }

}