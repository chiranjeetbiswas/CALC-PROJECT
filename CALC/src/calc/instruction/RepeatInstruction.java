package calc.instruction;
import calc.parser.nodes.Expression;
import calc.runtime.Environment;
import java.util.*;

public class RepeatInstruction implements Instruction {
	private final Expression count;
	private final List<Instruction> body;
	
	public RepeatInstruction(Expression count, List<Instruction> body) {
		if(count == null) {
			throw new RuntimeException("Count cannot be null!");
		}
		if(body == null) {
			throw new RuntimeException("Body connot be null!");
		}
		this.count = count;
		this.body = body;
	}
	
	public void execute(Environment env) {
		Object result = count.evaluate(env);
		if(!(result instanceof Number)) {
			throw new RuntimeException("Number of Iterations must be a Number");
		}
		int times = ((Double) result).intValue();
		if(times<0) {
			throw new RuntimeException("Number of Iterations must be positive!");
		}
		for(int i = 0; i<times; i++) {
			for(Instruction instr: body) {
				instr.execute(env);
			}
		}
	}
	public String toString() {
		return "RepeatInstruction(" + this.count + ", " + this.body+ ")";
	}
}