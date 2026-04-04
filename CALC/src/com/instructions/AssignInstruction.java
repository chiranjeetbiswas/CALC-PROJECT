package com.project;

public class AssignInstruction implements Instruction{
	private final String varName;
	private final Expression expression;
	public AssignInstruction(String varName, Expression expression) {
		if(varName.equals(null) || varName.isEmpty()) {
			throw new RuntimeException("Variable name cannot be null or empty!");
		}
		if(expression == null) {
			throw new RuntimeException("Expression can not null!");
		}
		this.varName = varName;
		this.expression = expression;
	}
	@Override
	public void execute(Environment env) {
		
		Object value = expression.evaluate(env);
		env.set(varName, value);
	}
	
	public String getVarName() {
		return varName;
	}
	
	public Expression getExpression() {
		return expression;
	}
	public String toString() {
		return "AssignInstruction(" + this.varName + ", " + this.expression+ ")";
	}
}
