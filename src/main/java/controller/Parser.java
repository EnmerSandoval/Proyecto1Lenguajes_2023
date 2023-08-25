/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import model.Token;

/**
 *
 * @author Usuario
 */
public class Parser {

    private ArrayList<Token> tokens = new ArrayList<>();
    private char[] simbolos = {
        '+',
        '-',
        '*',
        '/',
        '%',
        '"',
        '\'',
        '=',
        '!',
        '#',
        '>',
        '<',
        '(',
        ')',
        '{',
        '}',
        '[',
        ']',
        ',',
        '.',
        ';',
        ':'
    };

    public Parser() {
    }

    public String analizar(JTextPane textoArea) {
        String textoAnalizando = textoArea.getText();
        int texto = 0;
        int fila = 0;
        int columna = 0;
        //System.out.println("Esto es el texto a analizar: " + textoAnalizando);
        StringBuilder textoAnalizadoStringBuilder = new StringBuilder();

        if (!verificacionJTextPane(textoArea)) {
            for (int i = 0; i < textoAnalizando.length(); i++) {
                char c = textoAnalizando.charAt(i);
                if (Character.isLetterOrDigit(c)) {//si no es un simbolo 
                    textoAnalizadoStringBuilder.append(c);
                    if (!textoAnalizadoStringBuilder.isEmpty()) {
                        if (recorriendoEnum(textoAnalizadoStringBuilder)) {
                            System.out.println("Si entramos palabra reservada");
                            textoAnalizadoStringBuilder.setLength(0);
                        }
                    }
                } else if (verificacionEsSimbolo(c)) {
                    if ((i + 1) < textoAnalizando.length() && !Character.isLetterOrDigit(textoAnalizando.charAt(i + 1))) {
                        Gramatica gramaticaDobleSimbolo = dobleSimbolo(c, textoAnalizando.charAt(i + 1));
                        TipoToken tipoToken = dobleSimboloTipo(c, textoAnalizando.charAt(i + 1));
                        tokens.add(new Token(tipoToken, gramaticaDobleSimbolo, (c + "" + textoAnalizando.charAt(i + 1)), 0, 0));
                        i = i + 1;
                    } else {
                        Gramatica gramaticaSimbolo = simbolos(c);
                        tokens.add(new Token(TipoToken.SIMBOLO, gramaticaSimbolo, textoAnalizando, 0, 0));
                    }

                }
            }
            impresionTokens();
        }
        return textoArea.getText();
    }

    private TipoToken dobleSimboloTipo(char c1, char c2) {
        TipoToken tipoTokenRetorno = null;
        String unionChars = c1 + "" + c2;
        switch (unionChars) {
            case "==", "!=", ">=", "<=":
                tipoTokenRetorno = TipoToken.COMPARACION;
                break;
            case "**", "//":
                tipoTokenRetorno = TipoToken.ARITMETICO;
                break;
        }
        return tipoTokenRetorno;
    }

    private Gramatica dobleSimbolo(char c1, char c2) {
        Gramatica gramaticaRetorno = null;
        String unionChars = c1 + "" + c2;
        switch (unionChars) {
            case "==":
                gramaticaRetorno = Gramatica.IGUAL;
                break;
            case "!=":
                gramaticaRetorno = Gramatica.DIFERENTE;
                break;
            case ">=":
                gramaticaRetorno = Gramatica.MAYOR_O_IGUAL_QUE;
                break;
            case "<=":
                gramaticaRetorno = Gramatica.MENOR_O_IGUAL_QUE;
                break;
            case "**":
                gramaticaRetorno = Gramatica.EXPONENTE;
                break;
            case "//":
                gramaticaRetorno = Gramatica.DIVISION;
                break;
        }
        return gramaticaRetorno;
    }

    public void impresionTokens() {
        for (int i = 0; i < tokens.size(); i++) {
            System.out.println(i + "" + tokens.get(i).toString());
        }
    }

    private Gramatica simbolos(char c) {
        Gramatica gramaticaRetorno = null;
        switch (c) {
            case '+':
                gramaticaRetorno = Gramatica.SUMA;
                break;
            case '-':
                gramaticaRetorno = Gramatica.RESTA;
                break;
            case '*':
                gramaticaRetorno = Gramatica.MULTIPLICACION;
                break;
            case '>':
                gramaticaRetorno = Gramatica.MAYOR_QUE;
                break;
            case '<':
                gramaticaRetorno = Gramatica.MENOR_QUE;
                break;
            case '(':
                gramaticaRetorno = Gramatica.PARENTESISAPERTURA;
                break;
            case ')':
                gramaticaRetorno = Gramatica.PARENTESISCIERRE;
                break;
            case '{':
                gramaticaRetorno = Gramatica.LLAVEAPERTURA;
                break;
            case '}':
                gramaticaRetorno = Gramatica.LLAVECIERRE;
                break;
            case '[':
                gramaticaRetorno = Gramatica.CORCHETEAPERTURA;
                break;
            case ']':
                gramaticaRetorno = Gramatica.CORCHETECIERRE;
                break;
            case ',':
                gramaticaRetorno = Gramatica.COMA;
                break;
            case '.':
                gramaticaRetorno = Gramatica.PUNTO;
                break;
            case ';':
                gramaticaRetorno = Gramatica.PUNTO_Y_COMA;
                break;
            case ':':
                gramaticaRetorno = Gramatica.DOS_PUNTOS;
                break;
            case '\\':
                gramaticaRetorno = Gramatica.BARRAINVERSA;
                break;
        }
        return gramaticaRetorno;
    }

    private boolean recorriendoEnum(StringBuilder texto) {
        boolean encontrada = false;
        String textoToken = texto.toString();
        textoToken = textoToken.toUpperCase();
        System.out.println(textoToken);
        try {
            Gramatica gramaticaAuxiliar = Gramatica.valueOf(textoToken);
            if (gramaticaAuxiliar != null) {
                encontrada = true;
            } else {
                encontrada = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encontrada;
    }

    private boolean verificacionEsSimbolo(char c) {
        boolean esSimbolo = false;
        for (int i = 0; i < simbolos.length; i++) {
            if (c == simbolos[i]) {
                esSimbolo = true;
                break;
            } else {
                esSimbolo = false;
            }
        }
        return esSimbolo;
    }

    private boolean verificacionJTextPane(JTextPane textoArea) {
        String verificacionTextoArea = textoArea.getText();
        boolean error = false;
        if (verificacionTextoArea.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Error el texto esta vacio, revisar por favor");
            error = true;
        } else {
            error = false;
        }
        return error;
    }

    private boolean delimitador(char c) {
        return c == '\n' || c == '\r' || c == '\t';
    }

    /* private String verificacionPalabraReservada(String palabra){
        palabra = palabra.toUpperCase();
        switch (palabra) {
            case 
                    //return "palabraReservada";
              
            default:
                return  "irreconocible";
        }
    }*/
}
