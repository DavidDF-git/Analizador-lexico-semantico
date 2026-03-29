package codigo;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
   private Symbol symbol(int type, Object value){
        int start = yycolumn;
        int end = yycolumn + yytext().length() - 1;
        return new Symbol(type, start, end, value);
    }
    private Symbol symbol(int type){
        int start = yycolumn;
        int end = yycolumn + yytext().length() - 1;
        return new Symbol(type, start, end);
    }
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Comillas */
( "\"" ) {return new Symbol(sym.Comillas, yyline, yycolumn, yytext());}

/* Tipos de datos */
( byte | int | char | long | float | double ) { return new Symbol(sym.T_dato, yyline, yycolumn, yytext()); }

/* Palabras reservadas */
( String )      { return new Symbol(sym.Cadena, yyline, yycolumn, yytext()); }
( if )          { return new Symbol(sym.If, yyline, yycolumn, yytext()); }
( else )        { return new Symbol(sym.Else, yyline, yycolumn, yytext()); }
( do )          { return new Symbol(sym.Do, yyline, yycolumn, yytext()); }
( while )       { return new Symbol(sym.While, yyline, yycolumn, yytext()); }
( for )         { return new Symbol(sym.For, yyline, yycolumn, yytext()); }
( include )     { return new Symbol(sym.Include, yyline, yycolumn, yytext()); }
( printf )      { return new Symbol(sym.Printf, yyline, yycolumn, yytext()); }
( scanf )       { return new Symbol(sym.Scanf, yyline, yycolumn, yytext()); }
( main )        { return new Symbol(sym.Main, yyline, yycolumn, yytext()); }

/* Operadores aritméticos y otros */
"="     { return new Symbol(sym.Igual, yyline, yycolumn, yytext()); }
"+"     { return new Symbol(sym.Suma, yyline, yycolumn, yytext()); }
"-"     { return new Symbol(sym.Resta, yyline, yycolumn, yytext()); }
"*"     { return new Symbol(sym.Multiplicacion, yyline, yycolumn, yytext()); }
"/"     { return new Symbol(sym.Division, yyline, yycolumn, yytext()); }
"%"     { return new Symbol(sym.Modulo, yyline, yycolumn, yytext()); }

/* Operadores incremento/decremento */
"++" | "--" { return new Symbol(sym.Op_incremento, yyline, yycolumn, yytext()); }

/* Operadores lógicos */
"&&" | "||" | "!" | "&" | "|" { return new Symbol(sym.Op_logico, yyline, yycolumn, yytext()); }

/* Operadores relacionales */
">" | "<" | "==" | "!=" | ">=" | "<=" | "<<" | ">>" { return new Symbol(sym.Op_relacional, yyline, yycolumn, yytext()); }

/* Operadores de atribución */
"+=" | "-=" | "*=" | "/=" | "%=" { return new Symbol(sym.Op_atribucion, yyline, yycolumn, yytext()); }

/* Booleanos */
( true | false ) { return new Symbol(sym.Op_booleano, yyline, yycolumn, yytext()); }

/* Delimitadores y símbolos comunes */
"("     { return new Symbol(sym.Parentesis_a, yyline, yycolumn, yytext()); }
")"     { return new Symbol(sym.Parentesis_c,  yyline, yycolumn, yytext()); }
"{"     { return new Symbol(sym.Llave_a, yyline, yycolumn, yytext()); }
"}"     { return new Symbol(sym.Llave_c, yyline, yycolumn, yytext()); }
"["     { return new Symbol(sym.Corchete_a, yyline, yycolumn,  yytext()); }
"]"     { return new Symbol(sym.Corchete_c, yyline, yycolumn, yytext()); }
";"     { return new Symbol(sym.P_coma, yyline, yycolumn, yytext()); }
"#"     { return new Symbol(sym.Numeral, yyline, yycolumn, yytext()); }
"$"     { return new Symbol(sym.Dolar, yyline, yycolumn, yytext()); }
"\\"    { return new Symbol(sym.Backslash, yyline, yycolumn,  yytext()); }
"."     { return new Symbol(sym.Punto, yyline, yycolumn,  yytext()); }
":"     { return new Symbol(sym.Dos_puntos, yyline, yycolumn,  yytext()); }
"?"     { return new Symbol(sym.Interrogacion_c, yyline, yycolumn, yytext()); }
"¿"     { return new Symbol(sym.Interrogacion_a, yyline, yycolumn,  yytext()); }
"¡"     { return new Symbol(sym.Exclamacion_a, yyline, yycolumn,  yytext()); }


/* Identificadores */
{L}({L}|{D})* { return new Symbol(sym.Identificador, yyline, yycolumn, yytext()); }

/* Números (positivos y negativos entre paréntesis) */
("(-"{D}+")")|{D}+ { return new Symbol(sym.Numero, yyline, yycolumn,  yytext()); }

/* Cualquier otro carácter se marca como error */
. { return new Symbol(sym.ERROR, yyline, yycolumn, yytext()); }