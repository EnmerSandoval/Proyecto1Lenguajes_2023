/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import model.Token;

/**
 *
 * @author Usuario
 */
public class Parser {

    private ArrayList<Token> tokens = new ArrayList<>();
    private String[] simbolosDobles = {
        "==",
        "+=",
        "-=",
        "*=",
        "/=",
        "%=",
        "!=",
        ">=",
        "<=",
        "**",
        "//",};
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
        ':',
        '?',
        '!',
        '¿',
        '¡',
        '\'',};

    public Parser() {
    }

    public String analizar(JTable tabla, JTextPane textoArea) {
        String textoAnalizando = textoArea.getText();
        boolean comentarioBooleano = false;
        boolean entreComillas = false;
        int texto = 0;
        int fila = 0;
        int columna = 0;
        //System.out.println("Esto es el texto a analizar: " + textoAnalizando);    
        StringBuilder textoAnalizadoStringBuilder = new StringBuilder();

        if (!verificacionJTextPane(textoArea)) {
            for (int i = 0; i < textoAnalizando.length(); i++) {
                char c = textoAnalizando.charAt(i);
                if (c == '#' || Character.isLetterOrDigit(c) || delimitador(c) || comentarioBooleano == true || c == '"' || c == ' ') {//si no es un simbolo 
                    if (c == '#' || comentarioBooleano == true) {
                        comentarioBooleano = true;
                        if (!verificacionComentario(c)) {
                            textoAnalizadoStringBuilder.append(c);
                        }

                        if (delimitador(c)) {
                            TipoToken comentario = TipoToken.COMENTARIO;
                            Gramatica comentarioGramatica = Gramatica.COMENTARIO;
                            tokens.add(new Token(comentario, comentarioGramatica, textoAnalizadoStringBuilder.toString(), 0, 0));
                            comentarioBooleano = false;
                            textoAnalizadoStringBuilder.setLength(0);
                        }

                    } else if (c == '"' || entreComillas == true) {
                        entreComillas = true;
                        if (!tieneComillasDobles(textoAnalizadoStringBuilder.toString())) {
                            textoAnalizadoStringBuilder.append(c);
                        } else {
                            TipoToken tipoToken = TipoToken.IDENTIFICADOR;
                            Gramatica gramatica = Gramatica.IDENTIFICADOR;
                            tokens.add(new Token(tipoToken, gramatica, textoAnalizadoStringBuilder.toString(), 0, 0));
                            textoAnalizadoStringBuilder.setLength(0);
                        }
                    } else {
                        textoAnalizadoStringBuilder.append(c);
                        if (numeros(c)) {
                            TipoToken token = TipoToken.NUMEROS;
                            Gramatica gramatica = numerosGramatica(c);
                            tokens.add(new Token(token, gramatica, textoAnalizadoStringBuilder.toString(), 0, 0));
                            textoAnalizadoStringBuilder.setLength(0);
                        }
                        if (!textoAnalizadoStringBuilder.isEmpty() && !delimitador(c)) {
                            if (recorriendoEnum(textoAnalizadoStringBuilder)) {
                                TipoToken tipoToken = TipoToken.PALABRA_RESERVADA;
                                Gramatica gramatica = palabraReservada(textoAnalizadoStringBuilder);
                                tokens.add(new Token(tipoToken, gramatica, textoAnalizadoStringBuilder.toString(), 0, 0));
                                textoAnalizadoStringBuilder.setLength(0);
                            } else if (identificador(textoAnalizadoStringBuilder, c, textoAnalizando)) {
                                TipoToken tipoToken = TipoToken.IDENTIFICADOR;
                                Gramatica grmatica = Gramatica.IDENTIFICADOR;
                                tokens.add(new Token(tipoToken, grmatica, textoAnalizadoStringBuilder.toString(), 0, 0));
                                textoAnalizadoStringBuilder.setLength(0);
                            }
                        }
                    }

                } else if (verificacionEsSimbolo(c) && c != ' ') {

                    if ((i + 1) < textoAnalizando.length() && !Character.isLetterOrDigit(textoAnalizando.charAt(i + 1)) && verificacionSimbolosDobles(c, textoAnalizando.charAt(i + 1))) {
                        Gramatica gramaticaDobleSimbolo = dobleSimbolo(c, textoAnalizando.charAt(i + 1));
                        TipoToken tipoToken = dobleSimboloTipo(c, textoAnalizando.charAt(i + 1));
                        tokens.add(new Token(tipoToken, gramaticaDobleSimbolo, (c + "" + textoAnalizando.charAt(i + 1)), 0, 0));
                        i = i + 1;
                    } else {
                        Gramatica gramaticaSimbolo = simbolos(c);
                        TipoToken tipoToken = simboloTipo(c);
                        tokens.add(new Token(tipoToken, gramaticaSimbolo, textoAnalizando.charAt(i) + "", 0, 0));
                    }

                }
            }
        }
        llenadoTable(tabla, tokens);
        return impresionTokens();
    }
    
    private boolean numeros(char c){
        boolean numero = false;
         switch (c) {
            case '0':
                numero = true;
                break;
            case '1':
                numero = true;
                break;
            case '2':
                numero = true;
                break;
            case '3':
                numero = true;
                break;
            case '4':
                numero = true;
                break;
            case '5':
                numero = true;
                break;
            case '6':
               numero = true;
                break;
            case '7':
                numero = true;
                break;
            case '8':
                numero = true;
                break;
            case '9':
                numero = true;
                break;
            default:
                numero = false;
                break;
        }
         return numero;
    }
    
    private Gramatica numerosGramatica(char c) {
        switch (c) {
            case '0':
                return Gramatica.CERO;
            case '1':
                return Gramatica.UNO;
            case '2':
                return Gramatica.DOS;
            case '3':
                return Gramatica.TRES;
            case '4':
                return Gramatica.CUATRO;
            case '5':
                return Gramatica.CINCO;
            case '6':
                return Gramatica.SEIS;
            case '7':
                return Gramatica.SIETE;
            case '8':
                return Gramatica.OCHO;
            case '9':
                return Gramatica.NUEVE;
            default:
                return Gramatica.IDENTIFICADOR;
        }
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
            case "+=", "-=", "*=", "/=", "%=":
                tipoTokenRetorno = TipoToken.ASIGNACION;
                break;
        }
        return tipoTokenRetorno;
    }

    private TipoToken simboloTipo(char c) {
        TipoToken tipoTokenRetorno = null;
        switch (c) {
            case '+', '-', '/', '%', '*':
                tipoTokenRetorno = TipoToken.ARITMETICO;
                break;
            case '>', '<':
                tipoTokenRetorno = TipoToken.COMPARACION;
                break;
            case '=':
                tipoTokenRetorno = TipoToken.ASIGNACION;
                break;
            default:
                tipoTokenRetorno = TipoToken.OTRO;
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
                gramaticaRetorno = Gramatica.EXPONENTESIGNO;
                break;
            case "//":
                gramaticaRetorno = Gramatica.DIVISIONSIGNO;
                break;
            case "+=":
                gramaticaRetorno = Gramatica.ASIGNACION_ADICION;
                break;
            case "-=":
                gramaticaRetorno = Gramatica.ASIGNACION_RESIDUO;
                break;
            case "*=":
                gramaticaRetorno = Gramatica.ASIGNACION_MULTIPLICACION;
                break;
            case "/":
                gramaticaRetorno = Gramatica.ASIGNACION_DIVISION;
                break;
            case "%=":
                gramaticaRetorno = Gramatica.ASIGNACION_RESIDUO;
                break;
        }
        return gramaticaRetorno;
    }

    public String impresionTokens() {
        String resultados = "";
        for (int i = 0; i < tokens.size(); i++) {
            resultados += tokens.get(i).toString() + "\n";
        }
        return resultados;
    }

    private Gramatica simbolos(char c) {
        Gramatica gramaticaRetorno = null;
        switch (c) {
            case '+':
                gramaticaRetorno = Gramatica.SUMASIGNO;
                break;
            case '-':
                gramaticaRetorno = Gramatica.RESTASIGNO;
                break;
            case '*':
                gramaticaRetorno = Gramatica.MULTIPLICACIONSIGNO;
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
            case '=':
                gramaticaRetorno = Gramatica.ASIGNACION;
                break;
            case '\\':
                gramaticaRetorno = Gramatica.BARRAINVERSA;
                break;
            case '¡':
                gramaticaRetorno = Gramatica.ADMIRACIONABIERTO;
                break;
            case '!':
                gramaticaRetorno = Gramatica.ADMIRACIONCERRADO;
                break;
            case '¿':
                gramaticaRetorno = Gramatica.INTERROGACIONAPERTURA;
                break;
            case '?':
                gramaticaRetorno = Gramatica.INTERROGACIONCIERRE;
                break;
            case '\'':
                gramaticaRetorno = Gramatica.COMILLASSIMPLES;
                break;
            case '"':
                gramaticaRetorno = Gramatica.COMILLASDOBLES;
                break;
            case '/':
                gramaticaRetorno = Gramatica.DIVISIONSIGNO;
                break;
        }
        return gramaticaRetorno;
    }

    private Gramatica palabraReservada(StringBuilder texto) {
        Gramatica palabraReservada = null;
        String palabraAuxiliar = texto.toString();
        palabraAuxiliar = palabraAuxiliar.toUpperCase();
        try {
            palabraReservada = Gramatica.valueOf(palabraAuxiliar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return palabraReservada;
    }

    private boolean tieneComillasDobles(String palabra) {
        palabra = palabra.replaceAll(" ", "");
        if (palabra.length() >= 2 && palabra.startsWith("\"") && palabra.endsWith("\"")) {
            return true;
        } else {
            return false;
        }
    }

    public void llenadoTable(JTable tabla, List<Token> token) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Token");
        modelo.addColumn("Patron");
        modelo.addColumn("Lexema");
        modelo.addColumn("Linea");
        modelo.addColumn("Columna");
        Token tokenAuxiliar = new Token();
        try {

            for (int i = 0; i < token.size(); i++) {
                Object[] fila = new Object[5];
                tokenAuxiliar = token.get(i);
                fila[0] = tokenAuxiliar.getGramatica();
                fila[1] = tokenAuxiliar.getGramatica();
                fila[2] = tokenAuxiliar.getLexema();
                fila[3] = tokenAuxiliar.getFila();
                fila[4] = tokenAuxiliar.getColumna();
                modelo.addRow(fila);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        tabla.setModel(modelo);
    }

    private boolean identificador(StringBuilder texto, char c, String textoGeneral) {
        boolean estado = false;
        String textoToken = texto.toString().toUpperCase();
        char siguienteCaracter = (char) (c + 1);
        System.out.println("Siguiente caracter " + siguienteCaracter);

        if (textoToken.startsWith("_")
                || (!Character.isLetterOrDigit(siguienteCaracter) || siguienteCaracter == ' ')) {
            estado = true;
        }

        return estado;
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

    private boolean verificacionComentario(char c) {
        boolean estado = false;
        if (delimitador(c)) {
            estado = true;
        } else {
            estado = false;
        }
        return estado;
    }

    public boolean verificacionSimbolosDobles(char c1, char c2) {
        boolean estado = false;
        String unionChars = c1 + "" + c2;
        for (int i = 0; i < simbolosDobles.length; i++) {
            if (unionChars.equals(simbolosDobles[i])) {
                estado = true;
                break;
            } else {
                estado = false;
            }
        }
        return estado;
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

}
