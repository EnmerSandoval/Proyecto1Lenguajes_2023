/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package controller;

/**
 *
 * @author Usuario
 */
public enum Gramatica {
   
    //Simbolos Aritmeticos
    SUMASIGNO,
    RESTASIGNO,
    EXPONENTESIGNO,
    DIVISIONSIGNO,
    MODULOSIGNO,
    MULTIPLICACIONSIGNO,
    COMENTARIO,
    IDENTIFICADOR,
    
    //Asignacion
    ASIGNACION_ADICION,
    ASIGNACION_SUSTRACCION,
    ASIGNACION_MULTIPLICACION,
    ASIGNACION_DIVISION,
    ASIGNACION_RESIDUO,
    
    //Comparacion
    IGUAL,
    IGUALIGUAL,
    DIFERENTE,
    MAYOR_QUE,
    MENOR_QUE,
    MAYOR_O_IGUAL_QUE,
    MENOR_O_IGUAL_QUE,
    ADMIRACION,
    ASIGNACION,
    
    //SIMBOLOS
    PARENTESISAPERTURA,
    PARENTESISCIERRE,
    LLAVEAPERTURA,
    LLAVECIERRE,
    CORCHETEAPERTURA,
    CORCHETECIERRE,
    COMA,
    PUNTO,
    PUNTO_Y_COMA,
    DOS_PUNTOS,
    COMILLASSIMPLES,
    COMILLASDOBLES,
    BARRAINVERSA,
    ADMIRACIONABIERTO,
    ADMIRACIONCERRADO,
    INTERROGACIONAPERTURA,
    INTERROGACIONCIERRE,
    
    
      //Numeros
    CERO,
    UNO,
    DOS,
    TRES,
    CUATRO,
    CINCO,
    SEIS,
    SIETE,
    OCHO,
    NUEVE,
    //Logicos 
    Y,
    O,
    NEGACION,
    
    //Numeros
    ENTERO,
    DECIMAL,
    CADENACONSTANTE,
    BOOLEANAS,
    
    //Palabras reservadas
    AND,
    AS,
    ASSERT,
    BREAK,
    CLASS,
    CONTINUE,
    DEF,
    DEL,
    ELIF,
    ELSE,
    EXCEPT,
    FALSE,
    FINALLY,
    FOR,
    FROM,
    GLOBAL,
    IF,
    IMPORT,
    IN,
    IS,
    LAMBDA,
    NONE,
    NONLOCAL,
    NOT,
    OR,
    PASS,
    RAISE,
    RETURN,
    TRUE,
    TRY,
    WHILE,
    WITH,
    YIELD,
    PRINT,
    
    
}
