package ast;

import interp.SymbolTable;
import interp.Value;

public class Id extends Expr {
	String id;
	public Id(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "Id " + id);
	}
	public Value interpret(SymbolTable table) {	//FunDecls
		return table.lookup(id);
	}
}
