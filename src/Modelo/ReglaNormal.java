/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


/**
 *
 * @author Jeshuan
 */
public class ReglaNormal implements ReglaGanadora {

    @Override
    public boolean verificarVictoria(boolean[][] matrizMarcada) {
       return verificarHorizontal(matrizMarcada) || verificarVertical(matrizMarcada) ||
               verificarDiagonal(matrizMarcada);
    }
    private boolean verificarHorizontal(boolean [][] matriz){
       for(int i = 0; i <5; i++){
           boolean filaCompleta = true;
           for(int j = 0; j < 5; j++){
               if(!matriz[i][j]){
                 filaCompleta=false;
                 break;
               }
           }
           if(filaCompleta) return true;
       } 
       return false;
    }
    private boolean verificarVertical(boolean [][]matriz){
       for(int j = 0; j<5; j++){
           boolean colunmaCompleta= true;
           for(int i = 0; i < 5; i++){
               if(!matriz[i][j]){
                 colunmaCompleta=false;
                 break;
               }
               
           }
           if(colunmaCompleta) return true;
       }
       return false;
    }
    private boolean verificarDiagonal(boolean [][] matriz){
        boolean diagPrincipalCompleta = true;
        for(int i = 0; i < 5; i++){
           if(!matriz[i][i]){
            diagPrincipalCompleta = false;
            break;
           }
        }
          if(diagPrincipalCompleta) return true;
          boolean diagSecundariaCompleta = true;
          for(int i = 0; i<5; i++){
              if(!matriz[i][4-i]){
                 diagSecundariaCompleta = false;
                 break;
              }
          }
          return diagSecundariaCompleta;
    }
}
