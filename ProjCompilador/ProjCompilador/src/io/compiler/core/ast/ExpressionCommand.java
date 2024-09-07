package io.compiler.core.ast;

public class ExpressionCommand extends Command{
	private String expr;
	
	public ExpressionCommand() {
		super();
	}
	
	public ExpressionCommand(String expr) {
		super();
		this.expr = expr;
	}

	public String getExpr() {
		return expr;
	}

	public void setExpr(String expr) {
		this.expr = expr;
	}

	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		return getExpr();
	}

}
