package io.compiler.core.ast;

public class AttribCommand extends Command{
	private String atribuido;
	private String expression;

	public AttribCommand() {
		super();
	}

	public AttribCommand(String atribuido, String expression) {
		super();
		this.atribuido = atribuido;
		this.expression = expression;
	}

	public String getAtribuido() {
		return atribuido;
	}

	public void setAtribuido(String atribuido) {
		this.atribuido = atribuido;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append(atribuido);
		str.append(expression.substring(1) + ";");
		str.append("\n");
		return str.toString();
	}

}
