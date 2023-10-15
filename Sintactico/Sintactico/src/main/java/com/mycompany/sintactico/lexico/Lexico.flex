package com.mycompany.sintactico.lexico;




%%

%class Lexico
%type Token
%line
%column
L=[a-zA-Z_]+
D=[0-9]+
E=[\r\n]+
S=[\t ]{4}+
espacio=[ |\t|\|\n]+


%{
    public String lexema;    
    private int contador;
  

    
%}



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

AND | OR | NOT | and | or | not {lexema=yytext(); return new Token(TipoToken.LOGICO, yyline+1, yycolumn+1, yytext());}

"in"|"not in" {return new Token(TipoToken.MEMBRESIAOPERADOR, yyline+1, yycolumn+1, yytext());}

"is"|"is not"|"isn't" {return new Token(TipoToken.IDENTIFICADORES, yyline+1, yycolumn+1, yytext());}

True | False {return new Token(TipoToken.BOOLEANO, yyline+1, yycolumn+1, yytext());}

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

("#"[^\n]*) {/*Ignore*/}

 
. {return new Token(TipoToken.ERROR, yyline+1, yycolumn+1, yytext());}