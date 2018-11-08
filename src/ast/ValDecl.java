package ast;

import interp.IntValue;
import interp.SymbolTable;
import interp.Value;
import interp.VoidValue;

public class ValDecl extends ASTNode {
	private String id;
	private int value;
	
	public ValDecl(String id, int value) {
		this.id = id;
		this.value = value;
	}
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	@Override
	public void display(String indent) {
		System.out.println(indent + "Val " + id + " = " + value);
	}
	public Value interpret(SymbolTable table) {	//ValDecls
		table.bind(id, new IntValue(value));
		return new VoidValue();
	}
}
