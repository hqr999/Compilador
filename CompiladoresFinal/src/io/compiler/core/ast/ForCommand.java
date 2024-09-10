package io.compiler.core.ast;

import java.util.List;

public class ForCommand extends Command{
    private String inicio;
    private String fim;
    private List<Command> repeticao;
	
	public ForCommand() {
		super();
	}

	public ForCommand(String inicio, String fim, List<Command> repeticao) {
		super();
		this.inicio = inicio;
		this.fim = fim;
		this.repeticao = repeticao;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public List<Command> getRepeticao() {
		return repeticao;
	}

	public void setRepeticao(List<Command> repeticao) {
		this.repeticao = repeticao;
	}

	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("for (int i ="+inicio+"; i<"+fim+"; i++ ) {\n");
		for(Command cmd: repeticao) {
			str.append(cmd.generateTarget());
		}
		str.append("}\n");
		return str.toString();
	}

}
