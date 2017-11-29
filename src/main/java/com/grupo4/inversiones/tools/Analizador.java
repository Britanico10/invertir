package com.grupo4.inversiones.tools;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import com.grupo4.FormulasLexer;
import com.grupo4.FormulasParser;
import com.grupo4.inversiones.entidades.Empresa;

public class Analizador {
	public static Double evaluar(String formula, Empresa empresa, int periodo){
		
    	CharStream charStream = new ANTLRInputStream(formula);
		FormulasLexer lexer = new FormulasLexer(charStream);
    	CommonTokenStream tokens = new CommonTokenStream(lexer);
    	FormulasParser parser = new FormulasParser(tokens);
    	ParseTree tree = parser.indicador(); //parse
    	
    	//System.out.println(tree.toStringTree(parser));
    	
    	Visitor visitor = new Visitor(empresa,periodo);
    	return visitor.visit(tree);
		
	}

}
