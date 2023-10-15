/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sintactico.lexico;

/**
 *
 * @author enmer
 */
public class Sintaxis {
    
    private String mensaje;
    private int fila;
    private int columna;

    public Sintaxis(String mensaje, int fila, int columna) {
        this.mensaje = mensaje;
        this.fila = fila;
        this.columna = columna;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Sintaxis{" + "mensaje=" + mensaje + ", fila=" + fila + ", columna=" + columna + '}';
    }
    
    
    
}
