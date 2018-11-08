package ast;

import interp.BoolValue;
import interp.SymbolTable;
import interp.Value;

public class True extends Expr {
	
	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "True");
	}
	public Value interpret(SymbolTable table) {
		return new BoolValue(true);
	}
}
