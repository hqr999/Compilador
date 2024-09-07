package io.compiler.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStreams;

import io.compiler.core.UFABCGrammarLexer;
import io.compiler.core.UFABCGrammarParser;
import io.compiler.core.ast.Program;

public class MainClass {
	public static void main(String[] args) {
		try {
			UFABCGrammarLexer  lexer;
			UFABCGrammarParser parser;
			
			lexer = new UFABCGrammarLexer(CharStreams.fromFileName("program.in"));
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			
			
			// crio o parser a partir do tokenStream
			parser = new UFABCGrammarParser(tokenStream);
			
			System.out.println("Starting Expression Analysis");
			parser.programa();
			System.out.println("Analysis Finished!");
			
			Program program = parser.getProgram();
			
			System.out.println(program.generateTarget());
			try {
				File f = new File(program.getName()+".java");
				FileWriter fr = new FileWriter(f);
				PrintWriter pr = new PrintWriter(fr);
				pr.println(program.generateTarget());
				pr.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}