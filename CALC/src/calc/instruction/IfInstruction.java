package calc.instruction;
import calc.runtime.Environment;
import calc.parser.nodes.Expression;
import java.util.*;

public class IfInstruction implements Instruction {
	private final Expression condition;
	private final List<Instruction> body;
	
	public IfInstruction(Expression condition, List<Instruction> body) {
		if(condition == null) { 
			throw new RuntimeException("Condition cannot be null!");
		}
		if(body == null) {
			throw new RuntimeException("Body cannot be null!");
		}
		this.condition = condition;
		this.body = body;
	}

	@Override
	public void execute(Environment env) {
		Object result = condition.evaluate(env);
		if(!(result instanceof Boolean)) {
			throw new RuntimeException("Condition must evaluate to Boolean");
		}
		boolean cond = (Boolean) result;
		
		if(cond) {
			for(Instruction instr: body) {
				instr.execute(env);
			}
		}
	}
	
	public Expression getCondition() {
		return condition;
	}
	
	public List<Instruction> getBody(){
		return body;
	}
	public String toString() {
		return "AssignInstruction(" + this.condition + ", " + this.body+ ")";
	}
	
}
