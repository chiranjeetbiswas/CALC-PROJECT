package calc.parser.nodes;

import calc.runtime.*;

public class NumberNode implements Expression {
  private double value;
	public NumberNode(double value) {
		this.value = value;
	}
	public NumberNode(String text) {
		this.value = Double.parseDouble(text);
	}
	@Override
	public Object evaluate(Environment env) {
		return Double.valueOf(value);
	}
	public double getValue() {
		return value;
	}
	public String toString() {
		return "NumberNode(" + value +")";
	}
}
