/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

//import model.Gramatica;

import controller.Gramatica;
import controller.TipoToken;


/**
 *
 * @author Usuario
 */
public class Token {
   
    private TipoToken tipoToken;
    private Gramatica gramatica;
    private String lexema;
    private int fila;
    private int columna;

    public Token() {
    }
    
    public Token(TipoToken tipoToken ,Gramatica gramatica, String lexema, int fila, int columna) {
        this.tipoToken = tipoToken;
        this.gramatica = gramatica;
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

    public void setLexema(String Lexema) {
        this.lexema = Lexema;
    }

    public Gramatica getGramatica() {
        return gramatica;
    }

    public void setGramatica(Gramatica gramatica) {
        this.gramatica = gramatica;
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
        return "Token{" + "tipo=" + tipoToken + "gramatica=" + gramatica + ", lexema=" + lexema + ", fila=" + fila + ", columna=" + columna + '}';
                
    }
    
    
}
