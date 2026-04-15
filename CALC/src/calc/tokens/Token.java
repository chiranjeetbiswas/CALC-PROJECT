package calc.tokens;

public class Token {
  private final TokenType type;
	private final String value;
	private final int line;
	
	public Token(TokenType type, String value, int line) {
		this.type = type;
		this.value = value;
		this.line = line;
	}
	
	public TokenType getType() {
		return this.type;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public int getLine() {
		return this.line;
	}
	
	@Override
	public String toString() {
		if(this.getValue() == "\n") {
			return this.getType() + ": " + "\\n";
		}
		return this.getType() + ": " + this.getValue();
	}
}
