/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import prueba.Observer.TombolaListener;

/**
 *
 * @author huete
 */
public class Tombola {
    
    private final Set<Integer> numerosSalidos;
    private final Random numeroRandom;
    private final List<TombolaListener> listeners;

 //contenedores de numeros
    public Tombola() {
        numerosSalidos = new HashSet<>();
        numeroRandom = new Random();
        listeners = new ArrayList<>();
    }

    public int generarNumero() {
        if (numerosSalidos.size() >= 75) {
            throw new IllegalStateException("Ya se generaron todos los números (1 al 75).");
        }

        int numero;
        do {
            numero = numeroRandom.nextInt(75) + 1;
        } while (numerosSalidos.contains(numero));

        numerosSalidos.add(numero);
        notificarNumeroGenerado(numero);
        return numero;
    }

  
    public void agregarNumeroManual(int numero) {
        if (numero < 1 || numero > 75) {
            throw new IllegalArgumentException("El número debe estar entre 1 y el 75.");
        }
        if (numerosSalidos.contains(numero)) {
            throw new IllegalStateException("El número " + numero + " ya fue sorteado.");
        }

        numerosSalidos.add(numero);
        notificarNumeroGenerado(numero);
    }

    
    public void reiniciar() {
        numerosSalidos.clear();
        notificarTombolaReiniciada();
    }

    public boolean numeroYaSalio(int numero) {
        return numerosSalidos.contains(numero);
    }

    public Set<Integer> getNumerosSalidos() {
        return Collections.unmodifiableSet(numerosSalidos);
    }

// usando observer
    public void addListener(TombolaListener listener) {
        if (listener != null && !listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void removeListener(TombolaListener listener) {
        listeners.remove(listener);
    }

    private void notificarNumeroGenerado(int numero) {
        for (TombolaListener l : listeners) {
            l.generadorNumeros(numero);
        }
    }

    private void notificarTombolaReiniciada() {
        for (TombolaListener l : listeners) {
            l.tombolaReiniciada();
        }
    }
}
