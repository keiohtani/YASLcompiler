package ast;

import interp.BoolCell;
import interp.IntCell;
import interp.SymbolTable;
import interp.Value;
import interp.VoidValue;

public class VarDecl extends ASTNode {
	
	private String id;
	private Type typ;
	
	public VarDecl(String id, Type typ) {
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
		System.out.println(indent + "Var " + id + " : " + typ);
	}
	public Value interpret(SymbolTable table) {	//VarDecls
		if(typ.getClass() == IntType.class) {
			table.bind(id, new IntCell(0));
		} else if (typ.getClass() == BoolType.class) {
			table.bind(id, new BoolCell(false));
		} else {
			System.err.println("The type is mismatch.");
			System.exit(1);
		}
		return new VoidValue();
	}
}
