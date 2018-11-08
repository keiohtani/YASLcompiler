package interp;

import ast.Op2;

public class BoolValue extends Value {
	boolean boolValue;
	public BoolValue(boolean b) {
		boolValue = b;
	}	
	public BoolValue(int intValue, Op2 op, int intValue2) {
		switch(op.name()) {
		case "EQ":
			boolValue = intValue == intValue2;
			break;
		case "NE":
			boolValue = intValue != intValue2;
			break;
		case "LE":
			boolValue = intValue <= intValue2;
			break;
		case "GE":
			boolValue = intValue >= intValue2;
			break;
		case "LT":
			boolValue = intValue < intValue2;
			break;
		case "GT":
			boolValue = intValue > intValue2;
			break;
		}
	}
	public boolean get() {
		return boolValue;
	}
}
