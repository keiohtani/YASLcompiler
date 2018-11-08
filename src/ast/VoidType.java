package ast;

import interp.SymbolTable;
import interp.Value;
import interp.VoidValue;

public class VoidType extends Type {

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + this.getClass().getSimpleName());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Void";
	}
	public Value interpret(SymbolTable table) {
		return new VoidValue();
	}
}
