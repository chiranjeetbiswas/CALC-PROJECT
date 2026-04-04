package com.parser;
import com.instructions.*;

import com.parser.nodes.*;
import com.tokens.*;

import java.util.*;

public class Parser {
	public List<Token> tokens;
	public int curIndex = 0;
	public Parser(List<Token> tokens) {
		this.tokens = tokens;
	}
	//doubt
	private Instruction parseAssignment() {
		String varName = this.consume(TokenType.IDENTIFIER, "Expected variable").getValue();
		this.consume(TokenType.ASSIGN, "Expected :=");
		Expression expr = parseExpression();
		return new AssignInstruction(varName, expr);
		
	}
	private Expression parseComparison() {
		Expression left = parseExpression();
		while(this.check(TokenType.GREATER) || this.check(TokenType.LESS) || this.check(TokenType.DEQUAL)) {
			Token operator = advance();
			Expression right = parseExpression();
			left = new BinaryOpNode(left, operator, right);
		}
		return left;
	}
	private Expression parseExpression() {
		//handles + and -
		Expression left = parseTerm();
		while(this.check(TokenType.ADD) || this.check(TokenType.SUBTRACT)) {
			Token operator = advance();
			Expression right = parseTerm();
			left = new BinaryOpNode(left, operator, right);
		}
		return left;
	}
	
	
	private Expression parseTerm() {
		Expression left = parsePrimary();
//		Token token = this.peek();
		while(this.check(TokenType.MULTIPLY) || this.check(TokenType.DIVIDE)) {
			Token operator = advance();
			Expression right = parsePrimary();
			left = new BinaryOpNode(left, operator, right);
		}
		return left;
		//handles * and /
	}
	
	private Expression parsePrimary() {
		//handles a single number, string, or variable
		Token token = advance();
		if(token.getType() == TokenType.NUMBER) {
			return new NumberNode((Double.parseDouble(token.getValue())));
			
		}else if(token.getType() == TokenType.STRING) {
			return new StringNode(token.getValue());
			
		}else if(token.getType() == TokenType.IDENTIFIER) {
			return new VariableNode(token.getValue());
			
		}else {
			throw new RuntimeException("Invalid expression at line "+peek().getLine());
		}
	}
	private Token peek() {
		return tokens.get(curIndex);
	}
	
	private Token advance() {
		if(!(this.isAtEnd())) {
			curIndex++;
		}
		return this.previous();
	}
	
	private Token previous() {
		return tokens.get(curIndex - 1);
	}
	
	private boolean isAtEnd() {
		return this.peek().getType() == TokenType.EOF;
	}
	private boolean check(TokenType type) {
		if(this.isAtEnd()) {
			return false;
		}
		return this.peek().getType() == type;
	}
	private boolean match(TokenType type) {
		if (this.check(type) == true) {
			this.advance();
			return true;
		}
		return false;
	}
	private Token consume(TokenType type, String errorMessage) {
		if(this.check(type)) {
			return advance();
		}
		Token token = peek();
		throw new RuntimeException(errorMessage + " at line " + token.getLine() + ": found " + token.getValue());
	}
	
	private boolean isStartOfInstruction() {
		return check(TokenType.IDENTIFIER) || check(TokenType.PRINT) || check(TokenType.IF) || check(TokenType.LOOP);
	}

}
	
	