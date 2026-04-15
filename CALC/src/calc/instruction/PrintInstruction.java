package calc.instruction;

import calc.parser.nodes.Expression;
import calc.runtime.Environment;

public class PrintInstruction implements Instruction {
	private final Expression expression;
	public PrintInstruction(Expression expression) {
		if(expression == null) {
			throw new RuntimeException("Expression cannot be null");
		}
		this.expression = expression;
	}
	@Override
	public void execute(Environment env) {
		Object value = expression.evaluate(env);
		//Integer logic
		if(value instanceof Double) {
			double d = (Double) value;
			if(d == Math.floor(d)) {
				System.out.println((int)d);
			}else {
				System.out.println(d);
			}
		//...
			
		}else {
			System.out.println(value);
		}
		
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public String toString() {
		return "PrintInstruction(" + this.expression+ ")";
	}
}