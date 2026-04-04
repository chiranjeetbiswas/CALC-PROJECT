
public class NumberNode {
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
