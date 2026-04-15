package calc.parser.nodes;

import calc.runtime.*;
import calc.tokens.*;

public class BinaryOpNode implements Expression {
  private Expression left;
	private Token operator;
	private Expression right;
	
	public BinaryOpNode(Expression left, Token operator, Expression right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
	@Override
	public Object evaluate(Environment env) {
		Object leftValue = left.evaluate(env);
		Object rightValue = right.evaluate(env);
		String Op = operator.getValue();
		if(Op.equals("+") || Op.equals("-") || Op.equals("*") || Op.equals("/")) {
			
			double l = (Double) leftValue;
			double r = (Double) rightValue;
			
			if(Op.equals("+")) {
				return l + r;
			}if(Op.equals("-")) {
				return l - r;
			}if(Op.equals("*")) {
				return l * r;
			}if(Op.equals("/")) {
				if(r == 0) throw new RuntimeException("Division by zero at line "+ operator.getLine());
				return l / r;
			}
		}else if(Op.equals(">") || Op.equals("<") || Op.equals("==") || Op.equals("<=") || Op.equals(">=")) {
			double l = (Double) leftValue;
			double r = (Double) rightValue;
			
			if(Op.equals(">")) {
				return l>r;
			}if(Op.equals("<")) {
				return l<r;
			}if(Op.equals("==")) {
				return l==r;
			}if(Op.equals("<=")) {
				return l<=r;
			}if(Op.equals(">=")) {
				return l>=r;
			}
		}else if(Op.equals("==")){
			return leftValue.equals(rightValue);
		}
		throw new RuntimeException("Unknown operator: " + Op);
		
	}
	
	public Expression getLeft() {
		return left;
	}
	public Token getOperator() {
		return operator;
	}
	public Expression getRight() {
		return right;
	}
	
	public String toString() {
		return "BinaryOpNode(" + left +", "+ operator +", "+right+")";
	}
}
