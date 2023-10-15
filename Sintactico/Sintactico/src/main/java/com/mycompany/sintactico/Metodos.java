package com.mycompany.sintactico;

import com.mycompany.sintactico.lexico.Sintaxis;
import com.mycompany.sintactico.lexico.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author enmer
 */
public class Metodos {

    private ArrayList<Token> tokens = new ArrayList<>();
    private int index;
    private int funciones = 0;
    private Map<Integer, String> funcionesConteo = new HashMap<>();
    private Map<String, Object> variables = new HashMap<>();
    private ArrayList<String> erroresSintacticos = new ArrayList<>();
    private ArrayList<Sintaxis> errorSintaxis = new ArrayList<>();

    public Metodos(ArrayList<Token> tokens) {
        this.tokens = tokens;
        this.index = 0;
        if (analizar()) {
            System.out.println("Se completó la sentencia.");
        } else {
            System.out.println("No funciona.");
            for (String error : erroresSintacticos) {
                System.out.println(error);
            }
        }
    }

    public boolean analizar() {
        try {
            sentencia();
            return index == tokens.size();
        } catch (RuntimeException e) {
            agregarErrorSintactico("Error de sintaxis: " + e.getMessage(), 0, 0);
            return false;
        }
    }

    private void agregarErrorSintactico(String mensaje, int fila, int columna) {
        erroresSintacticos.add(mensaje);
        errorSintaxis.add(new Sintaxis(mensaje, fila, columna));
        index++;
    }

    private void sentencia() {
        System.out.println("El token es de tipo: " + tokens.get(index).getTipoToken());
        if (tokens.get(index).getTipoToken().toString().equals("PALABRA_RESERVADA")) {
            if (tokens.get(index).getLexema().toString().equals("if")) {
                sentencia_if();
            } else if (tokens.get(index).getLexema().toString().equals("for")) {
                sentencia_for();
            } else if (tokens.get(index).getLexema().toString().equals("while")) {
                sentencia_while();
            } else if (tokens.get(index).getLexema().toString().equals("def")) {
                //sentencia_funcion();
            }
        } else if (tokens.get(index).getTipoToken().toString().equals("IDENTIFICADOR")) {
            System.out.println("Entro al identificador");
            if (index + 1 < tokens.size() && tokens.get(index + 1).getTipoToken().toString().equals("ASIGNACION")) {
                System.out.println("Andamos en asignacion");
                asignacion();
            } else {
                System.out.println("Andamos en declaracion");
                index++;
                sentencia();
                //declaracionVariable();
            }
        } else {
            agregarErrorSintactico("Error de sintaxis ", tokens.get(index).getFila(), tokens.get(index).getColumna());
        }
    }

    private void consumirToken(String expectedToken) {
        if (index < tokens.size() && tokens.get(index).getTipoToken().toString().equals(expectedToken)) {
            index++;
        } else {
            agregarErrorSintactico("Error de sintaxis ", tokens.get(index).getFila(), tokens.get(index).getColumna());
        }
    }

    private void tokenPalabraReservada(String token) {
        if (index < tokens.size() && tokens.get(index).getLexema().equals(token)) {
            index++;
        } else {
            agregarErrorSintactico("Error de sintaxis ", tokens.get(index).getFila(), tokens.get(index).getColumna());
        }
    }

    private void sentencia_if() {
        tokenPalabraReservada("if");
        expresionCondicional();
        consumirToken("DOSPUNTOS");
        sentencia_elif();
        sentencia_else();
        sentencia();
    }

    private void expresionAsignacion() {
        if (tokens.get(index).getTipoToken().toString().equals("BOOLEANO")) {
            consumirToken("BOOLEANO");
        } else if (tokens.get(index).getTipoToken().toString().equals("IDENTIFICADOR")) {
            consumirToken("IDENTIFICADOR");
        } else if(tokens.get(index).getTipoToken().toString().equals("CADENA")){
            consumirToken("CADENA");
        } else if (tokens.get(index).getTipoToken().equals("CONSTANTE") || tokens.get(index).getTipoToken().equals("ARITMETICO")) {
            expresionAritmetica();
        } else {
            agregarErrorSintactico("Error de sintaxis ", tokens.get(index).getFila(), tokens.get(index).getColumna());
        }
    }

    private void expresionCondicional() {
        if (tokens.get(index).getTipoToken().toString().equals("BOOLEANO")) {
            consumirToken("BOOLEANO");
        } else if (tokens.get(index).getTipoToken().toString().equals("IDENTIFICADOR")) {
            consumirToken("IDENTIFICADOR");
        } else if (tokens.get(index).getTipoToken().equals("CONSTANTE") || tokens.get(index).getTipoToken().equals("ARITMETICO")) {
            expresionAritmetica();
        } else {
            agregarErrorSintactico("Error de sintaxis ", tokens.get(index).getFila(), tokens.get(index).getColumna());
        }
    }

