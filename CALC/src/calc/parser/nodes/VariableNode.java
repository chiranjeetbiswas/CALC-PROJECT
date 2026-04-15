package calc.parser.nodes;

import calc.runtime.*;

public class VariableNode implements Expression {
  private final String varName;
	
	public VariableNode(String varName) {
		if(varName == null || varName.isEmpty()) {
			throw new IllegalArgumentException("Variable name cannot be null or empty");
		}
		this.varName = varName;
	}
	
	@Override
	public Object evaluate(Environment env) {
		if (env == null) {
			throw new IllegalArgumentException("Environment must not be null when evaluating a VariableNode");
	    }
		return env.get(varName);
	}
	
	public String getVarName() {
		return varName;
	}
	
	public String toString() {
		return "VariableNode(" + varName + ")";
	}
}
