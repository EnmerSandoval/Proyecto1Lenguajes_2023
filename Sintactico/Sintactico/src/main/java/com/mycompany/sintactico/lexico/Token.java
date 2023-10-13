/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sintactico.lexico;

/**
 *
 * @author enmer
 */
public class Token {

    private TipoToken tipoToken;
    private String lexema;
    private int fila;
    private int columna;

    public Token() {
    }

    public Token(TipoToken tipoToken, int fila, int columna, String lexema) {
        this.tipoToken = tipoToken;
        this.lexema = lexema;
        this.fila = fila;
        this.columna = columna;
    }

    public TipoToken getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(TipoToken tipoToken) {
        this.tipoToken = tipoToken;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
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

    public boolean match(String pattern) {
        return this.lexema.matches(pattern);
    }

}
