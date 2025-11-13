/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


/**
 *
 * @author Jeshuan
 */
public class ReglaCartonLleno implements ReglaGanadora {

    @Override
    public boolean verificarVictoria(boolean[][] matrizMarcada) {
    int marcados=0;
    for(int i = 0; i < 5; i++){
        for(int j = 0; j < 5; j++){
           if(matrizMarcada[i][j]){
           marcados++;
        }
    }
    }
    return marcados ==25;
    }
    
}
