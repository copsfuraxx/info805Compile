/* --------------------------Section de Code Utilisateur---------------------*/
package fr.usmb.m1isc.compilation.simple;
import java_cup.runtime.Symbol;

%%

/* -----------------Section des Declarations et Options----------------------*/
// nom de la class a generer
%class SimpleLexer
%unicode
%line   // numerotation des lignes
%column // numerotation caracteres par ligne

// utilisation avec CUP
// nom de la classe generee par CUP qui contient les symboles terminaux
%cupsym SimpleParserSym
// generation analyser lexical pour CUP
%cup

// code a ajouter dans la classe produite
%{

%}

/* definitions regulieres */
nombre      = ([1-9][0-9]*) | 0
point       = ";"
sep         = \s
commentaire = \/\*[^/*]*\*\/
variable = [a-z][a-zA-Z0-9]*

%% 
/* ------------------------Section des Regles Lexicales----------------------*/

/* regles */
{sep}+          { /* pas d'action */ }
{commentaire}+  { /* pas d'action */ }
"mod"           { return new Symbol(SimpleParserSym.MODULO   , yyline, yycolumn); }
"let"           { return new Symbol(SimpleParserSym.LET      , yyline, yycolumn); }
{variable}      { return new Symbol(SimpleParserSym.VAR      , yyline, yycolumn, yytext()); }
{nombre}        { return new Symbol(SimpleParserSym.NOMBRE   , yyline, yycolumn, Integer.parseInt(yytext())); }
"="             { return new Symbol(SimpleParserSym.EGAL     , yyline, yycolumn); }
"("             { return new Symbol(SimpleParserSym.LEFTBRAC , yyline, yycolumn); }
")"             { return new Symbol(SimpleParserSym.RIGHTBRAC, yyline, yycolumn); }
"*"             { return new Symbol(SimpleParserSym.FOIS     , yyline, yycolumn); }
"/"             { return new Symbol(SimpleParserSym.DIVISE   , yyline, yycolumn); }
"+"             { return new Symbol(SimpleParserSym.PLUS     , yyline, yycolumn); }
"-"             { return new Symbol(SimpleParserSym.MOINS    , yyline, yycolumn); }
{point}         { return new Symbol(SimpleParserSym.POINT    , yyline, yycolumn); }
.               { return new Symbol(SimpleParserSym.ERROR    , yyline, yycolumn); }
