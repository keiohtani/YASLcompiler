package ast;

import interp.IntValue;
import interp.SymbolTable;
import interp.Value;

public class Num extends Expr {
	int value;
	public Num(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "Num " + value);
	}
	public Value interpret(SymbolTable table) {
		return new IntValue(value);
	}
}
