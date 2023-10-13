package com.mycompany.sintactico.lexico;


import java.util.ArrayList;
import java.util.Stack;
import java.util.List;


%%

%class Lexico
%type Token
%line
%column
L=[a-zA-Z_]+
D=[0-9]+
E=[\r\n]+
S=[\t ]{4}+
espacio = [ ]{4}+
NUM_DECIMAL = (["0-9"])+ (("." | ",") (["0-9"])+)?


%{
    public String lexema;    
    private int contador;
    private int currentIndentationLevel = 0;
    private Stack<Integer> indentationStack = new Stack<>();

    private int indentacionMetodo(String texto) {
        int variable = 0;
        for (char c : texto.toCharArray()) {
            if (c == ' ' || c == '\t') {
                variable++;
            } else {
                break;
            }
        }
        int indentaciones = variable / 4;  // Divide por 4 para calcular el nivel de indentación
        return indentaciones;
    }

    
%}

%init{
    contador = 1;
    indentationStack.push(0); 
%init}

%%

and |
as |
assert |
break |
class |
continue |
def |
del |
elif |
else |
except |
finally |
for |
from |
global |
if |
import |
in |
lambda |
is |
None |
nonlocal |
print |
pass |
raise |
return |
try |
while |
with |
yield {lexema=yytext(); return new Token(TipoToken.PALABRA_RESERVADA, yyline+1, yycolumn+1, lexema);}

{espacio} {/*Ignore*/}
{E}+ {return new Token(TipoToken.NEWLINE, yyline+1, yycolumn+1, "");}

{espacio}+ {

    int indentacion = indentacionMetodo(yytext());
    System.out.println("valor de la identacion es: " + indentacion);
    System.out.println("valor de currentIdentationLevel es: " + currentIndentationLevel); 
    contador++;
    if (indentacion > currentIndentationLevel) {
        // Incrementar el nivel de indentación
        currentIndentationLevel = indentacion;
        indentationStack.push(indentacion);
        return new Token(TipoToken.INDENT, yyline + 1, yycolumn + 1, "");
    } else if (indentacion < currentIndentationLevel) {
        // Reducir el nivel de indentación
        System.out.println("Aca esta la verificacion si es menor");
        while (indentationStack.peek() > indentacion) {
            indentationStack.pop();
            contador++;
            return new Token(TipoToken.DEDENT, yyline + 1, yycolumn + 1, "");
        }
    }
}

[\t] {
    int indentacion = indentacionMetodo(yytext());
    contador++;
    if (indentacion > currentIndentationLevel) {
        // Incrementar el nivel de indentación
        currentIndentationLevel = indentacion;
        indentationStack.push(indentacion);
        return new Token(TipoToken.INDENT, yyline + 1, yycolumn + 1, "");
    } else if (indentacion < currentIndentationLevel) {
        // Reducir el nivel de indentación
        while (indentationStack.peek() > indentacion) {
            indentationStack.pop();
            contador++;
            return new Token(TipoToken.DEDENT, yyline + 1, yycolumn + 1, "");
        }
    }
}

AND | OR | NOT | and | or | not {lexema=yytext(); return new Token(TipoToken.LOGICO, yyline+1, yycolumn+1, yytext());}

"in"|"not in" {return new Token(TipoToken.MEMBRESIAOPERADOR, yyline+1, yycolumn+1, yytext());}

"is"|"is not"|"isn't" {return new Token(TipoToken.IDENTIFICADORES, yyline+1, yycolumn+1, yytext());}

True | False {return new Token(TipoToken.BOOLEANO, yyline+1, yycolumn+1, yytext());}

"&"|"|"|"^"|"~"|"<<"|">>" {return new Token(TipoToken.BITOPERADOR, yyline+1, yycolumn+1, yytext());}

"=" {return new Token(TipoToken.ASIGNACION, yyline+1, yycolumn+1, yytext());}
"(" {return new Token(TipoToken.PARENTESISAPERTURA, yyline+1, yycolumn+1, yytext());}
")" {return new Token(TipoToken.PARENTESISCIERRE, yyline+1, yycolumn+1, yytext());}

"{" {return new Token(TipoToken.LLAVEAPERTURA, yyline+1, yycolumn+1, yytext());}
"}" {return new Token(TipoToken.LLAVECIERRE, yyline+1, yycolumn+1, yytext());}

"[" {return new Token(TipoToken.CORCHETEAPERTURA, yyline+1, yycolumn+1, yytext());}
"]" {return new Token(TipoToken.CORCHETECIERRE, yyline+1, yycolumn+1, yytext());}
"," {return new Token(TipoToken.COMA, yyline+1, yycolumn+1, yytext());}
":" {return new Token(TipoToken.DOSPUNTOS, yyline+1, yycolumn+1, yytext());}
";" {return new Token(TipoToken.PUNTOCOMA, yyline+1, yycolumn+1, yytext());}
"==" | "!=" | "<" | ">" | ">=" | "<=" {return new Token(TipoToken.COMPARACION, yyline+1, yycolumn+1, yytext());}
"+=" | "-+" | "*+" | "@=" | "/=" | "%=" | "&=" | "|=" | "^=" | "<<=" | ">>=" | "**=" | "//=" {return new Token(TipoToken.ASIGNACION, yyline+1, yycolumn+1, yytext());}
"+" | "-" | "*" | "/" | "%" | "**" | "//" {return new Token(TipoToken.ARITMETICO, yyline+1, yycolumn+1, yytext());}

{NUM_DECIMAL} { return new Token(TipoToken.DECIMAL, yyline+1, yycolumn+1, yytext());}

{L}({L}|{D})* {
    lexema = yytext();
    return new Token(TipoToken.IDENTIFICADOR, yyline + 1, yycolumn + 1, lexema);
}

{D}+ {   
    lexema = yytext();
    return new Token(TipoToken.CONSTANTE, yyline + 1, yycolumn + 1, lexema);
}

("-{D}+")|{D}+ {lexema=yytext(); return new Token(TipoToken.CONSTANTE, yyline+1, yycolumn+1, lexema);}

("-"?{D}+"."{D}+) | {D}+ {lexema=yytext(); return new Token(TipoToken.CONSTANTE, yyline+1, yycolumn+1, lexema);}

\"([^\"\\\\]|\\\\.)*\"|'([^'\\\\]|\\\\.)*' {lexema=yytext(); return new Token(TipoToken.CADENA, yyline+1, yycolumn+1, lexema);}

("#"[^\n]*) {lexema=yytext(); return new Token(TipoToken.COMENTARIO, yyline+1, yycolumn+1, lexema);}

 
. {return new Token(TipoToken.ERROR, yyline+1, yycolumn+1, yytext());}