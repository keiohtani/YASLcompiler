package ast;

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

}
