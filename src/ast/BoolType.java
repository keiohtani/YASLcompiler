package ast;

import interp.BoolValue;
import interp.SymbolTable;
import interp.Value;

public class BoolType extends Type {

	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + this.getClass().getSimpleName());
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Bool";
	}
	public Value interpret(SymbolTable table) {	//FunDecls
		return new BoolValue(false);
	}
}
