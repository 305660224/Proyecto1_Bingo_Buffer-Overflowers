/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.util.Random;
/**
 *
 * @author Jeshuan
 */
public class Carton {
private String id;
private final int FILAS=5;
private final int COLUMNAS=5;
private int [][] matrizNumerica;
private boolean [][] matrizMarcada;

    public Carton(String id) {
        this.id = id;
        this.matrizNumerica = new int [FILAS][COLUMNAS];
        this.matrizMarcada = new boolean [FILAS][COLUMNAS];
        this.matrizMarcada[2][2] = true;
    }
    
    public boolean marcarNumero(int numero) {
        for(int i = 0; i < FILAS; i++){
           for(int j = 0; j < COLUMNAS; j++){
              if(this.matrizNumerica[i][j] ==numero){
                 this.matrizMarcada [i][j] = true;
                 return true;
              }
           }
        }
        return false;
    }
     public boolean desmarcarNumero(int numero) {
        for(int i = 0; i < FILAS; i++){
           for(int j = 0; j < COLUMNAS; j++){
              if(i == 2 && j ==2) continue; //No se permite desmarcar la casilla libre
                 if(this.matrizNumerica[i][j] ==numero){
                 this.matrizMarcada [i][j] = false; //Se establece como no marcada
                 return true;
                 }
           }
           }
        return false;
    }
    public boolean verificarVictoria(ReglaGanadora estrategia){
     return estrategia.verificarVictoria(matrizMarcada);
    }
    public void generarCartonAutomatico(){
       //Version de prueba, necesito implementar validaciones más completa
       Random rand = new Random();
       for(int j = 0; j<COLUMNAS; j++ ){
          int min = j * 15 + 1;
          int max = min + 14;
          
          for(int i = 0; i<FILAS; i++ ){
              if(i == 2 && j ==2) continue; 
          int nuevoNumero;
          do{
              nuevoNumero = rand.nextInt(max - min + 1) +  min;
          }while(contieneNumero(nuevoNumero));
           this.matrizNumerica[i][j] = nuevoNumero; 
          }
        }
      }
    
    public String llenarCartonManual(String[][] numerosIngresados) {
    boolean[] numerosVistos = new boolean[76]; // Índices 1 a 75
    
    for (int i = 0; i < FILAS; i++) {
        for (int j = 0; j < COLUMNAS; j++) {
            if (i == 2 && j == 2) {
                this.matrizNumerica[i][j] = 0; // Se usa 0 para la casilla libre
                continue; 
            }
            int numero;
            try {
                numero = Integer.parseInt(numerosIngresados[i][j]);
            } catch (NumberFormatException e) {
                return "Error: Uno de los valores no es un número válido en la casilla [" + i + "][" + j + "].";
            }
    
            if (!validarRangoColumna(numero, j)) {
                return "Error: El número " + numero + " en la columna Bingo[" + (j + 1) + "] está fuera del rango permitido.";
            }
            
            // No Duplicado 
            if (numerosVistos[numero]) {
                return "Error: El número " + numero + " está duplicado en el cartón.";
            }
            
            this.matrizNumerica[i][j] = numero;
            numerosVistos[numero] = true;
        }
    }
    return null; 
}
    
    public boolean validarRangoColumna(int numero, int columna){
        int min = columna * 15 + 1;
        int max = min + 14;
        return numero >= min && numero <= max;
    }
     private boolean contieneNumero(int numero){
          for(int i = 0; i < FILAS; i++){
           for(int j = 0; j < COLUMNAS; j++){
             if(this.matrizNumerica[i][j]==numero){
               return true;  
             }  
           }
          }
          return false;
     } 
     public void limpiarMarcas(){
        this.matrizMarcada = new boolean[FILAS][COLUMNAS];
        this.matrizMarcada[2][2] = true;
     }
    //GETTERS
    public String getId() {
        return id;
    }

    public int[][] getMatrizNumerica() {
        return matrizNumerica;
    }

    public boolean[][] getMatrizMarcada() {
        return matrizMarcada;
    }
     
}