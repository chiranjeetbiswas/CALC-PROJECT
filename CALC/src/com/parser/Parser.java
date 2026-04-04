package com.parser;
import com.tokens.*;

import java.util.*;

public class Parser {
	public List<Token> tokens;
	public int curIndex = 0;
	public Parser(List<Token> tokens) {
		this.tokens = tokens;
	}
}
	
	