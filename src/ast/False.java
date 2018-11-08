package ast;

import interp.BoolValue;
import interp.SymbolTable;
import interp.Value;

public class False extends Expr {

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "False");
	}
	public Value interpret(SymbolTable table) {
		return new BoolValue(false);
	}
}
