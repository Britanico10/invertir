grammar Formulas;

indicador: expresion #printExpr
;

expresion: expresion op=('*'|'/') expresion #muldiv
| expresion op=('+'|'-') expresion #sumres
| NUMERO #double
| INDICADOR #indi
| CUENTA #cuenta
| '(' expresion ')' #paren
;

CUENTA: 'ebitda' | 'fds' | 'fCashFlow' | 'ingNetoOpCont' | 'ingNetoOpDiscont' | 'deuda' | 'capitalPropio' | 'inicioActividad' ;
MAS: '+';
MENOS: '-';
MUL: '*';
DIV: '/';
IGUAL: '=';
MAYOR: '>';
MENOR: '<';
MAYOROIGUAL: '>=';
MENOROIGUAL: '<=';
PA: '(';
PC: ')';
INDICADOR: [a-zA-Z]+ ;
NUMERO: [0-9]+;

WS : [ \t\r\n]+ -> skip ;