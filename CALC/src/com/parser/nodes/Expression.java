package com.parser.nodes;
import com.runtime.Environment;

public interface Expression {
	
	public Object evaluate(Environment env);
}
