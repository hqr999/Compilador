// Generated from UFABCGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exception.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class UFABCGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, OP=22, OP_AT=23, OPREL=24, OPLOG=25, 
		ID=26, NUM=27, NUMFRAC=28, VIRG=29, PV=30, AP=31, FP=32, DP=33, TEXTO=34, 
		WS=35;
	public static final int
		RULE_programa = 0, RULE_declaravar = 1, RULE_comando = 2, RULE_unusedFlag = 3, 
		RULE_cmdFor = 4, RULE_cmdWhile = 5, RULE_cmdIF = 6, RULE_opNot = 7, RULE_cmdAttrib = 8, 
		RULE_cmdLeitura = 9, RULE_cmdEscrita = 10, RULE_expr = 11, RULE_termo = 12, 
		RULE_exprl = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"programa", "declaravar", "comando", "unusedFlag", "cmdFor", "cmdWhile", 
			"cmdIF", "opNot", "cmdAttrib", "cmdLeitura", "cmdEscrita", "expr", "termo", 
			"exprl"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'inicio'", "'fim'", "'fimprog'", "'declare'", "'number'", 
			"'decimal'", "'text'", "'aPartirDe'", "'->'", "'close'", "'enquanto'", 
			"'inicioEnquanto'", "'fimEnquanto'", "'se'", "'entao'", "'senao'", "'fimse'", 
			"'not'", "'leia'", "'escreva'", null, "':='", null, null, null, null, 
			null, "','", "';'", "'('", "')'", "':'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "OP", "OP_AT", 
			"OPREL", "OPLOG", "ID", "NUM", "NUMFRAC", "VIRG", "PV", "AP", "FP", "DP", 
			"TEXTO", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "UFABCGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private HashMap<String,Var> symbolTable = new HashMap<String, Var>();
	    private ArrayList<Var> currentDecl = new ArrayList<Var>();
	    private Types currentType;
	    private Types leftType=null, rightType=null;
	    private Program program = new Program();
	    private String strExpr = "";
	    private IfCommand currentIfCommand;
	    private WhileCommand currentWhileCommand;
	    private AttribCommand currentAttribCommand;
	    
	    private Stack<ArrayList<Command>> stack = new Stack<ArrayList<Command>>();
	    private Stack<WhileCommand> whileStack = new Stack<WhileCommand>();
	    private Stack<IfCommand> ifStack = new Stack<IfCommand>();
	    private Stack<ForCommand> forStack = new Stack<ForCommand>();
	    
	    
	    public void updateType(){
	    	for(Var v: currentDecl){
	    	   v.setType(currentType);
	    	   symbolTable.put(v.getId(), v);
	    	}
	    }
	    public void exibirVar(){
	        for (String id: symbolTable.keySet()){
	        	System.out.println(symbolTable.get(id));
	        }
	    }
	    
	    public Program getProgram(){
	    	return this.program;
	    	}
	    
	    public boolean isDeclared(String id){
	    	return symbolTable.get(id) != null;
	    }
	    
	    public boolean isUsed(String id){
	    	return symbolTable.get(id).isUsed();
	    }

	public UFABCGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(UFABCGrammarParser.ID, 0); }
		public UnusedFlagContext unusedFlag() {
			return getRuleContext(UnusedFlagContext.class,0);
		}
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public ProgramaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programa; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterPrograma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitPrograma(this);
		}
	}

	public final ProgramaContext programa() throws RecognitionException {
		ProgramaContext _localctx = new ProgramaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_programa);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__0);
			setState(29);
			match(ID);
			 program.setName(_input.LT(-1).getText());
			                               stack.push(new ArrayList<Command>()); 
			                             
			setState(32); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(31);
				declaravar();
				}
				}
				setState(34); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__4 );
			setState(36);
			match(T__1);
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37);
				comando();
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 70291968L) != 0) );
			setState(42);
			unusedFlag();
			setState(43);
			match(T__2);
			setState(44);
			match(T__3);

			                  program.setSymbolTable(symbolTable);
			                  program.setCommandList(stack.pop());
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclaravarContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(UFABCGrammarParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(UFABCGrammarParser.ID, i);
		}
		public TerminalNode DP() { return getToken(UFABCGrammarParser.DP, 0); }
		public TerminalNode PV() { return getToken(UFABCGrammarParser.PV, 0); }
		public List<TerminalNode> VIRG() { return getTokens(UFABCGrammarParser.VIRG); }
		public TerminalNode VIRG(int i) {
			return getToken(UFABCGrammarParser.VIRG, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(T__4);
			 currentDecl.clear(); 
			setState(49);
			match(ID);
			 currentDecl.add(new Var(_input.LT(-1).getText()));
			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIRG) {
				{
				{
				setState(51);
				match(VIRG);
				setState(52);
				match(ID);
				 currentDecl.add(new Var(_input.LT(-1).getText()));
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(59);
			match(DP);
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(60);
				match(T__5);
				currentType = Types.NUMBER;
				}
				break;
			case T__6:
				{
				setState(62);
				match(T__6);
				currentType = Types.FRAC;
				}
				break;
			case T__7:
				{
				setState(64);
				match(T__7);
				currentType = Types.TEXT;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			 updateType(); 
			setState(69);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComandoContext extends ParserRuleContext {
		public CmdAttribContext cmdAttrib() {
			return getRuleContext(CmdAttribContext.class,0);
		}
		public CmdLeituraContext cmdLeitura() {
			return getRuleContext(CmdLeituraContext.class,0);
		}
		public CmdEscritaContext cmdEscrita() {
			return getRuleContext(CmdEscritaContext.class,0);
		}
		public CmdIFContext cmdIF() {
			return getRuleContext(CmdIFContext.class,0);
		}
		public CmdWhileContext cmdWhile() {
			return getRuleContext(CmdWhileContext.class,0);
		}
		public CmdForContext cmdFor() {
			return getRuleContext(CmdForContext.class,0);
		}
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitComando(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_comando);
		try {
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(71);
				cmdAttrib();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				cmdLeitura();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				cmdEscrita();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 4);
				{
				setState(74);
				cmdIF();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 5);
				{
				setState(75);
				cmdWhile();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 6);
				{
				setState(76);
				cmdFor();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnusedFlagContext extends ParserRuleContext {
		public UnusedFlagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unusedFlag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterUnusedFlag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitUnusedFlag(this);
		}
	}

	public final UnusedFlagContext unusedFlag() throws RecognitionException {
		UnusedFlagContext _localctx = new UnusedFlagContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_unusedFlag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			for (Var var : symbolTable.values()) {
			                  if (!var.isUsed()) {
			                     throw new UFABCSemanticException("Variable "+var.getId()+" declared but not used");
			                  }
			               }
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdForContext extends ParserRuleContext {
		public List<TerminalNode> NUM() { return getTokens(UFABCGrammarParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(UFABCGrammarParser.NUM, i);
		}
		public TerminalNode DP() { return getToken(UFABCGrammarParser.DP, 0); }
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdFor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdFor(this);
		}
	}

	public final CmdForContext cmdFor() throws RecognitionException {
		CmdForContext _localctx = new CmdForContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmdFor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(T__8);
			 stack.push(new ArrayList<Command>());
			                           forStack.push(new ForCommand());
			                           strExpr = "";
			                   
			setState(83);
			match(NUM);
			forStack.peek().setInicio(_input.LT(-1).getText());
			setState(85);
			match(T__9);
			setState(86);
			match(NUM);
			forStack.peek().setFim(_input.LT(-1).getText());
			setState(88);
			match(DP);
			setState(90); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(89);
				comando();
				}
				}
				setState(92); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 70291968L) != 0) );
			 forStack.peek().setRepeticao(stack.pop());
			setState(95);
			match(T__10);
			 stack.peek().add(forStack.pop()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdWhileContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public List<OpNotContext> opNot() {
			return getRuleContexts(OpNotContext.class);
		}
		public OpNotContext opNot(int i) {
			return getRuleContext(OpNotContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> OPREL() { return getTokens(UFABCGrammarParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(UFABCGrammarParser.OPREL, i);
		}
		public List<TerminalNode> OPLOG() { return getTokens(UFABCGrammarParser.OPLOG); }
		public TerminalNode OPLOG(int i) {
			return getToken(UFABCGrammarParser.OPLOG, i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdWhileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdWhile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdWhile(this);
		}
	}

	public final CmdWhileContext cmdWhile() throws RecognitionException {
		CmdWhileContext _localctx = new CmdWhileContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmdWhile);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__11);
			 stack.push(new ArrayList<Command>());
			                           whileStack.push(new WhileCommand());
			                           strExpr = "";
			                          
			setState(100);
			match(AP);
			setState(107);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				{
				setState(101);
				opNot();
				}
				break;
			case ID:
			case NUM:
			case NUMFRAC:
			case TEXTO:
				{
				setState(102);
				expr();
				setState(103);
				match(OPREL);
				 strExpr += _input.LT(-1).getText(); 
				setState(105);
				expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPLOG) {
				{
				{
				setState(109);
				match(OPLOG);
				 strExpr += _input.LT(-1).getText(); 
				setState(117);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__18:
					{
					setState(111);
					opNot();
					}
					break;
				case ID:
				case NUM:
				case NUMFRAC:
				case TEXTO:
					{
					setState(112);
					expr();
					setState(113);
					match(OPREL);
					 strExpr += _input.LT(-1).getText(); 
					setState(115);
					expr();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(124);
			match(FP);
			 whileStack.peek().setCriterioParada(strExpr);
			setState(126);
			match(T__12);
			setState(128); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(127);
				comando();
				}
				}
				setState(130); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 70291968L) != 0) );
			 whileStack.peek().setCycle(stack.pop());
			setState(133);
			match(T__13);
			 stack.peek().add(whileStack.pop()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdIFContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public List<OpNotContext> opNot() {
			return getRuleContexts(OpNotContext.class);
		}
		public OpNotContext opNot(int i) {
			return getRuleContext(OpNotContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> OPREL() { return getTokens(UFABCGrammarParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(UFABCGrammarParser.OPREL, i);
		}
		public List<TerminalNode> OPLOG() { return getTokens(UFABCGrammarParser.OPLOG); }
		public TerminalNode OPLOG(int i) {
			return getToken(UFABCGrammarParser.OPLOG, i);
		}
		public List<ComandoContext> comando() {
			return getRuleContexts(ComandoContext.class);
		}
		public ComandoContext comando(int i) {
			return getRuleContext(ComandoContext.class,i);
		}
		public CmdIFContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdIF; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdIF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdIF(this);
		}
	}

	public final CmdIFContext cmdIF() throws RecognitionException {
		CmdIFContext _localctx = new CmdIFContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdIF);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(T__14);
			 stack.push(new ArrayList<Command>());
			                      strExpr = "";
			                      ifStack.push(new IfCommand());
			                      currentIfCommand = new IfCommand();
			                    
			setState(138);
			match(AP);
			setState(145);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				{
				setState(139);
				opNot();
				}
				break;
			case ID:
			case NUM:
			case NUMFRAC:
			case TEXTO:
				{
				setState(140);
				expr();
				setState(141);
				match(OPREL);
				 strExpr += _input.LT(-1).getText(); 
				setState(143);
				expr();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPLOG) {
				{
				{
				setState(147);
				match(OPLOG);
				 strExpr += _input.LT(-1).getText(); 
				setState(155);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__18:
					{
					setState(149);
					opNot();
					}
					break;
				case ID:
				case NUM:
				case NUMFRAC:
				case TEXTO:
					{
					setState(150);
					expr();
					setState(151);
					match(OPREL);
					 strExpr += _input.LT(-1).getText(); 
					setState(153);
					expr();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(162);
			match(FP);
			 ifStack.peek().setExpression(strExpr); 
			setState(164);
			match(T__15);
			setState(166); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(165);
				comando();
				}
				}
				setState(168); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 70291968L) != 0) );
			 
			                  ifStack.peek().setTrueList(stack.pop());
			               
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(171);
				match(T__16);
				 stack.push(new ArrayList<Command>()); 
				setState(174); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(173);
					comando();
					}
					}
					setState(176); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 70291968L) != 0) );

				                   ifStack.peek().setFalseList(stack.pop());
				                 
				}
			}

			setState(182);
			match(T__17);

			                      stack.peek().add(ifStack.pop());
			               
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OpNotContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> OPREL() { return getTokens(UFABCGrammarParser.OPREL); }
		public TerminalNode OPREL(int i) {
			return getToken(UFABCGrammarParser.OPREL, i);
		}
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public List<TerminalNode> OPLOG() { return getTokens(UFABCGrammarParser.OPLOG); }
		public TerminalNode OPLOG(int i) {
			return getToken(UFABCGrammarParser.OPLOG, i);
		}
		public List<OpNotContext> opNot() {
			return getRuleContexts(OpNotContext.class);
		}
		public OpNotContext opNot(int i) {
			return getRuleContext(OpNotContext.class,i);
		}
		public OpNotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opNot; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterOpNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitOpNot(this);
		}
	}

	public final OpNotContext opNot() throws RecognitionException {
		OpNotContext _localctx = new OpNotContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_opNot);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			match(T__18);
			strExpr += "!(";
			setState(187);
			match(AP);
			setState(188);
			expr();
			setState(189);
			match(OPREL);
			 strExpr += _input.LT(-1).getText(); 
			setState(191);
			expr();
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OPLOG) {
				{
				{
				setState(192);
				match(OPLOG);
				 strExpr += _input.LT(-1).getText(); 
				setState(200);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__18:
					{
					setState(194);
					opNot();
					}
					break;
				case ID:
				case NUM:
				case NUMFRAC:
				case TEXTO:
					{
					setState(195);
					expr();
					setState(196);
					match(OPREL);
					 strExpr += _input.LT(-1).getText(); 
					setState(198);
					expr();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(207);
			match(FP);
			strExpr += ")";
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdAttribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(UFABCGrammarParser.ID, 0); }
		public TerminalNode OP_AT() { return getToken(UFABCGrammarParser.OP_AT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PV() { return getToken(UFABCGrammarParser.PV, 0); }
		public CmdAttribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdAttrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdAttrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdAttrib(this);
		}
	}

	public final CmdAttribContext cmdAttrib() throws RecognitionException {
		CmdAttribContext _localctx = new CmdAttribContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmdAttrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(ID);
			 if (!isDeclared(_input.LT(-1).getText())) {
			                       throw new UFABCSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
			                   }
			                   symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
			                   leftType = symbolTable.get(_input.LT(-1).getText()).getType();
			                   strExpr = "";
			                   currentAttribCommand = new AttribCommand();
			                   strExpr += _input.LT(-1).getText();
			                   currentAttribCommand.setAtribuido(strExpr);
			                   strExpr = "";
			                 
			setState(212);
			match(OP_AT);
			 strExpr += _input.LT(-1).getText();
			setState(214);
			expr();
			setState(215);
			match(PV);
			 currentAttribCommand.setExpression(strExpr); 
			                     stack.peek().add(currentAttribCommand);

			                 //System.out.println("Left  Side Expression Type = "+leftType);
			                 //System.out.println("Right Side Expression Type = "+rightType);
			                 if (leftType.getValue() < rightType.getValue()){
			                    throw new UFABCSemanticException("Type Mismatchig on Assignment");
			                 }
			                 rightType = null;
			              
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdLeituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public TerminalNode ID() { return getToken(UFABCGrammarParser.ID, 0); }
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(UFABCGrammarParser.PV, 0); }
		public CmdLeituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdLeitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdLeitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdLeitura(this);
		}
	}

	public final CmdLeituraContext cmdLeitura() throws RecognitionException {
		CmdLeituraContext _localctx = new CmdLeituraContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdLeitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(T__19);
			setState(220);
			match(AP);
			setState(221);
			match(ID);
			 if (!isDeclared(_input.LT(-1).getText())) {
			                       throw new UFABCSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
			                    }
			                    symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
			                    Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
			                    stack.peek().add(cmdRead);
			                  
			setState(223);
			match(FP);
			setState(224);
			match(PV);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdEscritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(UFABCGrammarParser.AP, 0); }
		public TerminalNode FP() { return getToken(UFABCGrammarParser.FP, 0); }
		public TerminalNode PV() { return getToken(UFABCGrammarParser.PV, 0); }
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public CmdEscritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdEscrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterCmdEscrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitCmdEscrita(this);
		}
	}

	public final CmdEscritaContext cmdEscrita() throws RecognitionException {
		CmdEscritaContext _localctx = new CmdEscritaContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdEscrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			match(T__20);
			setState(227);
			match(AP);
			{
			setState(228);
			termo();
			 Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
			                         stack.peek().add(cmdWrite);
			                       
			}
			setState(231);
			match(FP);
			setState(232);
			match(PV);
			 rightType = null;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public TermoContext termo() {
			return getRuleContext(TermoContext.class,0);
		}
		public ExprlContext exprl() {
			return getRuleContext(ExprlContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			termo();
			 strExpr += _input.LT(-1).getText(); 
			setState(237);
			exprl();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TermoContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(UFABCGrammarParser.ID, 0); }
		public TerminalNode NUM() { return getToken(UFABCGrammarParser.NUM, 0); }
		public TerminalNode NUMFRAC() { return getToken(UFABCGrammarParser.NUMFRAC, 0); }
		public TerminalNode TEXTO() { return getToken(UFABCGrammarParser.TEXTO, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_termo);
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(ID);
				 if (!isDeclared(_input.LT(-1).getText())) {
				                       throw new UFABCSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
				                    }
				                    if (!symbolTable.get(_input.LT(-1).getText()).isInitialized()){
				                       throw new UFABCSemanticException("Variable "+_input.LT(-1).getText()+" has no value assigned");
				                    }
				                    if (!symbolTable.get(_input.LT(-1).getText()).isUsed()){
				                        symbolTable.get(_input.LT(-1).getText()).setUsed(true);
				                    }
				                    if (rightType == null){
				                       rightType = symbolTable.get(_input.LT(-1).getText()).getType();
				                       //System.out.println("Encontrei pela 1a vez uma variavel = "+rightType);
				                    }   
				                    else{
				                       if (symbolTable.get(_input.LT(-1).getText()).getType().getValue() > rightType.getValue()){
				                          rightType = symbolTable.get(_input.LT(-1).getText()).getType();
				                          //System.out.println("Ja havia tipo declarado e mudou para = "+rightType);
				                          
				                       }
				                    }
				                  
				}
				break;
			case NUM:
				enterOuterAlt(_localctx, 2);
				{
				setState(241);
				match(NUM);
				  if (rightType == null) {
							 				rightType = Types.NUMBER;
							 				//System.out.println("Encontrei um numero pela 1a vez "+rightType);
							            }
							            else{
							                if (rightType.getValue() < Types.NUMBER.getValue()){			                    			                   
							                	rightType = Types.NUMBER;
							                	//System.out.println("Mudei o tipo para Number = "+rightType);
							                }
							            }
							         
				}
				break;
			case NUMFRAC:
				enterOuterAlt(_localctx, 3);
				{
				setState(243);
				match(NUMFRAC);
				  if (rightType == null) {
							 				rightType = Types.FRAC;
							 				//System.out.println("Encontrei um numero pela 1a vez "+rightType);
							            }
							            else{
							                if (rightType.getValue() < Types.FRAC.getValue()){			                    			                   
							                	rightType = Types.FRAC;
							                	//System.out.println("Mudei o tipo para Number = "+rightType);
							                }
							            }
							         
				}
				break;
			case TEXTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(245);
				match(TEXTO);
				  if (rightType == null) {
							 				rightType = Types.TEXT;
							 				//System.out.println("Encontrei pela 1a vez um texto ="+ rightType);
							            }
							            else{
							                if (rightType.getValue() < Types.TEXT.getValue()){			                    
							                	rightType = Types.TEXT;
							                	//System.out.println("Mudei o tipo para TEXT = "+rightType);
							                	
							                }
							            }
							         
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprlContext extends ParserRuleContext {
		public List<TerminalNode> OP() { return getTokens(UFABCGrammarParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(UFABCGrammarParser.OP, i);
		}
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public ExprlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).enterExprl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof UFABCGrammarListener ) ((UFABCGrammarListener)listener).exitExprl(this);
		}
	}

	public final ExprlContext exprl() throws RecognitionException {
		ExprlContext _localctx = new ExprlContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_exprl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OP) {
				{
				{
				setState(249);
				match(OP);
				 strExpr += _input.LT(-1).getText(); 
				setState(251);
				termo();
				 strExpr += _input.LT(-1).getText(); 
				}
				}
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001#\u0104\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0004\u0000!\b\u0000\u000b\u0000\f\u0000\"\u0001\u0000\u0001\u0000\u0004"+
		"\u0000\'\b\u0000\u000b\u0000\f\u0000(\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u00017\b\u0001\n\u0001\f\u0001"+
		":\t\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001C\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002N\b\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0004\u0004[\b\u0004\u000b\u0004\f\u0004\\\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005l\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005v\b"+
		"\u0005\u0005\u0005x\b\u0005\n\u0005\f\u0005{\t\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0004\u0005\u0081\b\u0005\u000b\u0005\f"+
		"\u0005\u0082\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006\u0092\b\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006\u009c\b\u0006\u0005\u0006\u009e\b\u0006\n\u0006\f\u0006\u00a1"+
		"\t\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0004\u0006\u00a7"+
		"\b\u0006\u000b\u0006\f\u0006\u00a8\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0004\u0006\u00af\b\u0006\u000b\u0006\f\u0006\u00b0\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u00b5\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00c9\b\u0007\u0005"+
		"\u0007\u00cb\b\u0007\n\u0007\f\u0007\u00ce\t\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00f8\b\f\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0005\r\u00ff\b\r\n\r\f\r\u0102\t\r\u0001\r\u0000\u0000"+
		"\u000e\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u0000\u0000\u0110\u0000\u001c\u0001\u0000\u0000\u0000\u0002/\u0001"+
		"\u0000\u0000\u0000\u0004M\u0001\u0000\u0000\u0000\u0006O\u0001\u0000\u0000"+
		"\u0000\bQ\u0001\u0000\u0000\u0000\nb\u0001\u0000\u0000\u0000\f\u0088\u0001"+
		"\u0000\u0000\u0000\u000e\u00b9\u0001\u0000\u0000\u0000\u0010\u00d2\u0001"+
		"\u0000\u0000\u0000\u0012\u00db\u0001\u0000\u0000\u0000\u0014\u00e2\u0001"+
		"\u0000\u0000\u0000\u0016\u00eb\u0001\u0000\u0000\u0000\u0018\u00f7\u0001"+
		"\u0000\u0000\u0000\u001a\u0100\u0001\u0000\u0000\u0000\u001c\u001d\u0005"+
		"\u0001\u0000\u0000\u001d\u001e\u0005\u001a\u0000\u0000\u001e \u0006\u0000"+
		"\uffff\uffff\u0000\u001f!\u0003\u0002\u0001\u0000 \u001f\u0001\u0000\u0000"+
		"\u0000!\"\u0001\u0000\u0000\u0000\" \u0001\u0000\u0000\u0000\"#\u0001"+
		"\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$&\u0005\u0002\u0000\u0000"+
		"%\'\u0003\u0004\u0002\u0000&%\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000"+
		"\u0000(&\u0001\u0000\u0000\u0000()\u0001\u0000\u0000\u0000)*\u0001\u0000"+
		"\u0000\u0000*+\u0003\u0006\u0003\u0000+,\u0005\u0003\u0000\u0000,-\u0005"+
		"\u0004\u0000\u0000-.\u0006\u0000\uffff\uffff\u0000.\u0001\u0001\u0000"+
		"\u0000\u0000/0\u0005\u0005\u0000\u000001\u0006\u0001\uffff\uffff\u0000"+
		"12\u0005\u001a\u0000\u000028\u0006\u0001\uffff\uffff\u000034\u0005\u001d"+
		"\u0000\u000045\u0005\u001a\u0000\u000057\u0006\u0001\uffff\uffff\u0000"+
		"63\u0001\u0000\u0000\u00007:\u0001\u0000\u0000\u000086\u0001\u0000\u0000"+
		"\u000089\u0001\u0000\u0000\u00009;\u0001\u0000\u0000\u0000:8\u0001\u0000"+
		"\u0000\u0000;B\u0005!\u0000\u0000<=\u0005\u0006\u0000\u0000=C\u0006\u0001"+
		"\uffff\uffff\u0000>?\u0005\u0007\u0000\u0000?C\u0006\u0001\uffff\uffff"+
		"\u0000@A\u0005\b\u0000\u0000AC\u0006\u0001\uffff\uffff\u0000B<\u0001\u0000"+
		"\u0000\u0000B>\u0001\u0000\u0000\u0000B@\u0001\u0000\u0000\u0000CD\u0001"+
		"\u0000\u0000\u0000DE\u0006\u0001\uffff\uffff\u0000EF\u0005\u001e\u0000"+
		"\u0000F\u0003\u0001\u0000\u0000\u0000GN\u0003\u0010\b\u0000HN\u0003\u0012"+
		"\t\u0000IN\u0003\u0014\n\u0000JN\u0003\f\u0006\u0000KN\u0003\n\u0005\u0000"+
		"LN\u0003\b\u0004\u0000MG\u0001\u0000\u0000\u0000MH\u0001\u0000\u0000\u0000"+
		"MI\u0001\u0000\u0000\u0000MJ\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000"+
		"\u0000ML\u0001\u0000\u0000\u0000N\u0005\u0001\u0000\u0000\u0000OP\u0006"+
		"\u0003\uffff\uffff\u0000P\u0007\u0001\u0000\u0000\u0000QR\u0005\t\u0000"+
		"\u0000RS\u0006\u0004\uffff\uffff\u0000ST\u0005\u001b\u0000\u0000TU\u0006"+
		"\u0004\uffff\uffff\u0000UV\u0005\n\u0000\u0000VW\u0005\u001b\u0000\u0000"+
		"WX\u0006\u0004\uffff\uffff\u0000XZ\u0005!\u0000\u0000Y[\u0003\u0004\u0002"+
		"\u0000ZY\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\Z\u0001\u0000"+
		"\u0000\u0000\\]\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^_\u0006"+
		"\u0004\uffff\uffff\u0000_`\u0005\u000b\u0000\u0000`a\u0006\u0004\uffff"+
		"\uffff\u0000a\t\u0001\u0000\u0000\u0000bc\u0005\f\u0000\u0000cd\u0006"+
		"\u0005\uffff\uffff\u0000dk\u0005\u001f\u0000\u0000el\u0003\u000e\u0007"+
		"\u0000fg\u0003\u0016\u000b\u0000gh\u0005\u0018\u0000\u0000hi\u0006\u0005"+
		"\uffff\uffff\u0000ij\u0003\u0016\u000b\u0000jl\u0001\u0000\u0000\u0000"+
		"ke\u0001\u0000\u0000\u0000kf\u0001\u0000\u0000\u0000ly\u0001\u0000\u0000"+
		"\u0000mn\u0005\u0019\u0000\u0000nu\u0006\u0005\uffff\uffff\u0000ov\u0003"+
		"\u000e\u0007\u0000pq\u0003\u0016\u000b\u0000qr\u0005\u0018\u0000\u0000"+
		"rs\u0006\u0005\uffff\uffff\u0000st\u0003\u0016\u000b\u0000tv\u0001\u0000"+
		"\u0000\u0000uo\u0001\u0000\u0000\u0000up\u0001\u0000\u0000\u0000vx\u0001"+
		"\u0000\u0000\u0000wm\u0001\u0000\u0000\u0000x{\u0001\u0000\u0000\u0000"+
		"yw\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z|\u0001\u0000\u0000"+
		"\u0000{y\u0001\u0000\u0000\u0000|}\u0005 \u0000\u0000}~\u0006\u0005\uffff"+
		"\uffff\u0000~\u0080\u0005\r\u0000\u0000\u007f\u0081\u0003\u0004\u0002"+
		"\u0000\u0080\u007f\u0001\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000"+
		"\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000"+
		"\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0085\u0006\u0005\uffff"+
		"\uffff\u0000\u0085\u0086\u0005\u000e\u0000\u0000\u0086\u0087\u0006\u0005"+
		"\uffff\uffff\u0000\u0087\u000b\u0001\u0000\u0000\u0000\u0088\u0089\u0005"+
		"\u000f\u0000\u0000\u0089\u008a\u0006\u0006\uffff\uffff\u0000\u008a\u0091"+
		"\u0005\u001f\u0000\u0000\u008b\u0092\u0003\u000e\u0007\u0000\u008c\u008d"+
		"\u0003\u0016\u000b\u0000\u008d\u008e\u0005\u0018\u0000\u0000\u008e\u008f"+
		"\u0006\u0006\uffff\uffff\u0000\u008f\u0090\u0003\u0016\u000b\u0000\u0090"+
		"\u0092\u0001\u0000\u0000\u0000\u0091\u008b\u0001\u0000\u0000\u0000\u0091"+
		"\u008c\u0001\u0000\u0000\u0000\u0092\u009f\u0001\u0000\u0000\u0000\u0093"+
		"\u0094\u0005\u0019\u0000\u0000\u0094\u009b\u0006\u0006\uffff\uffff\u0000"+
		"\u0095\u009c\u0003\u000e\u0007\u0000\u0096\u0097\u0003\u0016\u000b\u0000"+
		"\u0097\u0098\u0005\u0018\u0000\u0000\u0098\u0099\u0006\u0006\uffff\uffff"+
		"\u0000\u0099\u009a\u0003\u0016\u000b\u0000\u009a\u009c\u0001\u0000\u0000"+
		"\u0000\u009b\u0095\u0001\u0000\u0000\u0000\u009b\u0096\u0001\u0000\u0000"+
		"\u0000\u009c\u009e\u0001\u0000\u0000\u0000\u009d\u0093\u0001\u0000\u0000"+
		"\u0000\u009e\u00a1\u0001\u0000\u0000\u0000\u009f\u009d\u0001\u0000\u0000"+
		"\u0000\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a2\u00a3\u0005 \u0000\u0000"+
		"\u00a3\u00a4\u0006\u0006\uffff\uffff\u0000\u00a4\u00a6\u0005\u0010\u0000"+
		"\u0000\u00a5\u00a7\u0003\u0004\u0002\u0000\u00a6\u00a5\u0001\u0000\u0000"+
		"\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000"+
		"\u0000\u00a8\u00a9\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000"+
		"\u0000\u00aa\u00b4\u0006\u0006\uffff\uffff\u0000\u00ab\u00ac\u0005\u0011"+
		"\u0000\u0000\u00ac\u00ae\u0006\u0006\uffff\uffff\u0000\u00ad\u00af\u0003"+
		"\u0004\u0002\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00af\u00b0\u0001"+
		"\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b3\u0006"+
		"\u0006\uffff\uffff\u0000\u00b3\u00b5\u0001\u0000\u0000\u0000\u00b4\u00ab"+
		"\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001\u0000\u0000\u0000\u00b5\u00b6"+
		"\u0001\u0000\u0000\u0000\u00b6\u00b7\u0005\u0012\u0000\u0000\u00b7\u00b8"+
		"\u0006\u0006\uffff\uffff\u0000\u00b8\r\u0001\u0000\u0000\u0000\u00b9\u00ba"+
		"\u0005\u0013\u0000\u0000\u00ba\u00bb\u0006\u0007\uffff\uffff\u0000\u00bb"+
		"\u00bc\u0005\u001f\u0000\u0000\u00bc\u00bd\u0003\u0016\u000b\u0000\u00bd"+
		"\u00be\u0005\u0018\u0000\u0000\u00be\u00bf\u0006\u0007\uffff\uffff\u0000"+
		"\u00bf\u00cc\u0003\u0016\u000b\u0000\u00c0\u00c1\u0005\u0019\u0000\u0000"+
		"\u00c1\u00c8\u0006\u0007\uffff\uffff\u0000\u00c2\u00c9\u0003\u000e\u0007"+
		"\u0000\u00c3\u00c4\u0003\u0016\u000b\u0000\u00c4\u00c5\u0005\u0018\u0000"+
		"\u0000\u00c5\u00c6\u0006\u0007\uffff\uffff\u0000\u00c6\u00c7\u0003\u0016"+
		"\u000b\u0000\u00c7\u00c9\u0001\u0000\u0000\u0000\u00c8\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c3\u0001\u0000\u0000\u0000\u00c9\u00cb\u0001\u0000"+
		"\u0000\u0000\u00ca\u00c0\u0001\u0000\u0000\u0000\u00cb\u00ce\u0001\u0000"+
		"\u0000\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cd\u00cf\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000"+
		"\u0000\u0000\u00cf\u00d0\u0005 \u0000\u0000\u00d0\u00d1\u0006\u0007\uffff"+
		"\uffff\u0000\u00d1\u000f\u0001\u0000\u0000\u0000\u00d2\u00d3\u0005\u001a"+
		"\u0000\u0000\u00d3\u00d4\u0006\b\uffff\uffff\u0000\u00d4\u00d5\u0005\u0017"+
		"\u0000\u0000\u00d5\u00d6\u0006\b\uffff\uffff\u0000\u00d6\u00d7\u0003\u0016"+
		"\u000b\u0000\u00d7\u00d8\u0005\u001e\u0000\u0000\u00d8\u00d9\u0006\b\uffff"+
		"\uffff\u0000\u00d9\u00da\u0006\b\uffff\uffff\u0000\u00da\u0011\u0001\u0000"+
		"\u0000\u0000\u00db\u00dc\u0005\u0014\u0000\u0000\u00dc\u00dd\u0005\u001f"+
		"\u0000\u0000\u00dd\u00de\u0005\u001a\u0000\u0000\u00de\u00df\u0006\t\uffff"+
		"\uffff\u0000\u00df\u00e0\u0005 \u0000\u0000\u00e0\u00e1\u0005\u001e\u0000"+
		"\u0000\u00e1\u0013\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005\u0015\u0000"+
		"\u0000\u00e3\u00e4\u0005\u001f\u0000\u0000\u00e4\u00e5\u0003\u0018\f\u0000"+
		"\u00e5\u00e6\u0006\n\uffff\uffff\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000"+
		"\u00e7\u00e8\u0005 \u0000\u0000\u00e8\u00e9\u0005\u001e\u0000\u0000\u00e9"+
		"\u00ea\u0006\n\uffff\uffff\u0000\u00ea\u0015\u0001\u0000\u0000\u0000\u00eb"+
		"\u00ec\u0003\u0018\f\u0000\u00ec\u00ed\u0006\u000b\uffff\uffff\u0000\u00ed"+
		"\u00ee\u0003\u001a\r\u0000\u00ee\u0017\u0001\u0000\u0000\u0000\u00ef\u00f0"+
		"\u0005\u001a\u0000\u0000\u00f0\u00f8\u0006\f\uffff\uffff\u0000\u00f1\u00f2"+
		"\u0005\u001b\u0000\u0000\u00f2\u00f8\u0006\f\uffff\uffff\u0000\u00f3\u00f4"+
		"\u0005\u001c\u0000\u0000\u00f4\u00f8\u0006\f\uffff\uffff\u0000\u00f5\u00f6"+
		"\u0005\"\u0000\u0000\u00f6\u00f8\u0006\f\uffff\uffff\u0000\u00f7\u00ef"+
		"\u0001\u0000\u0000\u0000\u00f7\u00f1\u0001\u0000\u0000\u0000\u00f7\u00f3"+
		"\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000\u0000\u0000\u00f8\u0019"+
		"\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005\u0016\u0000\u0000\u00fa\u00fb"+
		"\u0006\r\uffff\uffff\u0000\u00fb\u00fc\u0003\u0018\f\u0000\u00fc\u00fd"+
		"\u0006\r\uffff\uffff\u0000\u00fd\u00ff\u0001\u0000\u0000\u0000\u00fe\u00f9"+
		"\u0001\u0000\u0000\u0000\u00ff\u0102\u0001\u0000\u0000\u0000\u0100\u00fe"+
		"\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u001b"+
		"\u0001\u0000\u0000\u0000\u0102\u0100\u0001\u0000\u0000\u0000\u0014\"("+
		"8BM\\kuy\u0082\u0091\u009b\u009f\u00a8\u00b0\u00b4\u00c8\u00cc\u00f7\u0100";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}