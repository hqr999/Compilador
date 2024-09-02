// Generated from Gramatica.g4 by ANTLR 4.13.2
package io.compiler.core;

     import java.util.ArrayList;
     import io.compiler.types.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GramaticaParser}.
 */
public interface GramaticaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(GramaticaParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(GramaticaParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(GramaticaParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(GramaticaParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(GramaticaParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(GramaticaParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(GramaticaParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(GramaticaParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaParser#exprl}.
	 * @param ctx the parse tree
	 */
	void enterExprl(GramaticaParser.ExprlContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaParser#exprl}.
	 * @param ctx the parse tree
	 */
	void exitExprl(GramaticaParser.ExprlContext ctx);
}