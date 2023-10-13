package com.mycompany.sintactico;

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

    private List<Token> tokens = new ArrayList<>();
    private int index;
    private Map<String, Object> variables = new HashMap<>();
    private Map<String, Object> funciones = new HashMap<>();
    private String bloqueActual = "";

    public Metodos(List<Token> tokens, int index) {
        this.tokens = tokens;
        this.index = index;
        if (analizar()) {
        System.out.println("Se completo la sentencia");            
        } else {
            System.out.println("no funciona");
        }
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
        System.out.println("Entre en sentencia");
        System.out.println("El token es de tipo: " + tokens.get(index).getTipoToken());
        if (tokens.get(index).getTipoToken().equals("PALABRA_RESERVADA")) {
            if (tokens.get(index).getLexema().equals("if")) {
                sentencia_if();
            } else if (tokens.get(index).getLexema().equals("for")) {
                sentencia_for();
            } else if (tokens.get(index).getLexema().equals("while")) {
                sentencia_while();
            } else if (tokens.get(index).getLexema().equals("def")) {
               // sentencia_funcion();
            }
        } else if (tokens.get(index).getTipoToken().toString().equals("IDENTIFICADOR")) {
            System.out.println("Entro al identificador");
            if (index + 1 < tokens.size() && tokens.get(index + 1).getTipoToken().toString().equals("ASIGNACION")) {
                System.out.println("Andamos en asignacion");
                asignacion();
            } else {
                System.out.println("Andamos en declaracion");
                declaracionVariable();
            }
        } else {
            throw new RuntimeException("Error de sintaxis: token inesperado");
        }
    }
    
    
    private void declaracionVariable() {
        Object valor;
        String nombreVariable = tokens.get(index).getLexema();
        consumirToken("IDENTIFICADOR");

        if (tokens.get(index).equals("ASIGNACION")) {
            consumirToken("ASIGNACION");

            if (tokens.get(index).match(".*[+\\-*/%].*")) {
                valor = expresionAritmetica();
            } else {
                valor = expresion();
            }

            variables.put(nombreVariable, valor);
        } else {
            if (tokens.get(index).equals("COMA")) {
                List<String> variablesDeclaradas = new ArrayList<>();
                variablesDeclaradas.add(nombreVariable);

                while (tokens.get(index).equals("COMA")) {
                    consumirToken("COMA");
                    String otraVariable = tokens.get(index).getLexema();
                    consumirToken("IDENTIFICADOR");
                    variablesDeclaradas.add(otraVariable);
                }

                if (tokens.get(index).equals("ASIGNACION")) {
                    consumirToken("ASIGNACION");
                    List<Object> valores = new ArrayList<>();

                    if (tokens.get(index).match(".*[+\\-*/%].*")) {
                        valores.add(expresionAritmetica());
                    } else {
                        valores.add(expresion());
                    }

                    while (tokens.get(index).equals("COMA")) {
                        consumirToken("COMA");

                        if (tokens.get(index).match(".*[+\\-*/%].*")) {
                            valores.add(expresionAritmetica());
                        } else {
                            valores.add(expresion());
                        }
                    }

                    if (variablesDeclaradas.size() != valores.size()) {
                        throw new RuntimeException("Error de sintaxis: número de variables y valores no coincide.");
                    }

                    for (int i = 0; i < variablesDeclaradas.size(); i++) {
                        variables.put(variablesDeclaradas.get(i), valores.get(i));
                    }
                } else {
                    for (String variable : variablesDeclaradas) {
                        variables.put(variable, null);
                    }
                }
            } else {
                variables.put(nombreVariable, null);
            }
        }
    }

    private boolean expresion() {
        if (tokens.get(index).getTipoToken().toString().equals("BOOLEANO")) {
            return true;
        } else if (tokens.get(index).getTipoToken().toString().equals("CADENA")) {
            return true;
        } else if (tokens.get(index).getTipoToken().toString().equals("CONSTANTE")) {
            return true;
        } else if (tokens.get(index).getTipoToken().toString().equals("DECIMAL")) {
            return true;
        } else {
            return false;
        }
    }

    private void consumirToken(String expectedToken) {
        if (index < tokens.size() && tokens.get(index).getTipoToken().toString().equals(expectedToken)) {
            index++;
        } else {
            throw new RuntimeException("Error de sintaxis: se esperaba '" + expectedToken + "'");
        }
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
        expresionCondicional();
        consumirToken("DOSPUNTOS");
        bloqueActual = "if";
       // bloque();
        sentencia_elif();
        sentencia_else();
        bloqueActual = "";
    }

    private boolean expresionCondicional() {
        if (tokens.get(index).equals("BOOLEANO")) {
            return Boolean.parseBoolean(tokens.get(index).getLexema());
        } else if (tokens.get(index).getTipoToken().equals("IDENTIFICADOR")) {
            String identificador = tokens.get(index).getLexema();
            return evaluarComoBooleano(identificador);
        }// else if (esOperadorRelacional(tokens.get(index).getLexema())) {
        //}
        else if (tokens.get(index).getLexema().equals("not")) {
            // Para manejar el operador "not"
            consumirToken("not");
            boolean expresionNegada = expresionCondicional();
            return !expresionNegada;
        } else if (tokens.get(index).getLexema().equals("(")) {
            consumirToken("(");
            boolean resultado = expresionCondicional();
            consumirToken(")");
            return resultado;
        } else {
            throw new RuntimeException("Error de sintaxis: expresión condicional no válida en el condicional if");
        }
    }

    //"==" | "!=" | "<" | ">" | ">=" | "<="
    private boolean esOperadorRelacional(String operador) {
        return operador.equals("==") || operador.equals("<") || operador.equals(">")
                || operador.equals("<=") || operador.equals(">=") || operador.equals("!=");
    }

    private boolean evaluarComoBooleano(String identificador) {
        if (variables.containsKey(identificador)) {
            Object valor = variables.get(identificador);
            if (valor instanceof Boolean) {
                return (boolean) valor;
            } else {
                throw new RuntimeException("Error de tipo: la variable '" + identificador + "' no es booleana");
            }
        } else {
            throw new RuntimeException("Error: la variable '" + identificador + "' no está definida");
        }
    }

   /* private boolean evaluarComparacion() {
        boolean valor1, valor2;

        if (tokens.get(index).getLexema().equals("not")) {
            consumirToken("not");
            boolean expresionNegada = expresionCondicional();
            return !expresionNegada;
        }

        valor1 = expresionCondicional();

        String operador = tokens.get(index).getLexema();
        if (esOperadorRelacional(operador)) {
            consumirToken(operador);
        } else {
            throw new RuntimeException("Operador de comparación no válido: " + operador);
        }

        valor2 = expresionCondicional();

        switch (operador) {
            case "==":
                return valor1 == valor2;
            case "<":
                return valor1 < valor2;
            case ">":
                return valor1 > valor2;
            case "<=":
                return valor1 <= valor2;
            case ">=":
                return valor1 >= valor2;
            case "!=":
                return valor1 != valor2;
            default:
                throw new RuntimeException("Operador de comparación no válido: " + operador);
        }
    }*/

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

    private void bloque() {
        sentencia();
        while (tokens.get(index).equals("INDENT")) {
            consumirToken("INDENT");
            sentencia();
            consumirToken("DEDENT");
        }
    }

    private boolean asignacion() {
        String identificador = tokens.get(index).getTipoToken().toString();
        consumirToken("IDENTIFICADOR");
        if (tokens.get(index).getTipoToken().toString().equals("ASIGNACION")) {
            consumirToken("ASIGNACION");
            if (expresion()) {
                index++;
                return true;
            } else {
                System.out.println("Error de sintaxis: asignacion no valida");
                return false;
            }
        } else {
            throw new RuntimeException("Error de sintaxis: asignación no válida");
        }
    }

  /*  private void comparacion() {
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
*/
    private boolean vacio(String token) {
        return token.equals("");
    }

    private Object expresionAritmetica() {
        Object resultado = null;

        if (tokens.get(index).equals("CONSTANTE")) {
            resultado = parseConstant(tokens.get(index).getLexema());
            consumirToken("CONSTANTE");
        } else if (tokens.get(index).equals("ARITMETICO")) {
            String operador = tokens.get(index).getLexema();
            consumirToken("ARITMETICO");
            Object operando = expresionAritmetica();

            switch (operador) {
                case "+":
                    resultado = (double) resultado + (double) operando;
                    break;
                case "-":
                    resultado = (double) resultado - (double) operando;
                    break;
                case "*":
                    resultado = (double) resultado * (double) operando;
                    break;
                case "/":
                    resultado = (double) resultado / (double) operando;
                    break;
                case "%":
                    resultado = (double) resultado % (double) operando;
                    break;
                case "**":
                    resultado = Math.pow((double) resultado, (double) operando);
                    break;
                case "//":
                    resultado = (double) resultado / (double) operando;
                    break;
                default:
                    throw new RuntimeException("Operador aritmético no válido");
            }
        }

        return resultado;
    }

    private Object parseConstant(String value) {
        if (value.contains(".")) {
            return Double.parseDouble(value);
        } else {
            return Integer.parseInt(value);
        }
    }
}
