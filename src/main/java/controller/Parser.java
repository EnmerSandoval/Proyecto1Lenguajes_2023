/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javax.swing.JTextPane;

/**
 *
 * @author Usuario
 */
public class Parser {

    public Parser() {
    }

    public String analizar(JTextPane textoArea) {
        String resultados = "";
        String parser = textoArea.getText();
        System.out.println("El parser es: " + parser);
        if (delimitador(resultados)) {
            System.out.println("Es verdadero");
        }
        while (resultados.equals(delimitador(resultados))) {
            if (parser.equals("if")) {
                resultados = "Se ingreso un if papito dale vos podes\n";
            }
        }
        return resultados;
    }
    

    private boolean delimitador(char c) {
        return c ==  '\n' || c == '\r' || c == '\t';
    }

}
