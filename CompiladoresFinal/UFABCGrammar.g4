grammar UFABCGrammar;

@header {
	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exception.*;
	import io.compiler.core.ast.*;
}

@members {
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
}
 
programa	: 'programa' ID  { program.setName(_input.LT(-1).getText());
                               stack.push(new ArrayList<Command>()); 
                             }
               declaravar+
               'inicio'
               comando+
               unusedFlag
               'fim'
               'fimprog'
               
               {
                  program.setSymbolTable(symbolTable);
                  program.setCommandList(stack.pop());
               }
			;
						
declaravar	: 'declare' { currentDecl.clear(); } 
               ID  { currentDecl.add(new Var(_input.LT(-1).getText()));}
               ( VIRG ID                
              		 { currentDecl.add(new Var(_input.LT(-1).getText()));}
               )*	 
               DP 
               (
               'number' {currentType = Types.NUMBER;}
               |
               'decimal' {currentType = Types.FRAC;}
               |
               'text' {currentType = Types.TEXT;}
               ) 
               
               { updateType(); } 
               PV
			;
			
comando     :  cmdAttrib
			|  cmdLeitura
			|  cmdEscrita
			|  cmdIF
			|  cmdWhile
			|  cmdFor
			;
			
unusedFlag  : {for (Var var : symbolTable.values()) {
                  if (!var.isUsed()) {
                     throw new UFABCSemanticException("Variable "+var.getId()+" declared but not used");
                  }
               }}
            ;
            
cmdFor      : 'aPartirDe' { stack.push(new ArrayList<Command>());
                           forStack.push(new ForCommand());
                           strExpr = "";
                   } 
              NUM  {forStack.peek().setInicio(_input.LT(-1).getText());}
              '->' 
              NUM {forStack.peek().setFim(_input.LT(-1).getText());}
              DP 
              comando+ { forStack.peek().setRepeticao(stack.pop());}
              'close' { stack.peek().add(forStack.pop()); }
            ;
			
cmdWhile    : 'enquanto' { stack.push(new ArrayList<Command>());
                           whileStack.push(new WhileCommand());
                           strExpr = "";
                          } 
               AP 
               (opNot
               |
               expr 
               OPREL  { strExpr += _input.LT(-1).getText(); }
               expr )
               (OPLOG { strExpr += _input.LT(-1).getText(); }
               (opNot| 
               expr 
               OPREL  { strExpr += _input.LT(-1).getText(); }
               expr))*
               FP     { whileStack.peek().setCriterioParada(strExpr);}
               'inicioEnquanto'
               comando+ { whileStack.peek().setCycle(stack.pop());}
               'fimEnquanto'{ stack.peek().add(whileStack.pop()); }
            ;
			
cmdIF        : 'se'  { stack.push(new ArrayList<Command>());
                      strExpr = "";
                      ifStack.push(new IfCommand());
                      currentIfCommand = new IfCommand();
                    } 
               AP 
               (opNot|
               expr
               OPREL  { strExpr += _input.LT(-1).getText(); }
               expr)
               (OPLOG { strExpr += _input.LT(-1).getText(); }
               (opNot|
               expr 
               OPREL  { strExpr += _input.LT(-1).getText(); }
               expr ))*
               FP  { ifStack.peek().setExpression(strExpr); }
               'entao'
               comando+
               { 
                  ifStack.peek().setTrueList(stack.pop());
               }
               ( 'senao'
                  { stack.push(new ArrayList<Command>()); }
                 comando+
                 {
                   ifStack.peek().setFalseList(stack.pop());
                 }
               )? 
               'fimse' 
               {
                      stack.peek().add(ifStack.pop());
               }
            ;
            
            opNot       : 'not' {strExpr += "!(";}
               AP
               expr 
               OPREL  { strExpr += _input.LT(-1).getText(); }
               expr
               (OPLOG { strExpr += _input.LT(-1).getText(); }
               (opNot| 
               expr 
               OPREL  { strExpr += _input.LT(-1).getText(); }
               expr))* 
               FP {strExpr += ")";}
            ;
			
cmdAttrib   : ID { if (!isDeclared(_input.LT(-1).getText())) {
                       throw new UFABCSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
                   }
                   symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
                   leftType = symbolTable.get(_input.LT(-1).getText()).getType();
                   strExpr = "";
                   currentAttribCommand = new AttribCommand();
                   strExpr += _input.LT(-1).getText();
                   currentAttribCommand.setAtribuido(strExpr);
                   strExpr = "";
                 }
              OP_AT { strExpr += _input.LT(-1).getText();}
              expr 
              PV   { currentAttribCommand.setExpression(strExpr); 
                     stack.peek().add(currentAttribCommand);}
              
              {
                 //System.out.println("Left  Side Expression Type = "+leftType);
                 //System.out.println("Right Side Expression Type = "+rightType);
                 if (leftType.getValue() < rightType.getValue()){
                    throw new UFABCSemanticException("Type Mismatchig on Assignment");
                 }
                 rightType = null;
              }
			;			
			
cmdLeitura  : 'leia' AP 
               ID { if (!isDeclared(_input.LT(-1).getText())) {
                       throw new UFABCSemanticException("Undeclared Variable: "+_input.LT(-1).getText());
                    }
                    symbolTable.get(_input.LT(-1).getText()).setInitialized(true);
                    Command cmdRead = new ReadCommand(symbolTable.get(_input.LT(-1).getText()));
                    stack.peek().add(cmdRead);
                  } 
               FP 
               PV 
			;
			
cmdEscrita  : 'escreva' AP 
              ( termo  { Command cmdWrite = new WriteCommand(_input.LT(-1).getText());
                         stack.peek().add(cmdWrite);
                       } 
              ) 
              FP PV { rightType = null;}
			;			

			
expr		:  termo  { strExpr += _input.LT(-1).getText(); } exprl 			
			;
			
termo		: ID  { if (!isDeclared(_input.LT(-1).getText())) {
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
			| NUM    {  if (rightType == null) {
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
			| NUMFRAC    {  if (rightType == null) {
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
			| TEXTO  {  if (rightType == null) {
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
			;
			
exprl		: ( OP { strExpr += _input.LT(-1).getText(); } 
                termo { strExpr += _input.LT(-1).getText(); } 
              ) *
			;	
			
OP			: '+' | '-' | '*' | '/' 
			;	
			
OP_AT	    : ':='
		    ;
		    
OPREL       : '>' | '<' | '>=' | '<= ' | '<>' | '=='
			;		
			
OPLOG       : 'ee' | 'ou'
			;    			
			
ID			: [a-z] ( [a-z] | [A-Z] | [0-9] )*		
			;
			
NUM			: AP '-' [0-9]+ FP | [0-9]+
			;
   
NUMFRAC		: AP '-' [0-9]+ '.' [0-9]+ FP | [0-9]+ '.' [0-9]+ 
			;
			
VIRG		: ','
			;
						
PV			: ';'
            ;			
            
AP			: '('
			;            
						
FP			: ')'
			;
									
DP			: ':'
		    ;
		    
TEXTO       : '"' ( [a-z] | [A-Z] | [0-9] | ',' | '.' | ' ' | '-' )* '"'
			;		    
		    			
WS			: (' ' | '\n' | '\r' | '\t' ) -> skip
			;