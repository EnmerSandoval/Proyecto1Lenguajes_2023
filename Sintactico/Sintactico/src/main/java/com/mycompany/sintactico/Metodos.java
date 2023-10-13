package com.mycompany.sintactico;

import com.mycompany.sintactico.lexico.TipoToken;
import com.mycompany.sintactico.lexico.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author enmer
 */
public class Metodos {
    /*
    private List<Token> tokens;
    private int index;
    private Map<String, Integer> variables = new HashMap<>();
    private Map<String, FunctionDefinition> funciones = new HashMap<>();
    private String bloqueActual = "";

    public Metodos(List<Token> tokens, int index) {
        this.tokens = tokens;
        this.index = index;
    }

    public boolean analizar() {
        try {
            sentencia();
            return index == tokens.size();
        } catch (Exception e) {
            return false;
        }
    }

    private void sentencia() {
        if (tokens.get(index).getTipoToken().equals("PALABRA_RESERVADA")) {
            if (tokens.get(index).getLexema().equals("if")) {
                sentencia_if();
            } else if (tokens.get(index).getLexema().equals("for")) {
                sentencia_for();
            } else if (tokens.get(index).getLexema().equals("while")) {
                sentencia_while();
            } else if (tokens.get(index).getLexema().equals("def")) {
                sentencia_funcion();
            }
        } else if (tokens.get(index).match("IDENTIFICADOR.*")) {
            asignacion();
        } else {
            throw new RuntimeException("Error de sintaxis: token inesperado");
        }
    }

    private void consumirToken(String expectedToken) {
        if (index < tokens.size() && tokens.get(index).equals(expectedToken)) {
            index++;
        } else {
            throw new RuntimeException("Error de sintaxis: se esperaba '" + expectedToken + "'");
        }
    }

    private void signosAritmeticos(String token) {

    }

    private void tokenPalabraReservada(String token) {
        if (index < tokens.size() && tokens.get(index).getLexema().equals(token)) {
            index++;
        } else {
            throw new RuntimeException("Error de sintaxis: se esperaba '" + token + "'");
        }
    }

    private void sentencia_if() {
        tokenPalabraReservada("if");
        expresion();
        consumirToken("DOSPUNTOS");
        bloqueActual = "if";
        bloque();
        sentencia_elif();
        sentencia_else();
        bloqueActual = "";
    }

    private void sentencia_elif() {
        while (tokens.get(index).getLexema().equals("elif")) {
            tokenPalabraReservada("elif");
            expresion();
            consumirToken("DOSPUNTOS");
            bloqueActual = "elif";
            bloque();
        }
    }

    private void sentencia_else() {
        if (tokens.get(index).getLexema().equals("else")) {
            tokenPalabraReservada("else");
            consumirToken("DOSPUNTOS");
            bloqueActual = "else";
            bloque();
            bloqueActual = "";
        }
    }

    private void sentencia_for() {
        tokenPalabraReservada("for");
        consumirToken("IDENTIFICADOR");
        consumirToken("IN");
        expresion();
        consumirToken("DOSPUNTOS");
        bloqueActual = "for";
        bloque();
        bloqueActual = "";
        sentencia_else();
    }

    private void sentencia_while() {
        consumirToken("WHILE");
        expresion();
        consumirToken("DOSPUNTOS");
        bloqueActual = "WHILE";
        bloque();
        bloqueActual = "";
        sentencia_else();
    }

    private void sentencia_funcion() {
        consumirToken("DEF");
        String nombreFuncion = tokens.get(index);
        consumirToken("IDENTIFICADOR");
        consumirToken("(");
        List<String> parametros = lista_parametros();
        consumirToken(")");
        consumirToken("DOSPUNTOS");
        bloqueActual = "FUNCION";
        bloque();
        //FunctionDefinition funcion = new FunctionDefinition(nombreFuncion, parametros, bloqueActual);
        // funciones.put(nombreFuncion, funcion);
        bloqueActual = "";
    }

    private List<String> lista_parametros() {
        List<String> parametros = new ArrayList<>();
        if (tokens.get(index).equals("IDENTIFICADOR")) {
            parametros.add(tokens.get(index));
            consumirToken("IDENTIFICADOR");
            while (tokens.get(index).equals(",")) {
                consumirToken(",");
                parametros.add(tokens.get(index));
                consumirToken("IDENTIFICADOR");
            }
        }
        return parametros;
    }

    private void bloque() {
        sentencia();
        while (tokens.get(index).equals("INDENT")) {
            consumirToken("INDENT");
            sentencia();
            consumirToken("DEDENT");
        }
    }

    private void asignacion() {
        String identificador = tokens.get(index).getTipoToken().toString();
        consumirToken("IDENTIFICADOR");
        if (tokens.get(index).equals("ASIGNACION")) {
            consumirToken("ASIGNACION");
            expresion();
            variables.put(identificador, 0); // Solo para demostración, puedes asignar un valor real aquí
        } else {
            throw new RuntimeException("Error de sintaxis: asignación no válida");
        }
    }

    private Object expresion() {
        if (tokens.get(index).equals("BOOLEANO")) {
            consumirToken("BOOLEANO");
        } else {
            Object left = comparacion();
            if (tokens.get(index).getTipoToken().equals("ASIGNACION")) {
                String operador = tokens.get(index++).getLexema();
                Object right = expresion();
                switch (operador) {
                    case "=":

                        break;
                    default:
                        throw new AssertionError();
                }
            }

        }
    }

    private Object comparacion() {
        Object resultado = null;
        if (tokens.get(index).getTipoToken().equals("IDENTIFICADOR")) {
            consumirToken("IDENTIFICADOR");
            if (tokens.get(index).getTipoToken().equals("ARITMETICO")) {
                consumirToken("ARITMETICO");
                resultado = expresion();
            } else if (tokens.get(index).getTipoToken().equals("TERNARIO")) {
                consumirToken("TERNARIO");
                Object condicion = comparacion();
                consumirToken("ELSE");
                Object valorTrue = comparacion();
                consumirToken("TERNARIO");
                resultado = (boolean) condicion ? valorTrue : valorFalse;
            }
        } else {
            throw new RuntimeException("Error de sintaxis: comparación no válida");
        }
        return resultado;
    }

    private boolean vacio(String token) {
        return token.equals("");
    }

    private void match(String expectedToken) {
        if (tokens.get(index).equals(expectedToken)) {
            consumirToken(expectedToken);
        } else {
            throw new RuntimeException("Error de sintaxis: se esperaba '" + expectedToken + "'");
        }
    }
    */
}