    //"==" | "!=" | "<" | ">" | ">=" | "<="
    private boolean esOperadorRelacional(String operador) {
        return operador.equals("==") || operador.equals("<") || operador.equals(">")
                || operador.equals("<=") || operador.equals(">=") || operador.equals("!=");
    }

    private void sentencia_elif() {
        while (tokens.get(index).getLexema().equals("elif")) {
            tokenPalabraReservada("elif");
            expresionCondicional();
            consumirToken("DOSPUNTOS");
        }
    }

    private void sentencia_else() {
        if (tokens.get(index).getLexema().equals("else")) {
            tokenPalabraReservada("else");
            consumirToken("DOSPUNTOS");
        }
        sentencia();
    }

    private void expresionFor() {
        if (tokens.get(index).getTipoToken().toString().equals("CONSTANTE")) {
            consumirToken("CONSTANTE");
            if (tokens.get(index).getTipoToken().toString().equals("COMPARACION")) {
                consumirToken("COMPARACION");
                if (tokens.get(index).getTipoToken().toString().equals("CONSTANTE")) {
                    consumirToken("CONSTANTE");
                } else {
                    agregarErrorSintactico("Error de sintaxis se esperaba una constante ", tokens.get(index).getFila(), tokens.get(index).getColumna());
                }
            } else {
                agregarErrorSintactico("Error de sintaxis se esperaba un token COMPARACION", tokens.get(index).getFila(), tokens.get(index).getColumna());
            }
        } else {
            agregarErrorSintactico("Error de sintaxis", tokens.get(index).getFila(), tokens.get(index).getColumna());
        }
    }

    private void sentencia_for() {
        tokenPalabraReservada("for");
        consumirToken("IDENTIFICADOR");
        consumirToken("MEMBRESIAOPERADOR");
        expresionFor();
        consumirToken("DOSPUNTOS");
        sentencia_else();
        sentencia();
    }

    private void sentencia_while() {
        tokenPalabraReservada("while");
        expresionCondicional();
        consumirToken("DOSPUNTOS");
        sentencia_else();
        sentencia();
    }

    /* private void sentencia_funcion() {
        consumirToken("DEF");
 //       String nombreFuncion = tokens.get(index);
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
    }*/

 /* private List<String> lista_parametros() {
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
    }*/
 /*private void bloque() {
        sentencia();
        while (tokens.get(index).equals("INDENT")) {
            consumirToken("INDENT");
            sentencia();
            consumirToken("DEDENT");
        }
    }*/
    private void asignacion() {
        consumirToken("IDENTIFICADOR");
        if (tokens.get(index).getTipoToken().toString().equals("ASIGNACION")) {
            consumirToken("ASIGNACION");
            System.out.println("ando aca");
            expresionAsignacion();
        } else {
            agregarErrorSintactico("Hubo un error con la asignacion", tokens.get(index).getFila(), tokens.get(index).getColumna());
        }
        sentencia();
    }

    /* private void comparacion() {
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
    }*/
    private boolean vacio(String token) {
        return token.equals("");
    }

    private void expresionAritmetica() {
        if (tokens.get(index).getTipoToken().toString().equals("CONSTANTE")) {
            consumirToken("CONSTANTE");
            if (tokens.get(index).getTipoToken().toString().equals("ARITMETICO")) {
                consumirToken("ARITMETICO");
                if (index + 1 < tokens.size() && tokens.get(index + 1).getTipoToken().toString().equals("CONSTANTE")) {
                    expresionAritmetica();
                } else {
                    agregarErrorSintactico("Error de sintaxis se esperaba una constante ", tokens.get(index).getFila(), tokens.get(index).getColumna());
                }
            } else {
                agregarErrorSintactico("Error de sintaxis se esperaba un token Aritmetico", tokens.get(index).getFila(), tokens.get(index).getColumna());
            }
        } else if (tokens.get(index).getTipoToken().toString().equals("ARITMETICO") && (tokens.get(index).getLexema().toString().equals("+") || tokens.get(index).getLexema().equals("-"))) {
            consumirToken("ARITMETICO");
            if (index + 1 < tokens.size() && tokens.get(index + 1).getTipoToken().toString().equals("CONSTANTE")) {
                expresionAritmetica();
            } else {
                agregarErrorSintactico("Error sintactico se esperaba una CONSTANTE", tokens.get(index).getFila(), tokens.get(index).getColumna());
            }
        } else {
            agregarErrorSintactico("Error en la expresion aritmetica ", tokens.get(index).getFila(), tokens.get(index).getColumna());
        }
    }

    private int obtenerPrecedencia(String operador) {
        switch (operador) {
            case "//":
                return 1;
            case "+":
            case "-":
                return 2;
            case "%":
                return 3;
            case "*":
            case "/":
                return 4;
            case "**":
                return 5;
            default:
                return 0;
        }
    }
}
