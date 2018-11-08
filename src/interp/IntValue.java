package interp;

import ast.Op2;

public class IntValue extends Value {
	
	int intValue;

	public IntValue(int value) {
		intValue = value;
	}

	public IntValue(int left, Op2 op, int right) {
		switch(op.name()) {
		case "Plus": 
			intValue = left + right;
			break;
		case "Minus": 
			intValue = left - right;
			break;
		case "Times": 
			intValue = left * right;
			break;
		case "Div": 
			intValue = left / right;
			break;
		case "Mod":
			intValue = left % right;
			break;
		}
	}

	public int get() {
		return intValue;
	}
}
