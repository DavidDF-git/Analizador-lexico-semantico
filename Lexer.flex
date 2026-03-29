package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+
%{
    public String lexeme;
%}
%%

/* Espacios en blanco */
{espacio} {/*Ignore*/}

/* Comentarios */
( "//"(.)* ) {/*Ignore*/}

/* Salto de linea */
( "\n" ) {return Linea;}

/* Comillas */
( "\"" ) {lexeme=yytext(); return Comillas;}

/* Tipos de datos */
( byte | int | char | long | float | double ) {lexeme=yytext(); return T_dato;}

/* Tipo de dato String */
( String ) {lexeme=yytext(); return Cadena;}

/* Palabras reservadas */
( if )          { lexeme=yytext(); return If; }
( else )        { lexeme=yytext(); return Else; }
( do )          { lexeme=yytext(); return Do; }
( while )       { lexeme=yytext(); return While; }
( for )         { lexeme=yytext(); return For; }
( include )     { lexeme=yytext(); return Include; }
( printf )      { lexeme=yytext(); return Printf; }
( scanf )       { lexeme=yytext(); return Scanf; }
( main )        { lexeme=yytext(); return Main; }

/* Operadores */
"="     { lexeme=yytext(); return Igual; }
"+"     { lexeme=yytext(); return Suma; }
"-"     { lexeme=yytext(); return Resta; }
"*"     { lexeme=yytext(); return Multiplicacion; }
"/"     { lexeme=yytext(); return Division; }
"%"     { lexeme=yytext(); return Modulo; }

"++" | "--"                             { lexeme=yytext(); return Op_incremento; }
"&&" | "||" | "!" | "&" | "|"           { lexeme=yytext(); return Op_logico; }
">" | "<" | "==" | "!=" | ">=" | "<="   { lexeme=yytext(); return Op_relacional; }
"+=" | "-=" | "*=" | "/=" | "%="       { lexeme=yytext(); return Op_atribucion; }

/* Booleanos */
( true | false ) { lexeme=yytext(); return Op_booleano; }

/* Delimitadores */
"("     { lexeme=yytext(); return Parentesis_a; }
")"     { lexeme=yytext(); return Parentesis_c; }
"{"     { lexeme=yytext(); return Llave_a; }
"}"     { lexeme=yytext(); return Llave_c; }
"["     { lexeme=yytext(); return Corchete_a; }
"]"     { lexeme=yytext(); return Corchete_c; }
";"     { lexeme=yytext(); return P_coma; }

/* Símbolos adicionales */
"#"     { lexeme=yytext(); return Numeral; }
"$"     { lexeme=yytext(); return Dolar; }
"\\"    { lexeme=yytext(); return Backslash; }
"."     { lexeme=yytext(); return Punto; }
":"     { lexeme=yytext(); return Dos_puntos; }
"?"     { lexeme=yytext(); return Interrogacion_c; }
"¿"     { lexeme=yytext(); return Interrogacion_a; }
"¡"     { lexeme=yytext(); return Exclamacion_a; }


/* Identificadores */
{L}({L}|{D})* { lexeme=yytext(); return Identificador; }

/* Números (enteros y negativos entre paréntesis) */
("(-"{D}+")") | {D}+ { lexeme=yytext(); return Numero; }

/* Cualquier otro símbolo se marca como error */
. { return ERROR; }