package calc.parser.nodes;

import calc.runtime.*;

public class StringNode implements Expression {
  private String value;
	
	public StringNode(String value) {
		this.value = value;
	}
	
	@Override
	public Object evaluate(Environment env) {
		return value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return "StringNode(" + value + ")";
	}
}
