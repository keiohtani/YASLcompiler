package ast;

import interp.IntValue;
import interp.SymbolTable;
import interp.Value;

public class IntType extends Type {

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + this.getClass().getSimpleName());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Int";
	}
	public Value interpret(SymbolTable table) {	
		return new IntValue(0);
	}
}
