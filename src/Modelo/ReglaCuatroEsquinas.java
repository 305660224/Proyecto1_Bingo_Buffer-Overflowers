/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


/**
 *
 * @author Jeshuan
 */
public class ReglaCuatroEsquinas implements ReglaGanadora{

    @Override
    public boolean verificarVictoria(boolean[][] matrizMarcada) {
    boolean esquinaSuperiorIzquierda = matrizMarcada[0][0];
    boolean esquinaSuperiorDerecha = matrizMarcada[0][4];
    boolean esquinaInferiorIzquierda = matrizMarcada[4][0];
    boolean esquinaInferiorDerecha = matrizMarcada[4][4];
    
    return esquinaSuperiorIzquierda && esquinaSuperiorDerecha && 
            esquinaInferiorIzquierda && esquinaInferiorDerecha;
    
    }
   
    
}
