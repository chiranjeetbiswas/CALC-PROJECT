package calc.instruction;
import calc.runtime.Environment;


public interface Instruction {
    public void execute(Environment env);
}
