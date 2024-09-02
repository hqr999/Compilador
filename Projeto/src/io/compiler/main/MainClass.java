package io.compiler.main;

import org.antlr.v4.runtime.CommonTokenStream;

import org.antlr.v4.runtime.CharStreams;

import io.compiler.core.GramaticaLexer;
import io.compiler.core.GramaticaParser;

public class MainClass {
    public static void main(String[] args) {
    	try {
    		GramaticaLexer lexer;
    		GramaticaParser parser;
    		
    		// Criamos o analisador léxico a apartir da leitura de um arquivo
    		lexer = new GramaticaLexer(CharStreams.fromFileName("input.in"));
    		
    		//Vindo do analisador conseguimos o fluxo de tokens
    		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    		
    		//Parser a partir do token stream
    		parser = new GramaticaParser(tokenStream);
    		
    		// Agora eu quero chamar 
    		System.out.println("Compilador: ");
    		parser.programa();
    		System.out.println("Compilação bem sucedida - Bom Trabalho");
    		parser.exibirVar();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	
    	
    }
}
