/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Factory para la creación de cartones con ID automático
 */
public class CartonFactory {
    private static CartonFactory instancia;
    private AtomicInteger contadorId;
    
    private CartonFactory() {
        this.contadorId = new AtomicInteger(1); // Empieza desde CARTON-1
    }
    
    public static CartonFactory getInstancia() {
        if (instancia == null) {
            instancia = new CartonFactory();
        }
        return instancia;
    }
    
    /**
     * Crea un cartón automático con ID generado automáticamente
     * @return Carton con ID automático
     */
    public Carton crearCartonAutomatico() {
        String id = generarIdAutomatico();
        Carton carton = new Carton(id);
        carton.generarCartonAutomatico();
        return carton;
    }
    
    /**
     * Crea un cartón manual con ID generado automáticamente
     * @param numerosIngresados Matriz 
     * @return Carton 
     */
    public ResultadoCreacionCarton crearCartonManual(String[][] numerosIngresados) {
        String id = generarIdAutomatico();
        Carton carton = new Carton(id);
        String resultado = carton.llenarCartonManual(numerosIngresados);
        
        if (resultado == null) {
            return new ResultadoCreacionCarton(carton, null);
        } else {
            // Si hay error, revertir el contador
            contadorId.decrementAndGet();
            return new ResultadoCreacionCarton(null, resultado);
        }
    }
    
    /**
     * Crea un cartón con ID específico (para casos especiales)
     * @param id ID específico para el cartón
     * @param esAutomatico true para cartón automático, false para manual
     * @param numerosIngresados Matriz con números (solo para manual)
     * @return ResultadoCreacionCarton con el cartón o el error
     */
    public ResultadoCreacionCarton crearCartonConId(String id, boolean esAutomatico, String[][] numerosIngresados) {
        Carton carton = new Carton(id);
        
        if (esAutomatico) {
            carton.generarCartonAutomatico();
            return new ResultadoCreacionCarton(carton, null);
        } else {
            String resultado = carton.llenarCartonManual(numerosIngresados);
            if (resultado == null) {
                return new ResultadoCreacionCarton(carton, null);
            }
            return new ResultadoCreacionCarton(null, resultado);
        }
    }
    
    /**
     * Genera ID automático en formato: CARTON-1, CARTON-2, etc.
     */
    private String generarIdAutomatico() {
        int numero = contadorId.getAndIncrement();
        return "CARTON-" + numero;
    }
    
    /**
     * Reinicia el contador de IDs (útil para nuevos juegos)
     */
    public void reiniciarContador() {
        this.contadorId.set(1);
    }
    
    /**
     * Obtiene el próximo ID sin incrementar el contador
     */
    public String obtenerProximoId() {
        return "CARTON-" + contadorId.get();
    }
    
    /**
     * Clase para manejar el resultado de la creación del cartón
     */
    public static class ResultadoCreacionCarton {
        private Carton carton;
        private String mensajeError;
        
        public ResultadoCreacionCarton(Carton carton, String mensajeError) {
            this.carton = carton;
            this.mensajeError = mensajeError;
        }
        
        public boolean fueExitoso() {
            return carton != null;
        }
        
        public Carton getCarton() {
            return carton;
        }
        
        public String getMensajeError() {
            return mensajeError;
        }
    }
}