package io.compiler.core.ast;

import java.util.List;

public class WhileCommand extends Command{
	
	private String criterioParada;
	private List<Command> cycle;
	
	public WhileCommand() {
		super();
	}

	public WhileCommand(String criterioParada, List<Command> cycle) {
		super();
		this.criterioParada = criterioParada;
		this.cycle = cycle;
	}

	public String getCriterioParada() {
		return criterioParada;
	}

	public void setCriterioParada(String criterioParada) {
		this.criterioParada = criterioParada;
	}

	public List<Command> getCycle() {
		return cycle;
	}

	public void setCycle(List<Command> cycle) {
		this.cycle = cycle;
	}

	@Override
	public String generateTarget() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		String cP = "";
		cP = criterioParada.replace("ee","&&").replace("ou","||").replace("<>","!=");
		str.append("while ("+cP+") {\n");
		for(Command cmd: cycle) {
			str.append(cmd.generateTarget());
		}
		str.append("}\n");
		return str.toString();
	}

}
