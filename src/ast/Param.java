package ast;

import interp.SymbolTable;
import interp.Value;
import interp.VoidValue;

public class Param extends ASTNode {
	String id;
	Type typ;
	
	public Param(String id, Type typ) {
		super();
		this.id = id;
		this.typ = typ;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Type getTyp() {
		return typ;
	}
	public void setTyp(Type typ) {
		this.typ = typ;
	}
	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "Val " + id + " : " + typ);
	}
	public Value interpret(SymbolTable table) {
		//TODO
		return new VoidValue();
	}
}
