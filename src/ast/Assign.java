package ast;

import interp.BoolCell;
import interp.BoolValue;
import interp.IntCell;
import interp.IntValue;
import interp.SymbolTable;
import interp.Value;

public class Assign extends Stmt {
	String id;
	Expr expr;
	
	public Assign(String id, Expr expr) {
		this.id = id;
		this.expr = expr;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Expr getExp() {
		return expr;
	}

	public void setExp(Expr exp) {
		this.expr = exp;
	}
	
	@Override
	public void display(String indent) {
		// TODO Auto-generated method stub
		System.out.println(indent + "Assign " + id);
		expr.display(indent + "  ");
	}
	
	public Value interpret(SymbolTable table) {	//Assign
		Value lhs = table.lookup(id);
		Value rhs = expr.interpret(table);
		if (lhs instanceof IntCell) {
			((IntCell)lhs).set(((IntValue)rhs).get());
		} else if (lhs instanceof BoolCell) {
			((BoolCell)lhs).set(((BoolValue)rhs).get());
		} else {
			System.err.println("Assigned type does not match.");
			System.exit(1);
		}
		return rhs;
	}
}
