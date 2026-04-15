package calc.parser.nodes;
import calc.runtime.Environment;

public interface Expression {

    public Object evaluate(Environment env);
}

