package calc.interpreter;


import java.util.*;
import calc.tokens.Tokenizer;
import calc.parser.Parser;
import calc.instruction.Instruction;
import calc.runtime.Environment;

public class Interpreter {
	public void run(String sourceCode) {
		Tokenizer tokenizer = new Tokenizer(sourceCode);
		Parser parser = new Parser(tokenizer.tokenize());
		List<Instruction> instructions = parser.parse();
		Environment env = new Environment();
		for(Instruction instr: instructions) {
			instr.execute(env);
		}
		
	}
	public static void main(String[] args) {
		Interpreter intp = new Interpreter();
		String sourceCode = "i := 1\n@ 4 =>\n >> i\ni := i+1";
		intp.run(sourceCode);
	}
}