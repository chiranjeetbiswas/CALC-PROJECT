package calc.tokens;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
	public String source;
	public int cur_pos = 0;
	
	public Tokenizer(String source) {
		this.source = source;
	}
	
	public List<Token> tokenize(){
		List<Token> token = new ArrayList<>();
		int line = 1;
		int i = cur_pos;
		
		//indent
//		boolean expectingIndent = false;
		//..
		
		while(i < source.length()) {
			char c = source.charAt(i);
			if(c == '\r') {
				i++;
				continue;
			}
			
			if(c == ' ' || c == '\t') {
				i++;
				continue;
			}
			
			else if(c == '\n') {
				token.add(new Token(TokenType.NEWLINE,"\n",line));
				line++;
				i++;
			
				//Adding indentation logic here(tokenizer)
				int count = 0;
				while(i < source.length() && (source.charAt(i) == '\t')) {
					count++;
					i++;
				}
				for(int j = 0; j < count; j++) {
                    token.add(new Token(TokenType.INDENT, "INDENT", line));
				}
			}
			
			else if(Character.isLetter(c) || c == '_') {
				StringBuilder ID = new StringBuilder();
				while(i<source.length() && (Character.isLetterOrDigit(source.charAt(i)) || source.charAt(i) == '_')) {
					ID.append(source.charAt(i));
					i++;
				}
				
				token.add(new Token(TokenType.IDENTIFIER,ID.toString(),line));
			}
			
			else if(Character.isDigit(c)) {
				int count = 0;
				StringBuilder NUM = new StringBuilder();
				while(i<source.length() && (Character.isDigit(source.charAt(i)) || source.charAt(i) == '.')) {
					c = source.charAt(i);
					if(c == '.') {
						count++;
						//Throw Exception
						if(count>1) {
							throw new RuntimeException("Invalid number format at line "+ line);
						}
					}
					NUM.append(c);
					i++;
				}
				if(!NUM.isEmpty() && NUM.charAt(0)!='.' && NUM.charAt(NUM.length() - 1)!='.') {
					token.add(new Token(TokenType.NUMBER, NUM.toString(), line));
				}
				else {
					throw new RuntimeException("Invalid number format at line "+ line);
				}
				
			}
			
			else if(c == '"') {
				i++;
				StringBuilder STR = new StringBuilder();
				while(i<source.length()) {
					c = source.charAt(i);
					
					if(c == '"') {
						break;
					}
					STR.append(c);
					i++;
				}
				if(i <source.length() && source.charAt(i) == '"') {
					token.add(new Token(TokenType.STRING, STR.toString(), line));
					i++;
				}else {
					throw new RuntimeException("Unterminated string at line "+ line);
				}
			}
			
			else if(i<source.length() - 1 && c == ':' && source.charAt(i+1) == '=') {
				token.add(new Token(TokenType.ASSIGN,":=",line));
				i += 2;
			}
			
			else if(i<source.length() - 1 && c == '>' && source.charAt(i+1) == '>') {
				token.add(new Token(TokenType.PRINT, ">>",line));
				i += 2;
			}
			
			else if(i<source.length() - 1 && c == '=' && source.charAt(i+1) == '>') {
				token.add(new Token(TokenType.ARROW, "=>", line));
				i += 2;
			}
			
			else if(i<source.length() - 1 && c == '=' && source.charAt(i+1) == '=') {
				token.add(new Token(TokenType.DEQUAL, "==", line));
				i += 2;
			}
			else if(i<source.length() - 1 && c == '<' && source.charAt(i+1) == '=') {
				token.add(new Token(TokenType.LTE, "<=", line));
				i+=2;
			}
			else if(i<source.length() - 1 && c == '>' && source.charAt(i+1) == '=') {
				token.add(new Token(TokenType.GTE, ">=" ,line));
				i+=2;
			}
			
			else if(c == '?') {
				token.add(new Token(TokenType.IF,"?",line));
				i++;
				continue;
			}
			
			else if(c == '@') {
				token.add(new Token(TokenType.LOOP,"@",line));
				i++;
				continue;
			}
			
			else if(c == '+') {
				token.add(new Token(TokenType.ADD, "+", line));
				i++;
				continue;
			}
			
			else if(c == '-') {
				token.add(new Token(TokenType.SUBTRACT,"-",line));
				i++;
				continue;
			}
			
			else if(c == '*') {
				token.add(new Token(TokenType.MULTIPLY, "*", line));
				i++;
				continue;
			}
			
			else if(c == '/') {
				token.add(new Token(TokenType.DIVIDE,"/", line));
				i++;
				continue;
			}
			
			else if(c == '>') {
				token.add(new Token(TokenType.GREATER, ">",line));
				i++;
				continue;
			}
			
			else if(c == '<') {
				token.add(new Token(TokenType.LESS,"<",line));
				i++;
				continue;
			}
			//Throw Exception
			else {
				i++;
				throw new RuntimeException("Unexpected character: "+c+" at line " + line);
			}
		}
		token.add(new Token(TokenType.EOF, "EOF", line));
		return token;
	}
}